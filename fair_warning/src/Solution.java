import java.awt.*;
import java.math.BigInteger;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution{
    Scanner in;
    int CASE_NUM;
    public Solution(){
        in = new Scanner(System.in);
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            int N = in.nextInt();
            PriorityQueue<BigInteger> events = new PriorityQueue<>();
            for(int i = 0; i<N; i++){
                events.add(new BigInteger(in.next()).negate());
            }
            BigInteger divisor;
            BigInteger recent;
            {
                BigInteger first = events.poll();
                BigInteger second = events.poll();
                divisor = second.subtract(first);
                recent = second;
            }
            while(!events.isEmpty()){
                divisor = divisor.gcd(events.peek().subtract(recent));
                recent = events.poll();
            }
            recent = recent.negate();
            BigInteger rem = recent.divideAndRemainder(divisor)[1];
            BigInteger solution = new BigInteger("0");
            if(!rem.equals(BigInteger.ZERO)){
                solution = divisor.subtract(rem);
            }
            System.out.println("Case #" + CASE + ": " + solution);
        }
    }
    public static void main(String[] args){
        new Solution();
    }
}