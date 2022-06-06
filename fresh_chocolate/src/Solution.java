import java.util.Scanner;

public class Solution{
    static Scanner in = new Scanner(System.in);
    static int CASE_NUM;
    int N;
    int P;
    int[] listCountMods;
    boolean getValid(){
        for(int i = 0;i<P;i++){
            if(listCountMods[i]<0)return false;
        }
        return true;
    }
    boolean popBlock(){
        boolean failed = true;
        for(int i: listCountMods){
            if(i>=1){
                failed = false;
            }
        }
        if (failed) {
            return false;
        }



        if(listCountMods[0] >= 1){
            listCountMods[0]--;
            return true;
        }

        for(int i = 1; i<P; i++){
            int a = i;
            int b = P-i;
            if(a == b && listCountMods[i] >= 2){
                listCountMods[i] -= 2;
                return true;
            }else if(listCountMods[a] >= 1 && listCountMods[b] >= 1){
                listCountMods[a]--;
                listCountMods[b]--;
                return true;
            }
        }

        for(int a = 1; a<P; a++){
            for(int b = 1; b<P; b++){
                for(int c = 1; c<P; c++){
                    listCountMods[a]--;
                    listCountMods[b]--;
                    listCountMods[c]--;
                    if(getValid()){
                        return true;
                    }else{
                        listCountMods[a]++;
                        listCountMods[b]++;
                        listCountMods[c]++;
                    }
                }
            }
        }

        for(int a = 1; a<P; a++){
            for(int b = 1; b<P; b++){
                for(int c = 1; c<P; c++){
                    for(int d = 1; d<P; d++){
                        listCountMods[a]--;
                        listCountMods[b]--;
                        listCountMods[c]--;
                        listCountMods[d]--;
                        if(getValid()){
                            return true;
                        }else{
                            listCountMods[a]++;
                            listCountMods[b]++;
                            listCountMods[c]++;
                            listCountMods[d]++;
                        }
                    }
                }
            }
        }

        for(int i = 0;i<P;i++){
            listCountMods[i]=0;
        }
        return true;
    }
    public Solution(int CASE){
        N = in.nextInt();
        P = in.nextInt();
        listCountMods = new int[P];
        for(int i = 0;i<N;i++){
            listCountMods[in.nextInt()%P]++;
        }
        int out = 0;
        while(popBlock())out++;
        System.out.println("Case #" + CASE + ": " + out);
    }
    public static void main(String[] args){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            new Solution(CASE);
        };
    }
}