import java.util.Scanner;

public class Solution{
    static Scanner in = new Scanner(System.in);
    int CASE_NUM;
    public Solution(){
        CASE_NUM = in.nextInt();
        in.nextLine();
        for(int CASE = 1; CASE <= CASE_NUM; CASE++){
            char[] letters = in.nextLine().toCharArray();
            char[] decoded = new char[letters.length];
            for(int i = 0;i<letters.length;i++){
                switch (letters[i]){
                    case 'y': decoded[i] = 'a';
                    break;
                    case 'n': decoded[i] = 'b';
                    break;
                    case 'f': decoded[i] = 'c';
                    break;
                    case 'i': decoded[i] = 'd';
                    break;
                    case 'c': decoded[i] = 'e';
                    break;
                    case 'w': decoded[i] = 'f';
                    break;
                    case 'l': decoded[i] = 'g';
                    break;
                    case 'b': decoded[i] = 'h';
                    break;
                    case 'k': decoded[i] = 'i';
                    break;
                    case 'u': decoded[i] = 'j';
                    break;
                    case 'o': decoded[i] = 'k';
                    break;
                    case 'm': decoded[i] = 'l';
                    break;
                    case 'x': decoded[i] = 'm';
                    break;
                    case 's': decoded[i] = 'n';
                    break;
                    case 'e': decoded[i] = 'o';
                    break;
                    case 'v': decoded[i] = 'p';
                    break;
                    case 'z': decoded[i] = 'q';
                    break;
                    case 'p': decoded[i] = 'r';
                    break;
                    case 'd': decoded[i] = 's';
                    break;
                    case 'r': decoded[i] = 't';
                    break;
                    case 'j': decoded[i] = 'u';
                    break;
                    case 'g': decoded[i] = 'v';
                    break;
                    case 't': decoded[i] = 'w';
                    break;
                    case 'h': decoded[i] = 'x';
                    break;
                    case 'a': decoded[i] = 'y';
                    break;
                    case 'q': decoded[i] = 'z';
                    break;
                    default: decoded[i] = ' ';
                }
            }
            System.out.println("Case #" + CASE + ": " + String.copyValueOf(decoded));
        }
    }
    public static void main(String[] args){
        new Solution();
    }
}