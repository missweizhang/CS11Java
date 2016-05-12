import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.TreeSet;


/** A set of words, implemented as a wrapper for {@code TreeSet< String >} **/
public class WordList {

    private TreeSet< String > list;


    /** default constructor creates empty word list */
    public WordList() {
        this.list = new TreeSet< String >();
    }

    
    /** constructor creates word list from words read from file,
        @param f the file from which to read words
        @throws IOException if cannot read file or read error
    */
    public WordList( File f ) throws IOException {
        // call default constructor
        this();

        // read words from file into list
        if ( !f.canRead() ) {
            throw new IOException("Cannot open file: " 
                                  + f.getAbsolutePath());
        }
        
        try {
            // BufferedReader reads more efficiently
            BufferedReader br = new BufferedReader(new FileReader(f));
            
            // read each line, BufferedReader#readLine throws IOException
            String line = null;
            while ( (line = br.readLine()) != null )
            {   
                // split each line by whitespace
                String[] words = line.split("\\s+"); // regex "\s+"
                
                // add words to list
                for ( String word : words ) {
                    this.addWord( word );
                }
            }
        }
        catch (IOException e)
        {
            System.err.println("Error: error reading from file: "
                               + f.getAbsolutePath() );
            throw e; // rethrow
        }
    }

    
    /** @return number of words recorded */
    public int size() {
        return this.list.size();
    }

    
    /** helper to trim whitespaces and convert word to lowercase 
        @return converted word
    */
    private String preprocessWord(String word) {
        // trim whitespace
        // convert to lowercase
        return word.trim().toLowerCase();
    }

    
    /** add a word to the list after preprocessing, mutator 
        @param word the word to add
    */
    public void addWord(String word)
    {
        String w = this.preprocessWord( word );
        if ( !w.isEmpty() )
            this.list.add( w );            
    }
    
    
    /** @return true    if list contains the word 
        @param word the word to test whether it is in the word list
    */
    public boolean containsWord(String word) { 
        String w = this.preprocessWord( word );

        if ( w.isEmpty() )
            return false;

        return this.list.contains( w );    
    }

    
    /**  add another word-list into the current word-list
        @param wl another word list
    */
    public void addWordList( WordList wl ) {
        this.list.addAll(wl.list);
    }


    /** Removes from this word list all elements that are also contained in the 
        other word list, by computing the set-differrence.
        @param wl the other word list
        @return true if this word list changed as a result of the call
    */
    public boolean subtract( WordList wl ) {
        return list.removeAll( wl.list );
    }
}