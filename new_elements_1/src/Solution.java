import java.awt.*;
import java.util.*;
import java.util.List;

public class Solution{
    static Scanner in = new Scanner(System.in);
    static int CASE_NUM;
    List<Util.Point> compounds = new ArrayList<>();
    int N;

    Set<Util.Rational> setCutoffs = new HashSet<>();
    public Solution(int CASE){
        N = in.nextInt();
        for(int i = 0;i<N;i++){
            int a = in.nextInt();
            int b = in.nextInt();
            compounds.add(new Util.Point(a, b));
        }

        for(Util.Point a: compounds){
            for(Util.Point b: compounds){
                if(!a.equals(b)){
                    double dx = b.x-a.x;
                    double dy = b.y-a.y;
                    if(dx > 0 && dy < 0){
                        setCutoffs.add(new Util.Rational((long)dy, (long)dx));
                    }
                }
            }
        }

        System.out.println("Case #" + CASE + ": " + (setCutoffs.size()+1));
    }
    public static void main(String[] args){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            new Solution(CASE);
        };
    }
}

class Util{
    public static long GCF(long a, long b){
        if(a == 0)
            return b;
        a = Math.abs(a);
        b = Math.abs(b);
        while(b != 0){
            long temp = a%b;
            a = b;
            b = temp;
        }
        return a;
    }

    static class Rational{
        final long numerator;
        final long denominator;
        public Rational(long numerator, long denominator) {
            long div = GCF(numerator, denominator);
            if(denominator < 0)
                div *= -1;
            if(denominator == 0)
                throw new IllegalArgumentException("Denominator cannot be 0!");
            this.numerator = numerator/div;
            this.denominator = denominator/div;
        }

        @Override
        public boolean equals(Object other){
            if(other instanceof Rational){
                Rational ratOther = (Rational) other;
                return ratOther.denominator == this.denominator && ratOther.numerator == this.numerator;
            }
            return false;
        }

        @Override
        public int hashCode(){
            return Long.hashCode(numerator)^Long.hashCode(denominator);
        }
    }

    static class Pair<A, B>{
        public A first;
        public B second;
        public Pair(A a, B b){
            this.first = a;
            this.second = b;
        }

        @Override
        public boolean equals(Object other){
            if(other instanceof Pair){
                return first.equals(((Pair<?, ?>) other).first) && second.equals(((Pair<?, ?>) other).second);
            }
            return false;
        }

        @Override
        public int hashCode(){
            return first.hashCode()^second.hashCode();
        }
    }

    static class Point{
        public final double x;
        public final double y;
        public Point(double x, double y){
            this.x = x;
            this.y = y;
        }

        public Point add(Point other){
            return new Point(this.x + other.x, this.y + other.y);
        }

        public Point sub(Point other){
            return new Point(this.x - other.x, this.y - other.y);
        }

        public double dot(Point other){
            return this.x * other.x + this.y * other.y;
        }

        public Point scale(double other){
            return new Point(this.x*other, this.y*other);
        }

        public Point perpLeft(){
            return new Point(-this.y, this.x);
        }

        public Point perpRight(){
            return new Point(this.y, -this.x);
        }

        @Override
        public boolean equals(Object other){
            if(other instanceof Point){
                Point otherPoint = (Point) other;
                return this.x == otherPoint.x && this.y == otherPoint.y;
            }
            return false;
        }

        @Override
        public int hashCode(){
            return Double.hashCode(x) ^ Double.hashCode(y);
        }
    }

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