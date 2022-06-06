import java.util.Arrays;
import java.util.Scanner;

public class Solution{
    static Scanner in = new Scanner(System.in);
    int CASE_NUM;
    int[][] exercises;
    int E;
    int W;
    int[][] minPath;
    int[][][] baseLine;
    int minPath(int start, int end){
        if(minPath[start][end] != -1) return minPath[start][end];
        int curMin = Integer.MAX_VALUE;
        for(int i = start; i < end; i++){
            //i is the end of the first interval
            int pathA = minPath(start, i);
            int pathB = minPath(i+1, end);
            int[] baseA = baseLine[start][i];
            int[] baseB = baseLine[i+1][end];
            int[] thisBase = new int[W];
            int baseSize = 0;
            for(int j = 0;j<W;j++){
                thisBase[j] = Math.min(baseA[j], baseB[j]);
                baseSize += thisBase[j];
            }
            int pathSplit = pathA + pathB - baseSize * 2;
            if(pathSplit < curMin){
                baseLine[start][end] = thisBase;
                curMin = pathSplit;
            }
        }
        minPath[start][end] = curMin;
        return minPath[start][end];
    }
    public Solution(){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            E = in.nextInt();
            W = in.nextInt();
            exercises = new int[E][W];
            minPath = new int[E][E];
            baseLine = new int[E][E][];

            for(int i = 0; i < E; i++){
                for(int j = 0;j < E; j++){
                    minPath[i][j] = -1;
                }
            }

            for(int i = 0; i < E; i++){
                for(int j = 0;j < W; j++){
                    exercises[i][j] = in.nextInt();
                }
            }
            for(int i = 0; i < E; i++){
                minPath[i][i] = 0;
                baseLine[i][i] = new int[W];
                for(int j = 0;j < W; j++){
                    minPath[i][i] += exercises[i][j] * 2;
                    baseLine[i][i][j] = exercises[i][j];
                }
            }
            System.out.println("Case #" + CASE + ": " + minPath(0, E-1));
        }
    }
    public static void main(String[] args){
        new Solution();
    }
}