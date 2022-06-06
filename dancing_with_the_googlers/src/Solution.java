import java.util.Scanner;

public class Solution{
    static Scanner in = new Scanner(System.in);
    int CASE_NUM;
    public Solution(){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            int N = in.nextInt();
            int nSup = in.nextInt();
            int goal = in.nextInt();
            int normTot = goal * 3 - 2;
            int supTot = goal * 3 - 4;
            int winners = 0;
            for(int i = 0;i<N;i++){
                int contestantTotal = in.nextInt();
                if(contestantTotal < goal){
                    continue;
                }
                if(contestantTotal >= normTot){
                    winners++;
                }else if(contestantTotal >= supTot && nSup != 0){
                    winners++;
                    nSup--;
                }
            }
            System.out.println("Case #" + CASE + ": " + winners);
        }
    }
    public static void main(String[] args){
        new Solution();
    }
}