import java.util.LinkedList;
import java.util.Scanner;

public class Solution{
    Scanner in;
    int CASE_NUM;
    LinkedList<Integer> groups;
    int euros;
    int cyclePos;
    int[] eurosAtPos;
    int[] runsAtPos;
    int runs;
    int cap;
    int N;
    public Solution(){
        in = new Scanner(System.in);
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            runs = in.nextInt();
            cap = in.nextInt();
            N = in.nextInt();
            groups = new LinkedList<>();
            for(int i = 0;i<N;i++){
                groups.addLast(in.nextInt());
            }
            euros = 0;
            cyclePos = 0;
            eurosAtPos = new int[N];
            runsAtPos = new int[N];
            while(eurosAtPos[cyclePos] == 0 && runs != 0){
                service();
            }
            if(runs != 0) {
                int cycleEuros = euros - eurosAtPos[cyclePos];
                int cycleLength = runsAtPos[cyclePos] - runs;

                int cyclesLeft = runs / cycleLength;
                euros += cyclesLeft * cycleEuros;
                runs -= cyclesLeft * cycleLength;
            }
            while(runs != 0){
                service();
            }
            System.out.println("Case #" + CASE + ": " + euros);
        }
    }
    public void service(){
        eurosAtPos[cyclePos] = euros;
        runsAtPos[cyclePos] = runs;
        int cartFill = 0;
        int nFilled = 0;
        while(cartFill + groups.getFirst() <= cap && nFilled != N){
            groups.addLast(groups.getFirst());
            cartFill += groups.pop();
            cyclePos = (cyclePos+1)%N;
            nFilled ++;
        }
        euros += cartFill;
        runs--;
    }
    public static void main(String[] args){
        new Solution();
    }
}