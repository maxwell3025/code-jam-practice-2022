import java.util.*;

class Planet{
    List<Planet> neighbors = new ArrayList<>();
    int dist = Integer.MAX_VALUE;
    int threats = -1;
    boolean queued = false;
    Planet parent = null;
    public void link(Planet other){
        neighbors.add(other);
    }
    public boolean considerPath(Planet newPar){
        Set<Planet> ownPlanets = new HashSet<>();
        Set<Planet> threatPlanets = new HashSet<>(neighbors);
        ownPlanets.add(this);
        Planet next = newPar;
        while(next != null){
            threatPlanets.addAll(next.neighbors);
            ownPlanets.add(next);
            next = next.parent;
        }
        threatPlanets.removeAll(ownPlanets);
        if(threatPlanets.size() > threats){
            parent = newPar;
            threats = threatPlanets.size();
            return true;
        }
        return false;
    }
}
public class Solution{
    static Scanner in = new Scanner(System.in);
    static int CASE_NUM;
    Planet[] planets;
    int N;
    int W;
    public Solution(int CASE){
        N = in.nextInt();
        W = in.nextInt();
        planets = new Planet[N];
        for(int i = 0;i<N;i++) planets[i] = new Planet();
        for (int i = 0; i < W; i++) {
            String[] pair = in.next().split(",");
            int a = Integer.parseInt(pair[0]);
            int b = Integer.parseInt(pair[1]);
            planets[a].link(planets[b]);
            planets[b].link(planets[a]);
        }
        Planet home = planets[0];
        Planet dest = planets[1];
        PriorityQueue<Planet> queue = new PriorityQueue<>(Comparator.comparingInt(a->a.dist));
        queue.add(home);
        home.dist = 0;
        home.threats = home.neighbors.size();
        while(!queue.isEmpty()){
            Planet head = queue.poll();
            for(Planet p: head.neighbors){
                if(p.dist>=head.dist+1){
                    p.dist = head.dist+1;
                    boolean foundNew = p.considerPath(head);
                    if(foundNew && !p.queued){
                        queue.add(p);
                    }
                }
            }
        }
        int maxThreats = Collections.max(dest.neighbors, Comparator.comparingInt(j -> j.threats)).threats;
        int minDist = dest.dist-1;
        System.out.println("Case #" + CASE + ": " + minDist + " " + maxThreats);
    }
    public static void main(String[] args){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            new Solution(CASE);
        };
    }
}