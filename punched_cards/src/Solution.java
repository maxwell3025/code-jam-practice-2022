import java.util.Scanner;

public class Solution{
    static Scanner in = new Scanner(System.in);
    int CASE_NUM;
    public Solution(){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            int H = in.nextInt();
            int W = in.nextInt();
            System.out.println("Case #" + CASE + ":");
            for(int col = 0; col < W; col++){
                if(col == 0){
                    System.out.print("..");
                }else{
                    System.out.print("+-");
                }
            }
            System.out.print("+");
            System.out.println();
            for(int row = 0; row < H; row++){
                for(int col = 0; col < W; col++){
                    if(col == 0 && row == 0){
                        System.out.print("..");
                    }else{
                        System.out.print("|.");
                    }
                }
                System.out.print("|");
                System.out.println();
                for(int col = 0; col < W; col++){
                        System.out.print("+-");
                }
                System.out.print("+");
                System.out.println();
            }
        }
    }
    public static void main(String[] args){
        new Solution();
    }
}