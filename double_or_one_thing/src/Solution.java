import java.util.Scanner;

public class Solution{
    static Scanner in = new Scanner(System.in);
    int CASE_NUM;
    public Solution(){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            String word = in.next();
            char[] characters = word.toCharArray();
            int N = characters.length;
            boolean[] highlighted = new boolean[N];
            boolean highlighting = false;
            for(int i = N-2; i >= 0; i--){
                if(characters[i+1] > characters[i]){
                    highlighting = true;
                }
                if(characters[i+1] < characters[i]){
                    highlighting = false;
                }
                highlighted[i] = highlighting;
            }
            StringBuilder s = new StringBuilder();
            for(int i = 0;i<N;i++){
                s.append(characters[i]);
                if(highlighted[i]) s.append(characters[i]);
            }
            System.out.println("Case #" + CASE + ": " + s.toString());
        }
    }
    public static void main(String[] args){
        new Solution();
    }
}