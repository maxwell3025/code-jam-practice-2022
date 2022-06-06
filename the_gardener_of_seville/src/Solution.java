import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum Hedge{
    TOP_LEFT,
    TOP_RIGHT,
}
public class Solution{
    static Scanner in = new Scanner(System.in);
    static int CASE_NUM;
    int R;
    int C;
    int[] listEdge;

    int N;
    Hedge[][] garden;
    class Turtle{
        int r;
        int c;
        int direction;//0 is bottom right
        void left(){
            direction++;
            direction %= 4;
        }
        void right(){
            left();
            left();
            left();
        }
        boolean forward(){
            switch(direction){
                case 0:
                    if(r == R || c == C || garden[r][c] == Hedge.TOP_RIGHT)
                        return false;
                    garden[r][c] = Hedge.TOP_LEFT;
                    r++;
                    c++;
                    return true;
                case 1:
                    if(r == R || c == 0 || garden[r][c-1] == Hedge.TOP_LEFT)
                        return false;
                    garden[r][c-1] = Hedge.TOP_RIGHT;
                    r++;
                    c--;
                    return true;
                case 2:
                    if(r == 0 || c == 0 || garden[r-1][c-1] == Hedge.TOP_RIGHT)
                        return false;
                    garden[r-1][c-1] = Hedge.TOP_LEFT;
                    r--;
                    c--;
                    return true;
                case 3:
                    if(r == 0 || c == C || garden[r-1][c] == Hedge.TOP_LEFT)
                        return false;
                    garden[r-1][c] = Hedge.TOP_RIGHT;
                    r--;
                    c++;
                    return true;
                default:
                    return false;
            }
        }
        boolean atEdge(){
            return r==0||c==0||r==R||c==C;
        }
        boolean traverse(int end){
            do{
                int attempts = 0;
                while(!forward() && attempts != 4){
                    attempts++;
                    right();
                }
                if(attempts == 4)
                    return false;
                left();
            } while(!atEdge());
            Turtle test = new Turtle((end+1)%N);
            return this.r == test.r && this.c == test.c;
        }
        Turtle(int start){
            if(start<N/2){
                if(start<C){
                    c = start;
                    r = 0;
                    direction = 0;
                }else{
                    c = C;
                    r = start-C;
                    direction = 1;
                }
            }else{
                if(start-N/2<C){
                    c = C-(start-N/2);
                    r = R;
                    direction = 2;
                }else{
                    c = 0;
                    r = R-(start-N/2-C);
                    direction = 3;
                }
            }
        }
    }
    class Section{
        int start;
        int end;
        List<Section> listSubSects = new ArrayList<>();
        public Section(int start, int end){
            this.start = start;
            this.end = end;
            int currentIndex = (start+1)%N;
            while(currentIndex != end){
                listSubSects.add(new Section(currentIndex, listEdge[currentIndex]));
                currentIndex = (listEdge[currentIndex] + 1)%N;
            }
        }
        boolean render(){
            for(Section subSection: listSubSects){
                if(!subSection.render())
                    return false;
            }
            return new Turtle(start).traverse(end);
        }
    }

    public Solution(int CASE){
        R = in.nextInt();
        C = in.nextInt();
        N = R*2+C*2;
        listEdge = new int[N];
        for (int i = 0;i<N/2;i++){
            int a = in.nextInt()-1;
            int b = in.nextInt()-1;
            listEdge[a] = b;
            listEdge[b] = a;
        }
        garden = new Hedge[R][C];

        for(int i = 0; i<N;i++){
            int start = i;
            int end = listEdge[i];
            for(int j = start+1; j < end; j++){
                if(listEdge[j] < start || listEdge[j] > end){
                    System.out.println("Case #" + CASE + ": ");
                    System.out.println("IMPOSSIBLE");
                    return;
                }
            }
        }
        int startOfPair = N-1;
        for(int i = 0;i<N;i++){
            if(listEdge[i] == i+1){
                startOfPair = i;
            }
        }
        Section s = new Section(startOfPair+1,startOfPair);
        if(s.render()){
            System.out.println("Case #" + CASE + ": ");
            for(int row = 0; row< R; row++){
                for (int col = 0;col<C;col++){
                    if(garden[row][col] == null){
                        System.out.print("\\");
                        continue;
                    }
                    switch (garden[row][col]){
                        case TOP_LEFT:
                            System.out.print("\\");
                            break;
                        case TOP_RIGHT:
                            System.out.print("/");
                            break;
                    }
                }
                System.out.println();
            }
        }else{
            System.out.println("Case #" + CASE + ": ");
            System.out.println("IMPOSSIBLE");
        }
    }
    public static void main(String[] args){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            new Solution(CASE);
        };
    }
}