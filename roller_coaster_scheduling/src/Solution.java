import java.util.*;

public class Solution{
    static Scanner in = new Scanner(System.in);
    static int CASE_NUM;

    public Solution(int CASE){
        System.out.println("Case #" + CASE + ": " + "TODO: send output");
    }
    public static void main(String[] args){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            new Solution(CASE);
        };
    }
}

class Util{
    static class Node{
        Set<Node> setAdj = new HashSet<>();
    }

    static class NodeDist extends Node{
        Map<NodeDist, Double> dist = new HashMap<>();
    }

    static void connect(Node a, Node b){
        a.setAdj.add(b);
    }

    static void connectDist(NodeDist a, NodeDist b, double dist){
        a.setAdj.add(b);
        a.dist.put(b, dist);
    }

    static List<NodeDist> djikstra(NodeDist origin, NodeDist destination){
        Map<NodeDist, Double> mapDist = new HashMap<>();
        Map<NodeDist, NodeDist> mapParent = new HashMap<>();
        TreeSet<NodeDist> queue = new TreeSet<>((a, b) -> (int)Math.signum(mapDist.get(a)-mapDist.get(b)));
        Set<NodeDist> explored = new HashSet<>();

        mapDist.put(origin, 0.);
        queue.add(origin);
        mapParent.put(origin, null);
        while(!queue.isEmpty()){
            NodeDist node = queue.pollFirst();
            explored.add(node);
            for(NodeDist adj: node.dist.keySet()){
                double distPrev = mapDist.getOrDefault(adj, Double.MAX_VALUE);
                double distNew = mapDist.get(node) + node.dist.get(adj);
                if(!explored.contains(adj) && distNew < distPrev){
                    queue.remove(adj);
                    mapDist.put(adj, mapDist.get(node)+node.dist.get(adj));
                    queue.add(adj);
                    mapParent.put(adj, node);
                }
            }
        }
        if(explored.contains(destination)){
            List<NodeDist> path = new ArrayList<>();
            for(NodeDist node = destination; node != null; node = mapParent.get(node)){
                path.add(node);
            }
            Collections.reverse(path);
            return path;
        }else{
            return null;
        }

    }

    static List<Node> BFS(Node origin, Node destination){
        Map<Node, Node> mapParent = new HashMap<>();
        Deque<Node> queue = new ArrayDeque<>();
        mapParent.put(origin, null);
        queue.add(origin);
        while(!queue.isEmpty()){
            Node nodeCur = queue.removeFirst();
            for(Node nodeAdj: nodeCur.setAdj){
                if(!mapParent.containsKey(nodeAdj)){
                    mapParent.put(nodeAdj, nodeCur);
                    queue.addLast(nodeAdj);
                }
            }
        }
        if(mapParent.get(destination) == null){
            return null;
        }else{
            List<Node> path = new ArrayList<>();
            for(Node node = destination; node != null; node = mapParent.get(node)){
                path.add(node);
            }
            Collections.reverse(path);
            return path;
        }
    }
}