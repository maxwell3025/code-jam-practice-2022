import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Walkway implements Comparable<Walkway>{
    public int beginning;
    public int end;
    public double speed;
    @Override
    public int compareTo(Walkway o) {
        return this.beginning - o.beginning;
    }
}
public class Solution{
    static Scanner in = new Scanner(System.in);
    int CASE_NUM;
    public Solution(){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            int X = in.nextInt();
            double S = in.nextInt();
            double R = in.nextInt() - S;
            double t = in.nextInt();
            int N = in.nextInt();
            List<Walkway> sections = new ArrayList<>();
            for(int i = 0;i<N; i++){
                sections.add(new Walkway());
                sections.get(i).beginning = in.nextInt();
                sections.get(i).end = in.nextInt();
                sections.get(i).speed = in.nextInt() + S;
            }
            sections.sort(Comparator.naturalOrder());
            //create additional "walkways" with walking speed
            if(sections.get(0).beginning != 0){
                Walkway newWalk = new Walkway();
                newWalk.beginning = 0;
                newWalk.end = sections.get(0).beginning;
                newWalk.speed = S;
                sections.add(0, newWalk);
            }
            if(sections.get(sections.size()-1).end != X){
                Walkway newWalk = new Walkway();
                newWalk.beginning = sections.get(sections.size()-1).end;
                newWalk.end = X;
                newWalk.speed = S;
                sections.add(newWalk);
            }
            N = sections.size();
            for(int i = 0;i<N-1;i++){
                int beginning = sections.get(i).end;
                int end = sections.get(i+1).beginning;
                if(beginning != end){
                    Walkway walkingSection = new Walkway();
                    walkingSection.beginning = beginning;
                    walkingSection.end = end;
                    walkingSection.speed = S;
                    sections.add(walkingSection);
                }
            }
            sections.sort(Comparator.comparingDouble(a -> a.speed));
            //put running with slowest sections first
            int i = 0;
            while(t != 0 && i < sections.size()){
                Walkway curSect = sections.get(i);
                double length = curSect.end - curSect.beginning;
                double currentSpeed = curSect.speed;
                double newSpeed = currentSpeed + R;
                double newTime = length/newSpeed;
                if(newTime <= t){
                    t -= newTime;
                    curSect.speed = newSpeed;
                }else{
                    double initialDist = newSpeed * t;
                    double extraTime = (length-initialDist)/currentSpeed;
                    curSect.speed = length/(t+extraTime);
                    t = 0;
                }
                i++;
            }
            double walkTime = 0;
            for(Walkway w: sections){
                walkTime += (w.end-w.beginning)/w.speed;
            }
            System.out.println("Case #" + CASE + ": " + String.format("%.07f", walkTime));
        }
    }
    public static void main(String[] args){
        new Solution();
    }
}