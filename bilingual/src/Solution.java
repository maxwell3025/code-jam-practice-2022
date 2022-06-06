import java.util.*;

class Node{
    Set<Node> setAdj = new HashSet<>();
    boolean dead = false;
    boolean explored = false;
    Node parent = null;
    public static void link(Node a, Node b){
        if(a != b){
            a.setAdj.add(b);
            b.setAdj.add(a);
        }
    }
}
public class Solution{
    static Scanner in = new Scanner(System.in);
    static int CASE_NUM;
    int N;
    List<List<String>> listSentences = new ArrayList<>();

    Set<String> setEnKnown = new HashSet<>();
    Set<String> setFrKnown = new HashSet<>();
    Node nodeFr = new Node();
    Node nodeEn = new Node();
    Map<String, Node> mapWordNodes = new HashMap<>();
    public Solution(int CASE){
        N = in.nextInt();
        in.nextLine();
        for(int i = 0;i<N;i++){
            listSentences.add(Arrays.asList(in.nextLine().split(" ")));
        }

        setEnKnown.addAll(listSentences.get(0));
        setFrKnown.addAll(listSentences.get(1));

        //initialize word->node map to have values for each unknown word
        for(int iSentence = 2; iSentence<N;iSentence++){
            List<String> sentence = listSentences.get(iSentence);
            for(String word: sentence){
                if(!setFrKnown.contains(word) && !setEnKnown.contains(word) && !mapWordNodes.containsKey(word))
                    mapWordNodes.put(word, new Node());
            }
        }

        //link each pair of nodes that must share a language
        for(int iSentence = 2; iSentence<N;iSentence++) {
            List<String> sentence = listSentences.get(iSentence);
            for (String wordA : sentence) {
                for (String wordB : sentence) {
                    if (wordA.equals(wordB)) continue;
                    Node nodeA;
                    if (setEnKnown.contains(wordA)) {
                        nodeA = nodeEn;
                    } else if (setFrKnown.contains(wordA)) {
                        nodeA = nodeFr;
                    } else {
                        nodeA = mapWordNodes.get(wordA);
                    }
                    Node nodeB;
                    if (setEnKnown.contains(wordB)) {
                        nodeB = nodeEn;
                    } else if (setFrKnown.contains(wordB)) {
                        nodeB = nodeFr;
                    } else {
                        nodeB = mapWordNodes.get(wordB);
                    }
                    Node.link(nodeA, nodeB);
                }
            }
        }

        //find every path
        int pathCount = 0;
        while(true){
            nodeFr.dead = false;
            nodeEn.dead = false;
            nodeFr.explored = true;
            nodeEn.explored = true;
            //BFS En->Fr to mark depths
            Deque<Node> queueNode = new ArrayDeque<>();
            queueNode.add(nodeEn);
            while(!queueNode.isEmpty()){
                Node nodeCur = queueNode.getFirst();
                if(nodeCur == nodeFr)
                    break;
                queueNode.removeFirst();
                for(Node nodeAdj:nodeCur.setAdj){
                    if(!nodeAdj.explored){
                        nodeAdj.explored = true;
                        nodeAdj.parent = nodeCur;
                        queueNode.addLast(nodeAdj);
                    }
                }
            }
            if(queueNode.isEmpty()){
                break;
            }
            pathCount++;
            for(Node nodeCur = nodeFr; nodeCur != nodeEn; nodeCur = nodeCur.parent){
                nodeCur.dead = true;
            }
            for(Node node: mapWordNodes.values()){
                node.explored = node.dead;
                node.parent = null;
            }
        }
        System.out.println("Case #" + CASE + ": " + pathCount);
    }
    public static void main(String[] args){
        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            new Solution(CASE);
        };
    }
}