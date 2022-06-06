import java.util.Scanner;

public class Solution{
    static Scanner in = new Scanner(System.in);
    static int CASE_NUM;
    int W;
    int H;
    int A;
    public Solution(int CASE){
        W = in.nextInt();
        H = in.nextInt();
        A = in.nextInt();

        if(A>W*H){
            System.out.println("Case #" + CASE + ": " + "IMPOSSIBLE");
            return;
        }
        int x1 = W;
        int y2 = A/W;
        if(A%W != 0)
            y2++;
        int a = W*y2;
        int residual = a-A;
        int y1 = 1;
        int x2 = residual;
        System.out.println("Case #" + CASE + ": 0 0 " + x1 + " " + y1 + " " + x2 + " " + y2);
    }
    public static void main(String[] args){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            new Solution(CASE);
        };
    }
}