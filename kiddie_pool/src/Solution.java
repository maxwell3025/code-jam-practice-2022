import java.util.Scanner;

public class Solution{
    static Scanner in = new Scanner(System.in);
    static int CASE_NUM;
    int N;
    double V0;
    double X0;
    double[] V;
    double[] X;

    double H0;
    double[] H;
    public Solution(int CASE){
        N = in.nextInt();
        V0 = in.nextDouble();
        X0 = in.nextDouble();
        V = new double[N];
        X = new double[N];
        for(int i = 0;i<N;i++){
            V[i] = in.nextDouble();
            X[i] = in.nextDouble();
        }

        H0 = V0 * X0;
        H = new double[N];
        for(int i = 0;i<N;i++){
            H[i] = V[i] * X[i];
        }

        double maxTime = Double.MAX_VALUE;
        for(int i = 0;i<N;i++){
            if(H0*V[i] == V0*H[i]){
                maxTime = Math.min(maxTime, V0/V[i]);
            }
        }
        for(int i = 0;i<N;i++){
            for(int j = 0;j<i;j++){
                //i <=> a
                //j <=> b
                double coeff = H[i]*V[j]-V[i]*H[j];
                if(coeff == 0) continue;
                double invCoeff = 1.0/(coeff);
                double a = invCoeff*(H0 * V[j] - V0 * H[j]);
                double b = invCoeff*(- H0 * V[i] + V0 * H[i]);
                if(a>=0 && b>=0){
                    maxTime = Math.min(maxTime, a+b);
                }
            }
        }
        if(maxTime < Double.MAX_VALUE)
        System.out.println("Case #" + CASE + ": " + maxTime);
        else
            System.out.println("Case #" + CASE + ": " + "IMPOSSIBLE");
    }
    public static void main(String[] args){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            new Solution(CASE);
        };
    }
}