import java.io.File;
import java.io.IOException;

public class TestWordList {
    
    /** read contents of a file into a {@code WordList} 
        @param args path to file, expect one argument
    **/
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: TestWordList <path to file>");
            System.exit(1);
        }
        
        // first argument used as pathname
        File f = new File( args[0] );
        try {
            WordList wl = new WordList(f);
            System.out.println(wl.size() + " words read.");
        }
        catch (IOException e) {
            System.err.println("Error: Unable to read from file: " + f.getAbsolutePath());
            System.exit(1);            
        }
    }
}