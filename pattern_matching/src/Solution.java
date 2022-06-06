import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution{
    static Scanner in = new Scanner(System.in);
    static int CASE_NUM;
    String[] rawData;
    int N;
    String[] startCaps;
    String[] endCaps;
    String[][] tokens;

    public Solution(int CASE){
        N = in.nextInt();
        rawData = new String[N];
        startCaps = new String[N];
        endCaps = new String[N];
        tokens = new String[N][];
        for(int i = 0;i<N; i++){
            rawData[i] = in.next();
            tokens[i] = rawData[i].split("\\*", -1);
            startCaps[i] = tokens[i][0];
            endCaps[i] = tokens[i][tokens[i].length-1];
        }
        String startSum = "";
        for(int i = 0; i< N; i++){
            //check match
            int overlapLength = Math.min(startSum.length(), startCaps[i].length());
            for(int j = 0; j < overlapLength; j++){
                if(startCaps[i].charAt(j) != startSum.charAt(j)){
                    System.out.println("Case #" + CASE + ": *");
                    return;
                }
            }
            //join
            if(startCaps[i].length() > startSum.length()){
                startSum = startCaps[i];
            }
        }
        String endSum = "";
        for(int i = 0; i < N; i++){
            //check match
            String curElem = endCaps[i];
            int overlapLength = Math.min(endSum.length(), curElem.length());
            for(int j = 0; j < overlapLength; j++){
                if(curElem.charAt(curElem.length() - 1 - j) != endSum.charAt(endSum.length() - 1 - j)){
                    System.out.println("Case #" + CASE + ": *");
                    return;
                }
            }
            //join
            if(curElem.length() > endSum.length()){
                endSum = curElem;
            }
        }
        //merge now
        StringBuilder outStringBuilder = new StringBuilder();
        outStringBuilder.append(startSum);
        for(int i = 0;i<N;i++){
            String[] curTokens = tokens[i];
            for(int j = 1; j < curTokens.length-1; j++){
                outStringBuilder.append(curTokens[j]);
            }
        }
        outStringBuilder.append(endSum);
        String outStr = outStringBuilder.toString();
        System.out.println("Case #" + CASE + ": " + outStr);
    }
    public static void main(String[] args){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            new Solution(CASE);
        }
    }
}