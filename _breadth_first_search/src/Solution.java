import java.util.*;

/**
 * PROBLEM:
 * given a graph G with N nodes, find the distance from A to B
 * INPUT:
 * N M A B
 * a b d
 * a b d
 * ...
 * [line M]
 */
public class Solution{
    static Scanner in = new Scanner(System.in);
    static int CASE_NUM;
    int N;
    int M;
    int A;
    int B;
    class Node{
        HashMap<Node, Double> dist = new HashMap<>();
    }
    Node[] listNodes;

    Node nodeA;
    Node nodeB;
    public Solution(int CASE){
        N = in.nextInt();
        M = in.nextInt();
        A = in.nextInt();
        B = in.nextInt();
        listNodes = new Node[N];
        for(int i = 0;i<N;i++){
            listNodes[i] = new Node();
        }
        for(int i = 0;i<M;i++){
            int a = in.nextInt();
            int b = in.nextInt();
            double d = in.nextDouble();
            listNodes[a].dist.put(listNodes[b], d);
        }


        nodeA = listNodes[A];
        nodeB = listNodes[B];

        Map<Node, Double> mapDist = new HashMap<>();
        TreeSet<Node> queue = new TreeSet<>((a, b) -> (int)Math.signum(mapDist.get(a)-mapDist.get(b)));
        Set<Node> explored = new HashSet<>();
        mapDist.put(nodeA, 0.);
        queue.add(nodeA);
        while(!queue.isEmpty()){
            Node node = queue.pollFirst();
            explored.add(node);
            for(Node adj: node.dist.keySet()){
                if(explored.contains(adj)) continue;
                if(mapDist.get(node)+node.dist.get(adj) < mapDist.getOrDefault(adj, Double.MAX_VALUE)){
                    queue.remove(adj);
                    mapDist.put(adj, mapDist.get(node)+node.dist.get(adj));
                    queue.add(adj);
                }
            }
        }
        if(mapDist.containsKey(nodeB)){
            System.out.println("Case #" + CASE + ": " + mapDist.get(nodeB));
        }else{
            System.out.println("Case #" + CASE + ": " + "IMPOSSIBLE");
        }
    }
    public static void main(String[] args){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            new Solution(CASE);
        };
    }
}