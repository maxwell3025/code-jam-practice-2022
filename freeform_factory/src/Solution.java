import java.util.Scanner;

public class Solution{
    static Scanner in = new Scanner(System.in);
    static int CASE_NUM;
    int N;
    boolean[][] known; //[worker][machine]

    boolean[][] tried; //[worker][machine]

    public Solution(int CASE){
        N = in.nextInt();
        known = new boolean[N][N];
        for(int row = 0;row<N;row++){
            char[] data = in.next().toCharArray();
            for(int col = 0;col<N;col++){
                known[row][col] = data[col] == '1';
            }
        }

        System.out.println("Case #" + CASE + ": " + "TODO: send output");
    }
    public static void main(String[] args){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            new Solution(CASE);
        };
    }
}