import java.util.Scanner;

public class Solution{
    static Scanner in = new Scanner(System.in);
    int CASE_NUM;
    int[] numbers;
    int cost;
    void reverse(int start, int end){
        cost += end - start + 1;
        int[] newSlice = new int[end - start + 1];
        for(int i = start; i <= end; i++){
            newSlice[end - i] = numbers[i];
        }
        for(int i = start; i <= end; i++){
            numbers[i] = newSlice[i - start];
        }

    }
    public Solution(){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            int N = in.nextInt();
            numbers = new int[N];
            cost = 0;
            for(int i = 0; i < N; i++){
                numbers[i] = in.nextInt();
            }
            for(int i = 0; i < N-1; i++){
                int j = i;
                for(int k = j; k < N; k++){
                    if(numbers[k] < numbers[j]) j = k;
                }
                reverse(i,j);
            }
            System.out.println("Case #" + CASE + ": " + cost);
        }

    }
    public static void main(String[] args){
        new Solution();
    }
}