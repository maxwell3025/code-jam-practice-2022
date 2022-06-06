import java.util.*;
import java.util.regex.Pattern;

public class Solution{
    static Scanner in = new Scanner(System.in);
    static int CASE_NUM;
    //Problem Variables
    int N;
    int K;
    //Solution Variables
    int minPathLen;
    int lenResidual;
    List<Pair<Integer, Integer>> jumps = new ArrayList<>();
    Pair<Integer, Integer> getCoord(int index){
        int originDist = (N*N-1)-index;
        Pair<Integer, Integer> diff = new Pair<>(0, 0);
        int currentDist = 0;
        for(int layer = 0;;layer++){
            diff.first -= layer*2+1;
            currentDist+= layer*2+1;
            if(currentDist >= originDist){
                diff.first += currentDist-originDist;
                break;
            }

            diff.second += layer*2+1;
            currentDist+= layer*2+1;
            if(currentDist >= originDist){
                diff.second -= currentDist-originDist;
                break;
            }

            diff.first += layer*2+2;
            currentDist+= layer*2+2;
            if(currentDist >= originDist){
                diff.first -= currentDist-originDist;
                break;
            }

            diff.second -= layer*2+2;
            currentDist+= layer*2+2;
            if(currentDist >= originDist){
                diff.second += currentDist-originDist;
                break;
            }
        }
        return diff;
    }
    int getDistance(int index) {
        Pair<Integer, Integer> diff = getCoord(index);
        return Math.abs(diff.first) + Math.abs(diff.second) + index;
    }

    public Solution(int CASE){
        //Parse Input
        N = in.nextInt();
        K = in.nextInt();
        //Solve Problem
        minPathLen = N-1;
        lenResidual = K - minPathLen;
        //if the residual is less than 0 or it cannot be augmented to;
        if(lenResidual%2 == 1 || lenResidual < 0){
            System.out.println("Case #" + CASE + ": IMPOSSIBLE");
            return;
        }
        int low = 0;
        int high = 25;
        while(low != high-1){
            int mid = (low+high)/2;
            int distMid = getDistance(mid);
            if(distMid > K){
                high = mid;
            }else{
                low = mid;
            }
        }
        Pair<Integer, Integer> currentPos = getCoord(low);
        int index = low;
        while(index != N*N-1){
            if(currentPos.first.equals(currentPos.second) && currentPos.first > 0){
                //jump down
                System.out.println((1 + index) + " " + (1 + index + (-8*currentPos.second-1)));
                index += -8*currentPos.second-1;
                currentPos.second++;
            }else if(currentPos.first.equals(currentPos.second) && currentPos.first < 0){
                index++;
                currentPos.first++;
            }else if(currentPos.first.equals(-currentPos.second) && currentPos.first > 0){
                index++;
                currentPos.second++;
            }else if(currentPos.first.equals(-currentPos.second) && currentPos.first < 0){
                index++;
                currentPos.second--;


            }else if(currentPos.second < 0 && Math.abs(currentPos.first) < Math.abs(currentPos.second)){
                //jump down
                jumps.add(new Pair<Integer, Integer>((1 + index),  (1 + index + (-8*currentPos.second-1))));
                index += -8*currentPos.second-1;
                currentPos.second++;
            }else if(currentPos.second > 0 && Math.abs(currentPos.first) < Math.abs(currentPos.second)){
                //jump up
                jumps.add(new Pair<Integer, Integer>((1 + index) ,(1 + index + (8* currentPos.second-5))));
                index += 8 * currentPos.second-5;
                currentPos.second--;
            }else if(currentPos.first < 0 && Math.abs(currentPos.first) > Math.abs(currentPos.second)){
                //jump right
                jumps.add(new Pair<Integer, Integer>((1 + index) ,(1 + index + (-8*currentPos.first-7))));
                index += -8*currentPos.first-7;
                currentPos.first++;
            }else if(currentPos.first > 0 && Math.abs(currentPos.first) > Math.abs(currentPos.second)){
                //jump left
                jumps.add(new Pair<Integer, Integer>((1 + index) , (1 + index + (8*currentPos.first-3))));
                index += 8*currentPos.first-3;
                currentPos.first--;
            }
        }
        System.out.println("Case #" + CASE + ": " + jumps.size());
        for(Pair<Integer, Integer> a: jumps){
            System.out.println(a.first + " " + a.second);
        }

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

    public static class Rational implements Comparable<Rational>{
        final long numerator;
        final long denominator;
        public Rational(long numerator, long denominator) {
            if(denominator == 0)
                throw new IllegalArgumentException("Denominator cannot be 0!");
            long div = GCF(numerator, denominator);
            if(denominator < 0)
                div *= -1;
            this.numerator = numerator/div;
            this.denominator = denominator/div;
        }

        public Rational reciprocal(){
            return new Rational(denominator, numerator);
        }

        public Rational add(Rational other){
            return new Rational(this.numerator * other.denominator + other.numerator * this.denominator, this.denominator*other.denominator);
        }

        public Rational sub(Rational other){
            return new Rational(this.numerator * other.denominator - other.numerator * this.denominator, this.denominator*other.denominator);
        }

        public Rational mul(Rational other){
            return new Rational(this.numerator*other.numerator, this.denominator*other.denominator);
        }

        public Rational div(Rational other){
            return new Rational(this.numerator*other.denominator, this.denominator*other.numerator);
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

        @Override
        public int compareTo(Rational o) {
            long da = this.numerator * o.denominator;
            long bc = this.denominator * o.numerator;
            if(da > bc){
                return 1;
            }else if(da < bc){
                return -1;
            }else{
                return 0;
            }
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