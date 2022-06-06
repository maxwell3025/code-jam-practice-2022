import java.util.*;

public class Solution{
    static Scanner in;
    HashMap<Map.Entry<Character, Character>, Character> rxns = new HashMap<>();
    HashSet<Map.Entry<Character, Character>> opps = new HashSet<>();
    public Solution(int CASE){
        int C = in.nextInt();
        for(int i = 0;i<C;i++){
            String reaction = in.next();
            rxns.put(new AbstractMap.SimpleEntry<>(reaction.charAt(0),reaction.charAt(1)),reaction.charAt(2));
            rxns.put(new AbstractMap.SimpleEntry<>(reaction.charAt(1),reaction.charAt(0)),reaction.charAt(2));
        }
        int D = in.nextInt();
        for(int i = 0;i<D;i++){
            String opposition = in.next();
            opps.add(new AbstractMap.SimpleEntry<>(opposition.charAt(0),opposition.charAt(1)));
            opps.add(new AbstractMap.SimpleEntry<>(opposition.charAt(1),opposition.charAt(0)));
        }
        int N = in.nextInt();
        char[] sequence = in.next().toCharArray();
        LinkedList<Character> elemLi = new LinkedList<>();
        for(int i = 0; i < N; i++){
            elemLi.push(sequence[i]);
            while(elemLi.size() >= 2){
                AbstractMap.SimpleEntry<Character, Character> latestPair = new AbstractMap.SimpleEntry<>(elemLi.get(0), elemLi.get(1));
                if(rxns.containsKey(latestPair)){
                    elemLi.pop();
                    elemLi.pop();
                    elemLi.push(rxns.get(latestPair));
                }else{
                    break;
                }
            }
            boolean wipe = false;
            for(char a : elemLi){
                for(char b : elemLi){
                    if(opps.contains(new AbstractMap.SimpleEntry<>(a,b))){
                        wipe = true;
                    }
                }
            }
            if(wipe)elemLi.clear();
        }
        char[] out = new char[elemLi.size()];
        for(int i = out.length-1; i >= 0; i--){
            out[i] = elemLi.pop();
        }
        System.out.printf("Case #%d: %s\n", CASE, Arrays.toString(out));
    }
    public static void main(String[] args){
        in = new Scanner(System.in);
        int CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            new Solution(CASE);
        }
    }
}