import java.util.Scanner;
enum Color{
    BLUE, ORANGE
}
public class Solution{
    public static Scanner in;
    int bluePos = 1;
    int oranPos = 1;
    Color[] colors;
    int[] buttons;
    int N;
    int time = 0;
    public Solution(int CASE){
        N = in.nextInt();
        colors = new Color[N];
        buttons = new int[N];

        for(int i = 0; i<N; i++){
            colors[i] = in.next().equals("O")?Color.ORANGE:Color.BLUE;
            buttons[i] = in.nextInt();
        }
        for(int i = 0; i<N; i++){
            int blueTask = -1;
            int oranTask = -1;
            for(int j = i; (blueTask == -1 || oranTask == -1)&&j<N; j++){
                if(colors[j]==Color.BLUE) blueTask = buttons[j];
                if(colors[j]==Color.ORANGE) oranTask = buttons[j];
            }
            if(colors[i] == Color.BLUE){
                int timePassed = Math.abs(bluePos-buttons[i])+1;
                time += timePassed;
                bluePos = buttons[i];
                if(Math.abs(oranPos -oranTask)<=timePassed){
                    oranPos = oranTask;
                }else if(oranTask< oranPos){
                    oranPos -= timePassed;
                }else{
                    oranPos += timePassed;
                }
            } else {
                int timePassed = Math.abs(oranPos - buttons[i]) + 1;
                time += timePassed;
                oranPos = buttons[i];
                if (Math.abs(bluePos - blueTask) <= timePassed) {
                    bluePos = blueTask;
                } else if (blueTask < bluePos) {
                    bluePos -= timePassed;
                } else {
                    bluePos += timePassed;
                }
            }
        }
        System.out.printf("case #%d: %d\n", CASE, time);
    }
    public static void main(String[] args){
        in = new Scanner(System.in);
        int CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            new Solution(CASE);
        }
    }
}