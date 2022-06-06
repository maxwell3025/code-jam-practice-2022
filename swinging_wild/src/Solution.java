import java.util.Scanner;

public class Solution{
    static Scanner in = new Scanner(System.in);
    static int CASE_NUM;
    int[] d;
    int[] l;
    int N;
    int D;
    public Solution(int CASE){
        N = in.nextInt();
        d = new int[N];
        l = new int[N];
        for(int i = 0;i<N;i++){
            d[i] = in.nextInt();
            l[i] = in.nextInt();
        }
        D = in.nextInt();
        l[0] = d[0];
        for(int i = 1; i < N; i++){
            int vineLength = l[i];
            int lowReach = 0;
            for(int j = 0; j < i; j++){
                if(l[j] >= d[i] - d[j]){
                    lowReach = d[i] - d[j];
                    break;
                }
            }
            if(lowReach < vineLength){
                l[i] = lowReach;
            }
        }
        boolean solvable = false;
        for(int i = 0;i<N;i++){
            if(l[i]+d[i] >= D){
                solvable = true;
                break;
            }
        }
        System.out.println("Case #" + CASE + ": " + (solvable?"YES":"NO"));
    }
    public static void main(String[] args){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            new Solution(CASE);
        };
    }
}