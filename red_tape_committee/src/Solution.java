import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Solution{
    static Scanner in = new Scanner(System.in);
    static int CASE_NUM;
    int N;
    int K;
    List<Double> probabilities = new ArrayList<>();
    public double probTie(int nLow, int nHigh){
        List<Double> distribution = new ArrayList<>();
        distribution.add(1.);

        List<Double> inclusions = new ArrayList<>();
        inclusions.addAll(probabilities.subList(0, nLow));
        inclusions.addAll(probabilities.subList(N-nHigh, N));

        for(double probability: inclusions){
            List<Double> newDistribution = new ArrayList<>();

            newDistribution.add(distribution.get(0)*(1-probability));
            for(int i = 1;i<distribution.size(); i++){
                newDistribution.add(distribution.get(i) * (1-probability) + distribution.get(i-1) * probability);
            }
            newDistribution.add(distribution.get(distribution.size()-1)*probability);

            distribution = newDistribution;
        }
        return distribution.get(K/2);
    }
    public Solution(int CASE){
        N = in.nextInt();
        K = in.nextInt();
        for(int i = 0;i<N;i++) probabilities.add(in.nextDouble());
        probabilities.sort(Comparator.naturalOrder());

        double maxProb = 0;
        for(int i = 0;i<=K;i++){
            maxProb = Math.max(maxProb, probTie(i, K-i));
        }

        System.out.println("Case #" + CASE + ": " + maxProb);
    }
    public static void main(String[] args){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            new Solution(CASE);
        };
    }
}