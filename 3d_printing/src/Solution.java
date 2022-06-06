import java.util.Arrays;
import java.util.Scanner;

public class Solution{
    static Scanner in = new Scanner(System.in);
    int CASE_NUM;
    int[][] inks = new int[4][3];
    int[] colors = new int[4];
    public Solution(){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            for(int printer = 0; printer < 3; printer++){
                for(int color = 0;color < 4; color++){
                    inks[color][printer] = in.nextInt();
                }
            }
            for(int i = 0; i < 4; i++){
                colors[i] = Arrays.stream(inks[i]).min().getAsInt();
            }
            int amtLeft = 1000000;
            for(int i = 0; i < 4; i++){
                int amt = Math.min(amtLeft, colors[i]);
                colors[i] = amt;
                amtLeft -= amt;
            }
            if(amtLeft != 0){
                System.out.println("Case #" + CASE + ": IMPOSSIBLE");
            }else{
                System.out.println(String.format("Case #%d: %d %d %d %d", CASE, colors[0], colors[1], colors[2], colors[3]));
            }
        }
    }
    public static void main(String[] args){
        new Solution();
    }
}