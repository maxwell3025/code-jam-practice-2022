import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution{
    static Scanner in = new Scanner(System.in);
    int CASE_NUM;
    static int[] stdOut = new int[100];
    int[] total;
    public Solution(){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            in.nextInt();
            total = new int[200];
            for(int i = 0;i<100;i++){
                total[i] = stdOut[i];
                if (i != 0) System.out.print(" ");
                System.out.print(stdOut[i]);
            }
            System.out.println();

            for(int i = 0;i<100;i++){
                total[i + 100] = in.nextInt();
            }
            long sum = 0;
            Arrays.sort(total);
            for(int element: total){
                sum += element;
            }
            sum/=2;
            List<Integer> glob = new ArrayList<>();
            for(int i = 199; i>=0; i--){
                if(total[i] <= sum){
                    sum -= total[i];
                    glob.add(total[i]);
                }
            }
            for(int i = 0;i< glob.size(); i++){
                if(i != 0) System.out.print(" ");
                System.out.print(glob.get(i));
            }
            System.out.println();

        }
    }
    public static void main(String[] args){
        int total = 1;
        for(int i = 0;i<100;i++){
            stdOut[i] = total;
            total*=2;
            if(total > 1000000000) break;
        }
        total = 1000000000;
        for(int i = 99; i >= 0; i--){
            if(stdOut[i] == 0) stdOut[i] = total;
            total--;
        }
        new Solution();
    }
}