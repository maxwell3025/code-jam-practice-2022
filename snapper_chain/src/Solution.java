import java.util.Scanner;

public class Solution{
    Scanner in;
    int CASE_NUM;
    public Solution(){
        in = new Scanner(System.in);
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            int count = in.nextInt();
            int snaps = in.nextInt();
            int cycleLength = 1;
            while(cycleLength <= count){
                cycleLength*=2;
            }
            boolean[] snappers = new boolean[cycleLength];
            snaps = snaps%cycleLength;
            for(int i = 0;i<snaps;i++){
                boolean pres = true;
                for(int snapper = 0; snapper<cycleLength;snapper++){
                    boolean temp = snappers[snapper];
                    snappers[snapper] = pres^temp;
                    pres = temp;
                }
            }
            boolean powerThrough = true;
            for(int i = 0;i<count;i++){
                powerThrough &= snappers[i];
            }
            if(powerThrough){
                System.out.println("Case #" + CASE + ": ON");
            }else{
                System.out.println("Case #" + CASE + ": OFF");
            }
        }
    }
    public static void main(String[] args){
        new Solution();
    }
}