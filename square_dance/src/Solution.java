import java.util.Scanner;

class Dancer{
    public Dancer left = null;
    public Dancer right = null;
    public Dancer up = null;
    public Dancer down = null;
    public int skill;
    public boolean eliminated = false;
    public Dancer(int skill){
        this.skill = skill;
    }
    public boolean isEliminated(){
        int neighborCount = 0;
        int sum = 0;
        if(left!=null) {
            neighborCount++;
            sum += left.skill;
        }
        if(right!=null) {
            neighborCount++;
            sum += right.skill;
        }
        if(up!=null) {
            neighborCount++;
            sum += up.skill;
        }
        if(down!=null) {
            neighborCount++;
            sum += down.skill;
        }
        return sum > skill * neighborCount;
    }
    public void eliminate(){
        eliminated = true;
        if(left!=null) {
            left.right = right;
        }
        if(right!=null) {
            right.left = left;
        }
        if(up!=null) {
            up.down = down;
        }
        if(down!=null) {
            down.up = up;
        }
    }
}
public class Solution{
    static Scanner in = new Scanner(System.in);
    int CASE_NUM;
    int R;
    int C;
    Dancer[][] dancers;
    int interest;
    boolean nextRound(){
        boolean anyInfo = false;

        for(int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                if(dancers[row][col] != null){
                    interest += dancers[row][col].skill;
                    dancers[row][col].eliminated = dancers[row][col].isEliminated();
                }
            }
        }
        for(int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                if(dancers[row][col] != null && dancers[row][col].eliminated){
                    anyInfo = true;
                    dancers[row][col].eliminate();
                    dancers[row][col] = null;
                }
            }
        }
        return anyInfo;
    }
    public Solution(){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            R = in.nextInt();
            C = in.nextInt();
            interest = 0;
            dancers = new Dancer[R][C];
            for(int row = 0; row < R; row++) {
                for (int col = 0; col < C; col++) {
                    dancers[row][col] = new Dancer(in.nextInt());
                }
            }
            for(int row = 0; row < R; row++) {
                for (int col = 0; col < C; col++) {
                    Dancer curDancer = dancers[row][col];
                    if(row != 0)curDancer.up = dancers[row - 1][col];
                    if(col != 0)curDancer.left = dancers[row][col - 1];
                    if(row != R-1)curDancer.down = dancers[row + 1][col];
                    if(col != C-1)curDancer.right = dancers[row][col + 1];
                }
            }
            while(nextRound());
            System.out.println("Case #" + CASE + ": " + interest);
        }
    }
    public static void main(String[] args){
        new Solution();
    }
}