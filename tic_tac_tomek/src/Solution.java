import java.util.Scanner;

public class Solution{
    static Scanner in = new Scanner(System.in);
    int CASE_NUM;
    public Solution(){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            boolean[][] O = new boolean[4][4];
            boolean[][] X = new boolean[4][4];
            boolean filled = true;
            for(int y = 0; y < 4; y++){
                String line = in.next();
                for(int x = 0;x<4;x++){
                    char nextChar = line.charAt(x);
                    switch(nextChar){
                        case 'O':
                            O[x][y] = true;
                            break;
                        case 'X':
                            X[x][y] = true;
                            break;
                        case 'T':
                            O[x][y] = true;
                            X[x][y] = true;
                            break;
                        default:
                            filled = false;
                    }
                }
            }
            boolean xWin = false;
            boolean oWin = false;
            xWin |= X[0][0] && X[1][0] && X[2][0] && X[3][0];
            xWin |= X[0][1] && X[1][1] && X[2][1] && X[3][1];
            xWin |= X[0][2] && X[1][2] && X[2][2] && X[3][2];
            xWin |= X[0][3] && X[1][3] && X[2][3] && X[3][3];
            xWin |= X[0][0] && X[0][1] && X[0][2] && X[0][3];
            xWin |= X[1][0] && X[1][1] && X[1][2] && X[1][3];
            xWin |= X[2][0] && X[2][1] && X[2][2] && X[2][3];
            xWin |= X[3][0] && X[3][1] && X[3][2] && X[3][3];
            xWin |= X[0][0] && X[1][1] && X[2][2] && X[3][3];
            xWin |= X[3][0] && X[2][1] && X[1][2] && X[0][3];

            oWin |= O[0][0] && O[1][0] && O[2][0] && O[3][0];
            oWin |= O[0][1] && O[1][1] && O[2][1] && O[3][1];
            oWin |= O[0][2] && O[1][2] && O[2][2] && O[3][2];
            oWin |= O[0][3] && O[1][3] && O[2][3] && O[3][3];
            oWin |= O[0][0] && O[0][1] && O[0][2] && O[0][3];
            oWin |= O[1][0] && O[1][1] && O[1][2] && O[1][3];
            oWin |= O[2][0] && O[2][1] && O[2][2] && O[2][3];
            oWin |= O[3][0] && O[3][1] && O[3][2] && O[3][3];
            oWin |= O[0][0] && O[1][1] && O[2][2] && O[3][3];
            oWin |= O[3][0] && O[2][1] && O[1][2] && O[0][3];

            if (xWin) {
                System.out.println("Case #" + CASE + ": X Won");
            }else if(oWin){
                System.out.println("Case #" + CASE + ": O Won");
            }else if(filled){
                System.out.println("Case #" + CASE + ": Draw");
            }else{
                System.out.println("Case #" + CASE + ": Game has not completed");
            }
        }
    }
    public static void main(String[] args){
        new Solution();
    }
}