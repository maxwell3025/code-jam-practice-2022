import java.util.*;
public class Solution{
    static Scanner in = new Scanner(System.in);
    static int CASE_NUM;
    int k;
    char[] listChars;

    int N;
    int[][] matrixMatches;
    class Path{
        Set<Integer> stops = new HashSet<>();
        int start;
        int end;
    }
    Map<Path, Integer> memo = new HashMap<>();
    int getMatches(int groupA, int groupB){
        if(groupA<groupB){
            return matrixMatches[groupA][groupB];
        }else{
            return matrixMatches[groupB][groupA];
        }
    }

    int getLength(Set<Integer> stops, int start, int end){
        Path input = new Path();
        input.start = start;
        input.end = end;
        input.stops = new HashSet<>();
        input.stops.addAll(stops);
        if (memo.containsKey(input))
            return memo.get(input);
        if(stops.size() == 1)
            return 0;
        int maxLength = 0;
        Set<Integer> setPrev = new HashSet<>(stops);
        setPrev.remove(end);
        for(int prevEnd: setPrev){
            if(prevEnd != start){
                int prevLength = getLength(setPrev, start, prevEnd);
                prevLength += getMatches(end, prevEnd);
                maxLength = Math.max(maxLength, prevLength);
            }
        }
        memo.put(input, maxLength);
        return maxLength;
    }

    char getChar(int index){
        if(index < 0 || index >= listChars.length){
            return '-';
        }
        return listChars[index];
    }
    public Solution(int CASE){
        k = in.nextInt();
        listChars = in.next().toCharArray();

        matrixMatches = new int[k][k*2];
        N = listChars.length;
        for(int i = 0;i<N; i++){
            for(int j = i;j/k < (i/k+2); j++){
                if (getChar(i) == getChar(j))
                    matrixMatches[i%k][j-(i/k)*k]++;
            }
        }
        int maxConnections = 0;
        Set<Integer> allGroups = new HashSet<>();
        for(int i = 0;i<k;i++){
            allGroups.add(i);
        }
        for(int start = 0;start<k;start++){
            for(int end = 0;end<k;end++){
                if(start == end)
                    continue;
                int nonLoopedConnections = getLength(allGroups, start, end);
                maxConnections = Math.max(nonLoopedConnections+matrixMatches[end][start+k], maxConnections);
            }
        }

        System.out.println("Case #" + CASE + ": " + (N-maxConnections));
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