import java.util.Arrays;
import java.util.Scanner;

public class Solution{
    static Scanner in = new Scanner(System.in);
    int CASE_NUM;
    public Solution(){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            int N = in.nextInt();
            int[] dice = new int[N];
            for(int i = 0;i < N; i++){
                dice[i] = in.nextInt();
            }
            Arrays.sort(dice);
            int currentHighest = 0;
            for(int i = 0; i < N; i++){
                if(dice[i] > currentHighest){
                    currentHighest++;
                }
            }
            System.out.println("Case #" + CASE + ": " + currentHighest);
        }
    }
    public static void main(String[] args){
        new Solution();
    }
}