import java.util.Scanner;
import java.net.URL;
import java.io.IOException;    
public class Book
{
    private String book;
    public Book (String url)
    {
        readBook(url);
    }
    private void readBook(String link)
    {
      StringBuilder sb = new StringBuilder();
       try{
        URL bookURL = new URL (link);
        Scanner s = new Scanner(bookURL.openStream());
        while (s.hasNextLine())
        {
            String text = s.nextLine();
            // preserve line breaks instead of joining everything into one long line
            sb.append(text);
            sb.append(System.lineSeparator());
            // book += text ;   // removed — avoid null-concat and duplicate
        }
        s.close();
        this.book = sb.toString();
       }
       catch (IOException e)
       {
        System.out.println("Error reading book from URL: " + e.getMessage());
       }
    }

     public Book()
    {
        book = "";
    }

    public String pigLatin(String word)
    {
        String digits = "123456789";
        String vowels = "aeiouy";

        if (word.length() == 0) return word;
        if (digits.indexOf(word.substring(0,1)) >= 0) return word;
        if (vowels.indexOf(word.substring(0,1).toLowerCase()) >= 0) return word + "yay";
        if (word.length() == 1) return word + "ay";

        for (int i = 0; i < word.length(); i++)
        {
            if (vowels.indexOf(word.substring(i,i+1).toLowerCase()) >= 0)
            {
                String left = word.substring(0,i);
                String right = word.substring(i);
                return right + left + "ay";
            }
        }

        return word + "ay";
    }

    public String fixCapital(String original, String pig)
    {
        if (Character.isUpperCase(original.charAt(0)))
        {
            return pig.substring(0,1).toUpperCase() + pig.substring(1);
        }
        return pig;
    }

    public int endPunctuation(String word)
    {
        for (int i = word.length() - 1; i >= 0; i--)
        {
            if (Character.isLetter(word.charAt(i)))
            {
                if (i == word.length() - 1) return -1;
                else return i + 1;
            }
        }
        return 0;
    }

    public String translateWord(String word)
    {
        if (word.length() == 0) return word;

        int punctIndex = endPunctuation(word);
        String punctuation = "";
        if (punctIndex > 0)
        {
            punctuation = word.substring(punctIndex);
            word = word.substring(0, punctIndex);
        }

        String lower = word.toLowerCase();
        String pig = pigLatin(lower);

        pig = fixCapital(word, pig);

        return pig + punctuation;
    }

    public String translateSentence(String sentence)
    {
        
      String words = "";
        while (sentence.length()>0  && sentence.indexOf(" ")>=0) {
            String word = sentence.substring(0,sentence.indexOf(" "));
            words += translateWord(word) + " ";
            sentence = sentence.substring(sentence.indexOf(" ")+1);
            
        }
        if (sentence.length()>0) {
            words += translateWord(sentence);
        }
    return words;
    }
   public int countWords(String sentence)
   {
       int count = 0;
       while (sentence.length()>0  && sentence.indexOf(" ")>=0) {
           String word = sentence.substring(0,sentence.indexOf(" "));
           count++;
           sentence = sentence.substring(sentence.indexOf(" ")+1);
       }
       return count+1;
    }
    public String translateBook()
    {
        if (book == null || book.isEmpty()) return "";
        StringBuilder out = new StringBuilder();
        StringBuilder currentLine = new StringBuilder();
        
        for (int i = 0; i < book.length(); i++)
        {
            char c = book.charAt(i);
            
            // Check if current character is a newline
            if (c == '\n')
            {
                out.append(translateSentence(currentLine.toString()));
                out.append(System.lineSeparator());
                currentLine = new StringBuilder();
            }
            else
            {
                currentLine.append(c);
            }
        }
        
        // Don't forget the last line if it doesn't end with a newline
        if (currentLine.length() > 0)
        {
            out.append(translateSentence(currentLine.toString()));
        }
        
        return out.toString();
    }
    }