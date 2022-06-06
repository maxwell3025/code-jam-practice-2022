import java.util.Scanner;

public class Solution{
    static Scanner in = new Scanner(System.in);
    int CASE_NUM;
    public int GCD(int a, int b) {
        if (b==0) return a;
        return GCD(b,a%b);
    }
    public Solution(){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            int W = in.nextInt();
            int H = in.nextInt();
            int D = in.nextInt();
            boolean[][] maze = new boolean[W][H];
            int xStart = 0;
            int yStart = 0;
            for(int y = 0;y<H;y++){
                String line = in.next();
                for(int x = 0;x<W;x++){
                    if(line.charAt(x) == '#'){
                        maze[x][y] = true;
                    }else if(line.charAt(x) == 'x'){
                        xStart = x;
                        yStart = y;
                    }else{
                        maze[x][y] = false;
                    }
                }
            }
            int copies = 4;
            for(int dx = -D; dx <= D; dx++){
                for(int dy = -D; dy <= D; dy++){
                    if(dx!=0 && dy!=0 && GCD(dx, dy) == 1){
                        double x = xStart + 0.5;
                        double y = yStart + 0.5;
                        double xStep = Math.hypot(1,1.*dy/dx);
                        double yStep = Math.hypot(1.*dx/dy,1);
                        double xLight = dx/Math.hypot(dx,dy);
                        double yLight = dy/Math.hypot(dx,dy);
                        double xDist = xStep * .5;
                        double yDist = yStep * .5;
                        double totalDist = 0;
                        boolean corner = false;
                        for(;;){
                            //step forward
                            if(xDist == yDist){
                                corner = true;
                                x += xLight * xDist;
                                y += yLight * yDist;
                                xDist = xStep;
                                yDist = yStep;
                                totalDist += xDist;
                            }else if(xDist < yDist){
                                corner = false;
                                x += xLight * xDist;
                                y += yLight * xDist;
                                xDist = xStep;
                                yDist -= xDist;
                                totalDist += xDist;
                            }else{
                                corner = false;
                                x += xLight * yDist;
                                y += yLight * yDist;
                                yDist = yStep;
                                xDist -= yDist;
                                totalDist += yDist;
                            }
                            if(totalDist > D){
                                break;
                            }
                            if(corner){
                                boolean topLeft = maze[(int)x-1][(int)y-1];
                                boolean botLeft = maze[(int)x-1][(int)y];
                                boolean topRight = maze[(int)x][(int)y-1];
                                boolean botRight = maze[(int)x][(int)y];
                                //vertical plane
                                if(
                                        (topLeft && botLeft && !topRight && !botRight)||
                                        (!topLeft && !botLeft && topRight && botRight)
                                ){
                                    xLight *= -1;
                                }
                                //horizontal plane
                                else if(
                                        (topLeft && !botLeft && topRight && !botRight)||
                                        (!topLeft && botLeft && !topRight && botRight)
                                ){
                                    yLight *= -1;
                                }
                                //external corner
                                else if(
                                        (topLeft && !botLeft && !topRight && !botRight)||
                                        (!topLeft && botLeft && !topRight && !botRight)||
                                        (!topLeft && !botLeft && topRight && !botRight)||
                                        (!topLeft && !botLeft && !topRight && botRight)
                                ){
                                    break;
                                }
                                //internal corner
                                else if(
                                        (!topLeft && botLeft && topRight && botRight)||
                                        (topLeft && !botLeft && topRight && botRight)||
                                        (topLeft && botLeft && !topRight && botRight)||
                                        (topLeft && botLeft && topRight && !botRight)
                                ){
                                    xLight *= -1;
                                    yLight *= -1;
                                }
                                //hole
                            }else{

                            }
                        }
                    }
                }
            }
        }
    }
    public static void main(String[] args){
        new Solution();
    }
}