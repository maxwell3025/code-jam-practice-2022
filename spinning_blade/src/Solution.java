import java.io.IOError;
import java.util.Scanner;

class Saw{
    int x;
    int y;
    int k;
    long MPx = 0;
    long MPy = 0;
    long mass = 0;
    public Saw(int x, int y, int k){
        this.x = x;
        this.y = y;
        this.k = k;
    }
    public void put(int x, int y, int[][] m){
        MPx += (long) x * m[x][y];
        MPy += (long) y * m[x][y];
        mass += m[x][y];
    }
}
public class Solution{
    static Scanner in = new Scanner(System.in);
    static int CASE_NUM;
    Saw[][][] mem; //[x][y][k]
    int R;
    int C;
    int[][] w;
    Saw getSaw(int x, int y, int k){
        if(mem[x][y][k] != null){
            return mem[x][y][k];
        }
        if(k <= 2){
            return mem[x][y][k] = new Saw(x, y, k);
        }
        Saw prev = getSaw(x, y, k-1);
        Saw s = new Saw(x, y, k);
        s.MPx = prev.MPx;
        s.MPy = prev.MPy;
        s.mass = prev.mass;
        //add on extra bits
        s.put(x + k - 2, y, w);
        s.put(x, y + k - 2, w);
        s.put(x + k - 2, y + k - 2, w);
        for(int i = 1; i<=k-2; i++){
            s.put(x + k - 1, y + i, w);
            s.put(x + i, y + k - 1, w);
        }
        return mem[x][y][k] = s;
    }
    boolean checkSaw(int x, int y, int k){
        Saw s = getSaw(x, y, k);
        double MPxExp = s.mass * (x + 0.5 * (k-1));
        double MPyExp = s.mass * (y + 0.5 * (k-1));
        return s.MPx == MPxExp && s.MPy == MPyExp;
    }

    public Solution(int CASE){
        R = in.nextInt();
        C = in.nextInt();
        in.nextInt();
        w = new int[C][R];
        mem = new Saw[C][R][];
        for(int row = 0; row < R; row++){
            char[] rowStr = in.next().toCharArray();
            for(int col = 0; col < C; col++){
                w[col][row] = Integer.parseInt(Character.toString(rowStr[col]));
                mem[col][row] = new Saw[Math.min(R-row, C-col) +1];
            }
        }
        int out = 2;
        for(int y = 0; y < R; y++){
            for(int x = 0; x < C; x++){
                for(int k = 2; x+k<=C && y+k<=R; k++){
                    if(checkSaw(x, y, k) && k>out)
                        out = k;
                }
            }
        }
        if(out == 2)
            System.out.println("Case #" + CASE + ": " + "IMPOSSIBLE");
        else
            System.out.println("Case #" + CASE + ": " + out);
    }
    public static void main(String[] args){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            new Solution(CASE);
        };
    }
}