import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

class Interval implements Comparable<Interval>{
    int bottom;
    int top;

    public Interval(int bottom, int top) {
        this.bottom = bottom;
        this.top = top;
    }

    @Override
    public int compareTo(Interval o) {
        return this.bottom - o.bottom;
    }
}
public class Solution{
    static Scanner in = new Scanner(System.in);
    static int CASE_NUM;
    static final int DIR_U = 0;
    static final int DIR_L = 1;
    static final int DIR_D = 2;
    static final int DIR_R = 3;
    int W;
    int H;
    int B;
    int[] X0;
    int[] Y0;
    int[] X1;
    int[] Y1;

    List<TreeSet<Interval>> obst = new ArrayList<>();
    int xStream = 0;
    int yStream = 0;
    int dir = DIR_U;

    void putInterval(int start, int end, int x){
        TreeSet<Interval> strip = obst.get(x);
        Interval toAdd = new Interval(start, end);
        Interval above = strip.higher(toAdd);
        Interval below = strip.lower(toAdd);
        if(above != null && above.bottom<=toAdd.top+1){
            toAdd.top = above.top;
            strip.remove(above);
        }
        if(below != null && below.top >= toAdd.bottom-1){
            toAdd.bottom = below.bottom;
            strip.remove(below);
        }
        strip.add(toAdd);
    }
    Interval getInterval(int x, int y){
        if(x<0 || x>=W)
            return new Interval(0,H-1);
        Interval b = obst.get(x).floor(new Interval(y, y));
        if(b!=null && y>=b.bottom && y<=b.top){
            return b;
        }
        return null;
    }
    boolean filled(int x, int y){
        if(x<0 || x>=W)
            return true;
        Interval b = obst.get(x).floor(new Interval(y, y));
        return b!=null && y>=b.bottom && y<=b.top;
    }

    void up(){
        Interval lWall = getInterval(xStream-1, yStream+1);
        Interval above = obst.get(xStream).higher(new Interval(yStream, yStream));
        if(above == null || lWall.top + 1 < above.bottom){
            dir = DIR_L;
            putInterval(yStream+1, lWall.top+1, xStream);
            yStream = lWall.top+1;
        }else{
            putInterval(yStream+1, above.bottom-1, xStream);
            yStream = above.bottom-1;
            dir = filled(xStream+1, yStream)?DIR_D:DIR_R;
        }
    }
    void left(){
        while (true) {
            xStream--;
            putInterval(yStream, yStream, xStream);
            if(!filled(xStream, yStream-1)){
                dir = DIR_D;
                break;
            }
            if(filled(xStream -1, yStream)){
                dir = filled(xStream, yStream+1)?DIR_R: DIR_U;
                break;
            }
        }
    }
    void down(){
        Interval rWall = getInterval(xStream+1, yStream-1);
        Interval below = obst.get(xStream).lower(new Interval(yStream, yStream));
        if(below == null || rWall.bottom-1 > below.top){
            putInterval(rWall.bottom-1, yStream-1, xStream);
            yStream = rWall.bottom-1;
            dir = DIR_R;
        }
        else{
            putInterval(below.top+1, yStream-1, xStream);
            yStream = below.top+1;
            dir = filled(xStream-1, yStream)? DIR_U:DIR_L;
        }
    }
    void right(){
        while(true){
            xStream++;
            putInterval(yStream, yStream, xStream);
            if(!filled(xStream, yStream+1)){
                dir = DIR_U;
                break;
            }
            if(filled(xStream+1, yStream)){
                dir = filled(xStream, yStream-1)? DIR_L: DIR_D;
            }
        }
    }
    void forward(){
        switch (dir){
            case DIR_U:
                up();
                break;
            case DIR_L:
                left();
                break;
            case DIR_R:
                right();
                break;
            case DIR_D:
                down();
                break;
        }
    }
    public Solution(int CASE){
        W = in.nextInt();
        H = in.nextInt();
        B = in.nextInt();
        X0 = new int[B];
        Y0 = new int[B];
        X1 = new int[B];
        Y1 = new int[B];
        for(int i = 0; i < B; i++){
            X0[i] = in.nextInt();
            Y0[i] = in.nextInt();
            X1[i] = in.nextInt();
            Y1[i] = in.nextInt();
        }

        for(int i = 0; i < W; i++){
            obst.add(new TreeSet<>());
        }

        for(int i = 0; i < B; i++){
            for(int x = X0[i]; x<= X1[i]; x++){
                putInterval(Y0[i], Y1[i], x);
            }
        }
        int successCount = 0;
        for(int i = 0; i< W; i++){
            xStream = i;
            yStream = 0;
            dir = DIR_U;
            if(filled(xStream, yStream))
                continue;
            while(true){
                forward();
                if(yStream <= 0) {
                    i = xStream;
                    break;
                }
                if( filled(xStream+1, yStream) &&
                    filled(xStream-1, yStream) &&
                    filled(xStream, yStream+1) &&
                    filled(xStream, yStream-1))
                    break;
                if(yStream >= H-1){
                    successCount++;
                    break;
                }
            }
        }

        System.out.println("Case #" + CASE + ": " + successCount);
    }
    public static void main(String[] args){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            new Solution(CASE);
        };
    }
}