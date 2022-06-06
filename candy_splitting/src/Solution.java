import java.util.Scanner;

public class Solution{
    Scanner in;
    int CASE_NUM;
    public Solution(){
        in = new Scanner(System.in);
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            int N = in.nextInt();
            int[] candies = new int[N];
            for(int i = 0;i<N;i++){
                candies[i] = in.nextInt();
            }
            int min = candies[0];
            int total = 0;
            int xorTot = 0;
            for(int i = 0; i<N;i++){
                xorTot ^= candies[i];
                total += candies[i];
                if(candies[i]<min) min = candies[i];
            }
            if(xorTot == 0){
            System.out.println("Case #"+CASE+": "+(total-min));
            }else{
                System.out.println("Case #"+CASE+": NO");
            }
        }
    }
    public static void main(String[] args){
        new Solution();
    }
}