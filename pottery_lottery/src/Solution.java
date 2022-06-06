import java.util.*;

public class Solution{
    static Scanner in = new Scanner(System.in);
    static int CASE_NUM;
    //Problem Variables

    //Solution Variables

    public Solution(int CASE){
        //Parse Input

        //Solve Problem

        System.out.println("Case #" + CASE + ": " + "TODO: send output");
    }










    //MAIN CLASS--------------------------------------------------

    public static void main(String[] args){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            new Solution(CASE);
        };
    }

    //UTILITY CODE------------------------------------------------

    public static long LCM(long a, long b){
        return Math.abs(a * b) / GCF(a , b);
    }

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

    public static class Rational{
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

    public static class Pair<A, B>{
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

    public static class Point{
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

    public static class Node<E, V>{
        public V value;
        public Map<Node<E, V>, E> edges = new HashMap<>();
        public Node(V value){
            this.value = value;
        }

        public void connect(Node<E, V> other, E edgeData){
            edges.put(other, edgeData);
        }
    }

    public static List<Node<Double, ?>> djikstra(Node<Double, ?> origin, Node<Double, ?> destination){
        Map<Node<Double, ?>, Double> mapDist = new HashMap<>();
        Map<Node<Double, ?>, Node<Double, ?>> mapParent = new HashMap<>();
        TreeSet<Node<Double, ?>> queue = new TreeSet<>((a, b) -> (int)Math.signum(mapDist.get(a)-mapDist.get(b)));
        Set<Node<Double, ?>> explored = new HashSet<>();

        mapDist.put(origin, 0.);
        queue.add(origin);
        mapParent.put(origin, null);
        while(!queue.isEmpty()){
            Node<Double, ?> node = queue.pollFirst();
            explored.add(node);
            for(Node<Double, ?> adj: node.edges.keySet()){
                double distPrev = mapDist.getOrDefault(adj, Double.MAX_VALUE);
                double distNew = mapDist.get(node) + node.edges.get(adj);
                if(!explored.contains(adj) && distNew < distPrev){
                    queue.remove(adj);
                    mapDist.put(adj, mapDist.get(node)+node.edges.get(adj));
                    queue.add(adj);
                    mapParent.put(adj, node);
                }
            }
        }
        if(explored.contains(destination)){
            List<Node<Double, ?>> path = new ArrayList<>();
            for(Node<Double, ?> node = destination; node != null; node = mapParent.get(node)){
                path.add(node);
            }
            Collections.reverse(path);
            return path;
        }else{
            return null;
        }

    }

    public static<E, V> List<Node<E, V>> BFS(Node<E, V> origin, Node<E, V> destination){
        Map<Node<E, V>, Node<E, V>> mapParent = new HashMap<>();
        Deque<Node<E, V>> queue = new ArrayDeque<>();
        mapParent.put(origin, null);
        queue.add(origin);
        while(!queue.isEmpty()){
            Node<E, V> nodeCur = queue.removeFirst();
            for(Node<E, V> nodeAdj: nodeCur.edges.keySet()){
                if(!mapParent.containsKey(nodeAdj)){
                    mapParent.put(nodeAdj, nodeCur);
                    queue.addLast(nodeAdj);
                }
            }
        }
        if(mapParent.get(destination) == null){
            return null;
        }else{
            List<Node<E, V>> path = new ArrayList<>();
            for(Node<E, V> node = destination; node != null; node = mapParent.get(node)){
                path.add(node);
            }
            Collections.reverse(path);
            return path;
        }
    }
}