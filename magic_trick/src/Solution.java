import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution{
    static Scanner in = new Scanner(System.in);
    int CASE_NUM;
    public Solution(){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            int firstRow = in.nextInt()-1;
            Set<Integer> firstSet = new HashSet<>();
            for(int row = 0; row<4;row++){
                for(int col = 0;col<4;col++){
                    int card = in.nextInt();
                    if(row == firstRow) firstSet.add(card);
                }
            }
            int secondRow = in.nextInt()-1;
            Set<Integer> secondSet = new HashSet<>();
            for(int row = 0; row<4;row++){
                for(int col = 0;col<4;col++){
                    int card = in.nextInt();
                    if(row == secondRow) secondSet.add(card);
                }
            }

            firstSet.retainAll(secondSet);
            if(firstSet.size() == 1){
                System.out.println("Case #" + CASE + ": " + firstSet.stream().iterator().next());
            }else if(firstSet.isEmpty()){
                System.out.println("Case #" + CASE + ": Volunteer cheated!");
            }else{
                System.out.println("Case #" + CASE + ": Bad magician!");
            }
        }
    }
    public static void main(String[] args){
        new Solution();
    }
}