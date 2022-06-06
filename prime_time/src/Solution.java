import java.util.Scanner;

public class Solution{
    static Scanner in = new Scanner(System.in);
    int CASE_NUM;
    int N;
    int[] primes;
    long[] cards;
    public boolean decomposable(long l){
        if(l == 0) return false;
        for(int i = 0; i < N; i++){
            int factors = 0;
            while(l%primes[i] == 0){
                factors++;
                l/=primes[i];
            }
            if(factors > cards[i]){
                return false;
            }
        }
        return l == 1;
    }
    public int decompsum(long l){
        int out = 0;
        for(int i = 0; i < N; i++){
            while(l % primes[i] == 0){
                l /= primes[i];
                out += primes[i];
            }
        }
        return out;
    }
    public Solution(){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            N = in.nextInt();
            primes = new int[N];
            cards = new long[N];
            long sum = 0;
            for(int i = 0;i<N;i++){
                primes[i] = in.nextInt();
                cards[i] = in.nextLong();
                sum += primes[i]*cards[i];
            }
            long score = sum;
            while(score != 0){
                score--;
                if(!decomposable(score))continue;
                if(decompsum(score) + score == sum)break;
            }
            System.out.println("Case #" + CASE + ": " + score);
        }
    }
    public static void main(String[] args){
        new Solution();
    }
}