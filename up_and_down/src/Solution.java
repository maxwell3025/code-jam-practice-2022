import java.util.*;

public class Solution{
    static Scanner in = new Scanner(System.in);
    static int CASE_NUM;
    int N;
    List<Integer> A = new ArrayList();
    public Solution(int CASE){
        N = in.nextInt();
        for(int i = 0;i<N;i++){
            A.add(in.nextInt());
        }
        int totalSwaps = 0;
        for(int i = 0; i < N; i++){
            int minIndex = A.indexOf(Collections.min(A));
            int left = 0;
            int right = A.size()-1;
            totalSwaps += Math.min(minIndex-left, right-minIndex);
            A.remove(minIndex);
        }
        System.out.println("Case #" + CASE + ": " + totalSwaps);
    }
    public static void main(String[] args){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            new Solution(CASE);
        };
    }
}