import java.math.BigInteger;
import java.util.Scanner;

public class Solution{
    static Scanner in = new Scanner(System.in);
    int CASE_NUM;

    private boolean isFair(BigInteger x) {
        String decimal = x.toString();
        for(int i = 0; i < decimal.length(); i++){
            if(decimal.charAt(i) != decimal.charAt(decimal.length() - i - 1)){
                return false;
            }
        }
        return true;
    }

    public Solution(){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            BigInteger A = in.nextBigInteger();
            BigInteger B = in.nextBigInteger();
            BigInteger aRoot = A.sqrt();
            if(!aRoot.multiply(aRoot).equals(A)){
                aRoot = aRoot.add(BigInteger.ONE);
            }
            BigInteger bRoot = B.sqrt();
            BigInteger solutions = BigInteger.ZERO;
            for(BigInteger i = aRoot; i.compareTo(bRoot) <= 0; i = i.add(BigInteger.ONE)){
                if(isFair(i.multiply(i)) && isFair(i)){
                    solutions = solutions.add(BigInteger.ONE);
                }
            }
            System.out.println("Case #" + CASE + ": " + solutions);
        }
    }
    public static void main(String[] args){
        new Solution();
    }
}