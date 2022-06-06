import java.util.Scanner;

public class Solution{
    static Scanner in = new Scanner(System.in);
    static int CASE_NUM;
    int N;
    long P;
    long twoN;
    //NOTE: losses are 1 and wins are 0 since 0 makes it lower and you want a low rank
    long worstCase(long x){
        long record = 0;
        long a = x + 1;
        for(int i = 0;i<N;i++){
            record = record<<1;
            if(a != 1){
                record++;
                a = a/2;
            }
        }
        return record;
    }
    long bestCase(long x){
        long record = 0;
        long a = twoN-x;
        for(int i = 0;i<N;i++){
            record = record<<1;
            if(a != 1){
                a = a/2;
            }else{
                record++;
            }
        }
        return record;
    }
    public Solution(int CASE){
        N = in.nextInt();
        P = in.nextLong();
        twoN = 1L<<N;
        System.out.print("Case #" + CASE + ":");

        long low = 0;
        long high = twoN;
        while(high != low + 1){
            long mid = (low+high)/2L;
            if(worstCase(mid)<P)
                low = mid;
            else
                high = mid;
        }
        System.out.print(" " + low);

        low = 0;
        high = twoN;
        while(high != low + 1){
            long mid = (low+high)/2L;
            if(bestCase(mid)<P)
                low = mid;
            else
                high = mid;
        }
        System.out.println(" " + low);
    }
    public static void main(String[] args){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            new Solution(CASE);
        };
    }
}