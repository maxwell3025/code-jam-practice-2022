import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Chest{
    int id;
    int keyType;
    int nKeys;
    int[] keyList;
    public Chest(){

    }
}
public class Solution{
    static Scanner in = new Scanner(System.in);
    static int CASE_NUM;
    public Solution(int CASE){
        int nKeys = in.nextInt();
        int nChests = in.nextInt();
        List<Integer> keys = new ArrayList<>();
        List<Chest> chests = new ArrayList<>();
        for(int i = 0; i < nKeys; i++){
            keys.add(in.nextInt());
        }
        for(int i = 0; i < nChests; i++){
            Chest c = new Chest();
            c.keyType = in.nextInt();
            c.nKeys = in.nextInt();
            c.keyList = new int[c.nKeys];
            for(int j = 0;j<c.nKeys;j++){
                c.keyList[j] = in.nextInt();
            }
            c.id = i;
            chests.add(c);
        }
    }
    public static void main(String[] args){

        CASE_NUM = in.nextInt();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            new Solution(CASE);
        }
    }
}