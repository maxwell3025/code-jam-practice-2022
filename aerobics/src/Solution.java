
import java.util.*;

public class Solution{
    static Scanner in = new Scanner(System.in);
    static int CASE_NUM;
    int N;
    int W;
    int H;
    int[] r;
    int[] x;
    int[] y;
    public Solution(int CASE){
        N = in.nextInt();
        W = in.nextInt();
        H = in.nextInt();
        r = new int[N];
        x = new int[N];
        y = new int[N];
        for(int i = 0;i<N;i++){
            r[i] = in.nextInt();
        }
        int xCur = W;
        for(int i = 0; i < N; i++){
            xCur += r[i];
            if(xCur > W)
                xCur = 0;
            int yCur = 0;
            for(int j = 0; j < i; j++){
                int rad = r[i] + r[j];
                int hDist = Math.abs(xCur-x[j]);
                if(hDist<rad){
                    yCur = Math.max(yCur, y[j]+rad);
                }
            }
            x[i] = xCur;
            y[i] = yCur;
            xCur += r[i];
        }
        System.out.print("Case #" + CASE + ":");
        for(int i = 0;i<N;i++){
            System.out.print(" " + x[i]);
            System.out.print(" " + y[i]);
        }
        System.out.println();
    }
    public static void main(String[] args){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            new Solution(CASE);
        };
    }
}