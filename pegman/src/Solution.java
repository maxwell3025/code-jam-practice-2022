import java.util.Scanner;
enum Tile{
    CROSS,
    UP,
    DOWN,
    LEFT,
    RIGHT
}
public class Solution{
    static Scanner in = new Scanner(System.in);
    static int CASE_NUM;
    int W;
    int H;
    Tile[][] board;

    boolean[][][] banned;
    public Solution(int CASE){
        H = in.nextInt();
        W = in.nextInt();
        board = new Tile[W][H];
        for(int row = 0;row<H;row++){
            char[] line = in.next().toCharArray();
            for(int col = 0;col<W;col++){
                char token = line[col];
                Tile value = Tile.CROSS;
                switch (token){
                    case '^':
                        value = Tile.UP;
                        break;
                    case 'v':
                        value = Tile.DOWN;
                        break;
                    case '<':
                        value = Tile.LEFT;
                        break;
                    case '>':
                        value = Tile.RIGHT;
                        break;
                }
                board[col][row] = value;
            }
        }

        banned = new boolean[W][H][4];
        boolean impossible = false;
        //0 is up
        //1 is down
        //2 is left
        //3 is right
        xLoop: for(int x0 = 0;x0<W; x0++){
            int x = x0;
            int y = 0;
            while(board[x][y] == Tile.CROSS){
                y++;
                if(y==H) continue xLoop;
            }
            banned[x][y][0] = true;
            x = x0;
            y = H-1;
            while(board[x][y] == Tile.CROSS){
                y--;
            }
            banned[x][y][1] = true;
        }
        yLoop: for(int y0 = 0;y0<H;y0++){
            int x = 0;
            int y = y0;
            while (board[x][y] == Tile.CROSS){
                x++;
                if(x==W) continue yLoop;
            }
            banned[x][y][2] = true;
            x = W-1;
            y = y0;
            while(board[x][y] == Tile.CROSS){
                x--;
            }
            banned[x][y][3] = true;
            if(banned[x][y][0]&&banned[x][y][1]&&banned[x][y][2]&&banned[x][y][3]) impossible = true;
        }
        if(impossible){
            System.out.println("Case #" + CASE + ": " + "IMPOSSIBLE");
            return;
        }
        int changes = 0;
        for(int x = 0;x<W;x++){
            for(int y = 0;y<H;y++) {
                switch (board[x][y]){
                    case CROSS:
                        break;
                    case UP:
                        if(banned[x][y][0]){
                            changes++;
                        }
                        break;
                    case DOWN:
                        if(banned[x][y][1]){
                            changes++;
                        }
                        break;
                    case LEFT:
                        if(banned[x][y][2]){
                            changes++;
                        }
                        break;
                    case RIGHT:
                        if(banned[x][y][3]){
                            changes++;
                        }
                        break;
                }
            }
        }
        System.out.println("Case #" + CASE + ": " + changes);
    }
    public static void main(String[] args){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            new Solution(CASE);
        };
    }
}