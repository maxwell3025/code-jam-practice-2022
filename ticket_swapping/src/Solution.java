import java.math.BigInteger;
import java.util.*;

class Group{
    public int o;
    public int e;
    public int p;
    public Group(int o, int e, int p) {
        this.o = o;
        this.e = e;
        this.p = p;
    }

}
class Card{
    public int age;
    public int num;
    public Card(int age, int num) {
        this.age = age;
        this.num = num;
    }
    BigInteger pay(int amount, int N){
        BigInteger BIamount = BigInteger.valueOf(amount);
        BigInteger BIN = BigInteger.valueOf(N);
        num -= amount;
        BigInteger costPer = BIN.multiply(BigInteger.valueOf(age)).subtract(BigInteger.valueOf((long)age*(age-1L)/2L));
        return costPer.multiply(BIamount);
    }
}
public class Solution{
    static Scanner in = new Scanner(System.in);
    static int CASE_NUM;
    int N;
    int M;
    List<Group> groups = new ArrayList<>();
    PriorityQueue<Group> enter = new PriorityQueue<>(Comparator.comparingInt(a->a.o));
    PriorityQueue<Group> exit = new PriorityQueue<>(Comparator.comparingInt(a->a.e));
    Stack<Card> cards = new Stack<>();
    int stop = 0;
    void update(int newStop){
        int change = newStop-stop;
        for(Card c: cards){
            c.age += change;
        }
        stop = newStop;
    }
    public Solution(int CASE){
        N = in.nextInt();
        M = in.nextInt();
        for(int i = 0; i < M; i++){
            int o = in.nextInt()-1;
            int e = in.nextInt()-1;
            int p = in.nextInt();
            groups.add(new Group(o, e, p));
        }
        enter.addAll(groups);
        exit.addAll(groups);
        BigInteger normTotal = BigInteger.ZERO;
        for(Group g: groups){
            long distance = g.e-g.o;
            long cost = N * (distance) - (distance) * (distance-1)/2;
            normTotal = normTotal.add(BigInteger.valueOf(cost).multiply(BigInteger.valueOf(g.p)));
        }

        BigInteger newTotal = BigInteger.ZERO;
        while(!exit.isEmpty()){
            if(enter.isEmpty() || exit.peek().e < enter.peek().o){
                Group currentGroup = exit.poll();
                int pos = currentGroup.e;
                int num = currentGroup.p;
                update(pos);
                while(num != 0){
                    int deckCap = cards.peek().num;
                    if(num > deckCap){
                        newTotal = newTotal.add(cards.peek().pay(deckCap, N));
                        num -= deckCap;
                        cards.pop();
                    }else{
                        newTotal = newTotal.add(cards.peek().pay(num, N));
                        num = 0;
                    }
                }
            }else{
                Group currentGroup = enter.poll();
                int pos = currentGroup.o;
                int num = currentGroup.p;
                update(pos);
                cards.push(new Card(0, num));
            }
        }

        BigInteger diff = normTotal.subtract(newTotal);
        int out = diff.remainder(BigInteger.valueOf(1000002013)).intValue();
        System.out.println("Case #" + CASE + ": " + out);
    }
    public static void main(String[] args){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            new Solution(CASE);
        };
    }
}