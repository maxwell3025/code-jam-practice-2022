import java.util.Scanner;

public class Solution{
    static Scanner in = new Scanner(System.in);
    int CASE_NUM;
    public Solution(){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            double farmCost = in.nextDouble();
            double farmRate = in.nextDouble();
            double goal = in.nextDouble();
            double rate = 2;
            double time = 0;
            while(true){
                //you now have 0
                double winTime = time + goal/rate;
                double farmPurchaseTime = time + farmCost/rate;
                double newWinTime = farmPurchaseTime + goal/(rate + farmRate);
                if(newWinTime < winTime){
                    time = farmPurchaseTime;
                    rate += farmRate;
                }else{
                    time = winTime;
                    break;
                }
            }
            System.out.println("Case #" + CASE + String.format(": %.7f", time));
        }
    }
    public static void main(String[] args){
        new Solution();
    }
}