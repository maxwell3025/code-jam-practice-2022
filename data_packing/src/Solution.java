import java.util.Arrays;
import java.util.Scanner;

public class Solution{
    static Scanner in = new Scanner(System.in);
    static int CASE_NUM;
    int N;
    int X;
    int[] S;
    boolean[] stored;
    public Solution(int CASE){
        N = in.nextInt();
        X = in.nextInt();
        S = new int[N];
        for(int i = 0;i<N;i++){
            S[i] = in.nextInt();
        }
        Arrays.sort(S);
        stored = new boolean[N];
        Arrays.fill(stored, false);
        int discsUsed = 0;
        for(int i = 0; i<N;i++){
            if(stored[i]) continue;
            stored[i] = true;
            int file1 = S[i];
            for(int j = N-1;j>i;j--){
                if(stored[j]) continue;
                if(S[j] + file1 <= X){
                    stored[j] = true;
                    break;
                }
            }
            discsUsed++;
        }
        System.out.println("Case #" + CASE + ": " + discsUsed);
    }
    public static void main(String[] args){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            new Solution(CASE);
        };
    }
}