import java.util.Scanner;

public class Solution{
    static Scanner in = new Scanner(System.in);
    int CASE_NUM;
    int[] numbers;
    int N;
    public int tenP(int x){
        int out = 1;
        for(int i = 0;i < x; i++){
            out *= 10;
        }
        return out;
    }
    public int digits(int x){
        int digits = 0;
        while(x != 0){
            x/=10;
            digits++;
        }
        return digits;
    }
    public int pad0(int x, int digits){
        if(digits(x) < digits)
        return x * tenP(digits-digits(x));
        else return x;
    }
    public int pad9(int x, int digits){
        if(digits(x) < digits)
        return (x+1) * tenP(digits-digits(x))-1;
        else return x;
    }
    public Solution(){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            N = in.nextInt();
            numbers = new int[N];
            for(int i = 0;i<N;i++){
                numbers[i] = in.nextInt();
            }
            int operations = 0;
            for(int i = 1; i<N; i++){
                int prev = numbers[i-1];
                if(prev < numbers[i]){
                    continue;
                }else if(prev < pad9(numbers[i], digits(prev))){
                    operations += digits(prev) - digits(numbers[i]);
                    numbers[i] = pad0(numbers[i], digits(prev));
                    if(numbers[i]<=prev){
                        numbers[i] = prev + 1;
                    }
                }else{
                    operations += digits(prev) + 1 - digits(numbers[i]);
                    numbers[i] = pad0(numbers[i], digits(prev) + 1);
                }
            }
            System.out.println("Case #" + CASE + ": " + operations);
        }
    }
    public static void main(String[] args){
        new Solution();
    }
}