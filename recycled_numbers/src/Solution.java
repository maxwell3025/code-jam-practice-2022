import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution{
    static Scanner in = new Scanner(System.in);
    int CASE_NUM;
    public int flip(int x, int endDigits, int digits){
        int endPTen = 1;
        int digitPTen = 1;
        for(int i = 0; i < endDigits; i++){
            endPTen *= 10;
        }
        for(int i = 0; i < digits - endDigits; i++){
            digitPTen *= 10;
        }
        int end = (x % endPTen);
        int front = x / endPTen;
        if(end < endPTen / 10){
            return -1;
        }else{
            return end * digitPTen + front;
        }
    }
    public Solution(){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            int a = in.nextInt();
            int b = in.nextInt();
            int numLength = Integer.toString(a).length();
            int solutions = 0;
            for(int n = a; n <= b; n++){
                Set<Integer> prevDests = new HashSet<>();
                for(int digits = 1; digits < numLength; digits++){
                    int m = flip(n,digits, numLength);
                    if(n < m && m <= b && !prevDests.contains(m)){
                        prevDests.add(m);
                        solutions++;
                    }
                }
            }
            System.out.println("Case #" + CASE + ": " + solutions);
        }
    }
    public static void main(String[] args){
        new Solution();
    }
}