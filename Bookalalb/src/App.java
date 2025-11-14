import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class App {
  public static void main(String[] args) {
    Book aBook = new Book("https://www.gutenberg.org/cache/epub/6130/pg6130.txt");
    String translated = aBook.translateBook();
    Path out = Paths.get("translated.txt");
    try {
      Files.writeString(out, translated, StandardCharsets.UTF_8);
      System.out.println("Wrote translated text to: " + out.toAbsolutePath());
    } catch (IOException e) {
      System.err.println("Failed to write file: " + e.getMessage());
    }
    System.out.println("word count: " + aBook.countWords(translated));
   Book ScarletLetter = new Book("https://www.gutenberg.org/cache/epub/25344/pg25344.txt");
   String translated2 = ScarletLetter.translateBook();
    Path out2 = Paths.get("ScarletLetter_translated.txt");
    try {
      Files.writeString(out2, translated2, StandardCharsets.UTF_8);
      System.out.println("Wrote translated text to: " + out2.toAbsolutePath());
    } catch (IOException e) {
      System.err.println("Failed to write file: " + e.getMessage());
    }
    System.out.println("word count: " + ScarletLetter.countWords(translated2));
  }

}
