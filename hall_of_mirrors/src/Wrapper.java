import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Wrapper {
    public static void main(String[] args){
        try {
            System.setIn(new FileInputStream("input.txt"));
        } catch (FileNotFoundException e) {
            System.err.println("input.txt could not be opened");
            System.exit(-1);
        }
        Solution.main(args);
    }
}
