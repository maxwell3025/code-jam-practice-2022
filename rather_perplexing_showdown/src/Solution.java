import java.util.Arrays;
import java.util.Scanner;

public class Solution{
    static Scanner in = new Scanner(System.in);
    static int CASE_NUM;
    int N;
    int R;
    int P;
    int S;

    int[] out;
    int pCount;
    int rCount;
    int sCount;
    int[] min_with_win(int winner, int levels){
        if(levels == 0)
            return new int[]{winner};
        int[] subSliceA = min_with_win(winner, levels-1);
        int secondWinner = winner+1;
        if(secondWinner==3)
            secondWinner = 0;
        int[] subSliceB = min_with_win(secondWinner, levels-1);
        boolean aLess = false;
        for(int i = 0;i<subSliceA.length; i++){
            if(subSliceA[i]<subSliceB[i]){
                aLess = true;
                break;
            }
            if(subSliceB[i]<subSliceA[i]){
                aLess = false;
                break;
            }
        }
        int[] both;
        if(aLess){
            both = Arrays.copyOf(subSliceA, subSliceA.length + subSliceB.length);
            System.arraycopy(subSliceB, 0, both, subSliceA.length, subSliceB.length);
        }else{
            both = Arrays.copyOf(subSliceB, subSliceB.length + subSliceA.length);
            System.arraycopy(subSliceA, 0, both, subSliceB.length, subSliceA.length);
        }
        return both;
    }
    boolean recheck(int winner){
        pCount = 0;
        rCount = 0;
        sCount = 0;
        out = min_with_win(winner, N);
        for (int k : out) {
            switch (k) {
                case 0:
                    pCount++;
                    break;
                case 1:
                    rCount++;
                    break;
                case 2:
                    sCount++;
                    break;
            }
        }
        return pCount == P && rCount == R && sCount == S;
    }
    void printOut(){
        for (int j : out) {
            switch (j) {
                case 0:
                    System.out.print("P");
                    break;
                case 1:
                    System.out.print("R");
                    break;
                case 2:
                    System.out.print("S");
                    break;
            }
        }
    }
    public Solution(int CASE){
        N = in.nextInt();
        R = in.nextInt();
        P = in.nextInt();
        S = in.nextInt();

        if(recheck(0)){
            System.out.print("Case #" + CASE + ": ");
            printOut();
            System.out.println();
            return;
        }

        if(recheck(1)){
            System.out.print("Case #" + CASE + ": ");
            printOut();
            System.out.println();
            return;
        }

        if(recheck(2)){
            System.out.print("Case #" + CASE + ": ");
            printOut();
            System.out.println();
            return;
        }
        System.out.println("Case #" + CASE + ": IMPOSSIBLE");
    }
    public static void main(String[] args){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            new Solution(CASE);
        };
    }
}