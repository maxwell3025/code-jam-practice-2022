import java.util.*;

/**
 * PROBLEM:
 * A graph with nodes G[0]...G[N-1] has a maximum flow f from a to b
 * Find the maximum flow from node G[S] to node G[D]
 * INPUT:
 * For each test case:
 * N, M, S, and D are given in line 1
 * for each of the following M lines, a, b, f are given
 * if a connection is not listed, assume f == 0
 * OUTPUT:
 * maximum flow
 */


public class Solution{
    static Scanner in = new Scanner(System.in);
    static int CASE_NUM;
    Node[] nodes;
    int N;
    int M;
    int S;
    int D;

    Node nodeSource;
    Node nodeDrain;
    class Node{
        Map<Node, Double> cap = new HashMap<>();
        Map<Node, Double> flow = new HashMap<>();
    }
    double residual(Node a, Node b){
        return a.cap.get(b) - a.flow.getOrDefault(b,0.);
    }
    void addFlow(Node a, Node b, double amount){
        a.flow.put(b, a.flow.getOrDefault(b,0.) + amount);
        b.flow.put(a, b.flow.getOrDefault(a,0.) - amount);
    }
    boolean open(Node a, Node b){
        return a.cap.get(b)>a.flow.getOrDefault(b,0.);
    }
    public Solution(int CASE){
        N = in.nextInt();
        M = in.nextInt();
        S = in.nextInt();
        D = in.nextInt();
        nodes = new Node[N];
        for(int i = 0; i<N;i++){
            nodes[i] = new Node();
        }
        for(int i = 0; i < M; i++){
            int a = in.nextInt();
            int b = in.nextInt();
            double c = in.nextDouble();
            nodes[a].cap.put(nodes[b], c);
        }
        nodeSource = nodes[S];
        nodeDrain = nodes[D];
        double maxFlow = 0;
        while(true){
            Deque<Node> queue = new ArrayDeque<>();
            Set<Node> explored = new HashSet<>();
            HashMap<Node, Node> parent = new HashMap<>();
            queue.add(nodeSource);
            explored.add(nodeSource);
            while(!queue.isEmpty()){
                Node node = queue.removeFirst();
                if(node == nodeDrain) break;
                for(Node adj: node.cap.keySet()){
                    if(explored.contains(adj) || !open(node, adj)) continue;
                    parent.put(adj, node);
                    explored.add(adj);
                    queue.add(adj);
                }
            }
            if(!explored.contains(nodeDrain)){
                break;
            }

            double streamFlow = Double.MAX_VALUE;
            for(Node node = nodeDrain; node != nodeSource; node = parent.get(node)){
                streamFlow = Math.min(streamFlow, residual(parent.get(node), node));
            }
            maxFlow += streamFlow;
            for(Node node = nodeDrain; node != nodeSource; node = parent.get(node)){
                addFlow(parent.get(node), node, streamFlow);
            }
        }
        System.out.println("Case #" + CASE + ": " + maxFlow);
    }
    public static void main(String[] args){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            new Solution(CASE);
        };
    }
}