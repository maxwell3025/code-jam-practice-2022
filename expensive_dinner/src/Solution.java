import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution{
    static Scanner in = new Scanner(System.in);
    static int CASE_NUM;
    long N;
    public Solution(int CASE){
        N = in.nextLong();
        int limit = (int)Math.sqrt(N);
        List<Integer> primes = new ArrayList<>();
        if(N == 1){
            System.out.println("Case #" + CASE + ": " + 0);
            return;
        }
        int total = 1;

        boolean[] isPrime = new boolean[limit + 1];
        Arrays.fill(isPrime, true);
        int sieveLimit = (int)Math.sqrt(limit);
        for(int i = 2; i <= sieveLimit; i++){
            if(isPrime[i]){
                for(int j = i*2; j <= limit; j += i){
                    isPrime[j] = false;
                }
            }
        }
        for(int i = 2; i <= limit; i++){
            if(isPrime[i]){
                for(long pow = (long)i*i; pow <= N; pow*=i){
                    total++;
                }
            }
        }
        System.out.println("Case #" + CASE + ": " + total);
    }
    public static void main(String[] args){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            new Solution(CASE);
        };
    }
}