import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution{
    static Scanner in = new Scanner(System.in);
    int CASE_NUM;
    public Solution(){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            int N = in.nextInt();
            PriorityQueue<Double> naomi = new PriorityQueue<>(0, (Double a, Double b) -> {return (int)Math.signum(b-a);});
            PriorityQueue<Double> ken = new PriorityQueue<>(0, (Double a, Double b) -> {return (int)Math.signum(b-a);});
            boolean[] naomisBlock = new boolean[N*2];
            for(int i = 0; i<N; i++){
                naomi.add(in.nextDouble());
            }
            for(int i = 0; i<N; i++){
                ken.add(in.nextDouble());
            }
            for(int i = 0; i<N*2; i++){
                if(naomi.peek() > ken.peek()){
                    naomisBlock[i] = false;
                }else{
                    naomi
                }
            }
            System.out.println("Case #" + CASE + ": ");
        }
    }
    public static void main(String[] args){
        new Solution();
    }
}