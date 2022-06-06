import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution{
    static Scanner in = new Scanner(System.in);
    static int CASE_NUM;
    int[] links;
    int[] heights;
    int N;
    public void solve(int a, int b, int baseheight, int slope){
        heights[a] = baseheight - (slope) * (b-a);
        heights[b] = baseheight;
        List<Integer> peaks = new ArrayList<>();
        peaks.add(a+1);
        int latest = a+1;
        while(latest != b){
            latest = links[latest];
            peaks.add(latest);
        }
        int peakCount = peaks.size();
        for(int i = peakCount-1; i > 0; i--){
            int peakPos = peaks.get(i);
            int newHeight = baseheight-(slope+1)*(b-peakPos);
            solve(peaks.get(i-1), peakPos, newHeight, slope+1);
        }
    }
    public Solution(int CASE){
        N = in.nextInt();
        links = new int[N];
        heights = new int[N];
        for(int i = 0;i<N-1;i++){
            links[i] = in.nextInt()-1;
        }
        links[N-1] = N-1;
        for(int i = 0;i < N-1; i++){
            if(links[i] <= i){
                System.out.println("Case #" + CASE + ": " + "Impossible");
                return;
            }
            for(int j = i + 1; j < links[i]; j++){
                if(links[j] > links[i]){
                    System.out.println("Case #" + CASE + ": " + "Impossible");
                    return;
                }
            }
        }
        int start = 0;
        int end = links[start];
        while(start != end){
            solve(start, end, 0, 0);
            start = links[start];
            end = links[end];
        }
        int minimum = 0;
        for(int a: heights){
            if(a < minimum) minimum = a;
        }
        for(int i = 0;i<N;i++){
            heights[i] -= minimum;
        }
        System.out.print("Case #" + CASE + ":");
        for(int i = 0;i<N;i++){
            System.out.print(" " + heights[i]);
        }
        System.out.println();
    }
    public static void main(String[] args){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            new Solution(CASE);
        };
    }
}