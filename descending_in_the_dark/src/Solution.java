import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Solution{
    static Scanner in = new Scanner(System.in);
    static int CASE_NUM;
    int R;
    int C;
    boolean[][] open;
    boolean[][] reachable;
    List<Point> pos;
    int[] x = new int[10];
    int[] y = new int[10];
    int d=0;
    int fill(Point cave){
        int total = 0;
        Stack<Point> queue = new Stack<>();
        queue.push(cave);
        reachable[cave.x][cave.y] = true;
        while(!queue.isEmpty()){
            total++;
            Point current = queue.pop();
            if(!reachable[current.x][current.y-1] && open[current.x][current.y-1]){
                queue.push(new Point(current.x, current.y-1));
                pos.add(new Point(current.x, current.y-1));
                reachable[current.x][current.y-1] = true;
            }
            if(!reachable[current.x-1][current.y] && open[current.x-1][current.y]){
                queue.push(new Point(current.x-1, current.y));
                pos.add(new Point(current.x-1, current.y));
                reachable[current.x-1][current.y] = true;
            }
            if(!reachable[current.x+1][current.y] && open[current.x+1][current.y]){
                queue.push(new Point(current.x+1, current.y));
                pos.add(new Point(current.x+1, current.y));
                reachable[current.x+1][current.y] = true;
            }
        }
        return total;
    }

    boolean shiftLeft(){
        boolean success = false;
        for(Point p: pos){
            if(open[p.x-1][p.y]){
                success = true;
                p.x--;
            }
        }
        return success;
    }

    boolean shiftRight(){
        boolean success = false;
        for(Point p: pos){
            if(open[p.x+1][p.y]){
                success = true;
                p.x++;
            }
        }
        return success;
    }
    boolean canDown(){
        boolean change = false;
        for(Point p: pos){
            if(open[p.x][p.y+1])
                change = true;
            if(open[p.x][p.y+1] && !reachable[p.x][p.y+1])
                return false;
        }
        return change;
    }
    void shiftDown(){
        for(Point p: pos){
            if(open[p.x][p.y+1])
                p.y++;
        }
    }

    void solve(){
        levelLoop: while(true){
            while(shiftLeft());
            if(canDown()){
                shiftDown();
                continue;
            }
            while(shiftRight()){
                if(canDown()){
                    shiftDown();
                    continue levelLoop;
                }
            }
            while(shiftLeft()){
                if(canDown()){
                    shiftDown();
                    continue levelLoop;
                }
            }
            return;
        }
    }
    public Solution(int CASE){
        R = in.nextInt();
        C = in.nextInt();
        open = new boolean[C][R];
        for(int row = 0; row<R; row++){
            char[] line = in.next().toCharArray();
            for(int col = 0; col<C; col++){
                open[col][row] = !(line[col] == '#');
                if(line[col] != '.' && line[col] != '#'){
                    int num = Integer.parseInt(line[col]+"");
                    if(num + 1>d){
                        d = num + 1;
                    }
                    x[num] = col;
                    y[num] = row;
                }
            }
        }
        System.out.println("Case #" + CASE + ":");
        for(int iCave = 0; iCave < d; iCave++){
            System.out.print(iCave + ":");
            Point cave = new Point(x[iCave], y[iCave]);
            reachable = new boolean[C][R];
            pos = new ArrayList<>();
            System.out.print(" " + fill(cave));
            solve();
            boolean solved = true;
            for(Point p: pos){
                if(p.x != cave.x || p.y != cave.y){
                    solved = false;
                }
            }
            System.out.println(" " + (solved?"Lucky":"Unlucky"));
        }
    }
    public static void main(String[] args){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            new Solution(CASE);
        };
    }
}