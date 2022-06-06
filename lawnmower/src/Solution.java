import java.awt.*;
import java.util.Scanner;

public class Solution{
    static Scanner in = new Scanner(System.in);
    int CASE_NUM;
    public Solution(){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            int H = in.nextInt();
            int W = in.nextInt();
            int[][] heights = new int[W][H];
            for(int y = 0; y < H; y++){
                for(int x = 0; x < W; x++) {
                    heights[x][y] = in.nextInt();
                }
            }
            boolean possible = true;
            for(int y = 0; y < H; y++){
                for(int x = 0; x < W; x++) {
                    int currentHeight = heights[x][y];
                    boolean verticalWell = false;
                    boolean horizontalWell = false;
                    for(int i = 0; i < W; i++){
                        horizontalWell |= heights[i][y] > currentHeight;
                    }
                    for(int i = 0; i < H; i++){
                        verticalWell |= heights[x][i] > currentHeight;
                    }
                    if(verticalWell && horizontalWell){
                        possible = false;
                    }
                }
            }
            System.out.println("Case #" + CASE + ": " + (possible?"YES":"NO"));
        }
    }
    public static void main(String[] args){
        new Solution();
    }
}