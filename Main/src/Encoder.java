import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Encoder {
    public final List<Character> ALPHABET = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'k', 'l', 'm', 'n', 'o', 'p'
            , 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
    public List<Character> encodedAlphabet = new ArrayList<>(ALPHABET);
    private final int beforeTXT=4;


    public void encodeFile(String filePath, int key) {
        Path path = Paths.get(filePath);
        try {
            String text = Files.readString(path);
            String encodedText = encode(text, key);
            Path outFilePath = Paths.get(addNameOfFile(filePath,key));
            Files.write(outFilePath, encodedText.getBytes(StandardCharsets.UTF_8));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String addNameOfFile(String filePath, int key) {
        if (key > 0) {
            StringBuilder sb = new StringBuilder(filePath);
            sb.insert(filePath.length() - beforeTXT, "(encoded)");
            return sb.toString();
        } else {
            if (key < 0) {
                StringBuilder sb = new StringBuilder(filePath);
                sb.insert(filePath.length() - beforeTXT, "(decoded)");
                return sb.toString();
            } else {
                StringBuilder sb = new StringBuilder(filePath);
                sb.insert(filePath.length() - beforeTXT, "(We do nothing)");
                return sb.toString();
            }
        }
    }

    public String encode(String text, int key) {
        StringBuilder sb = new StringBuilder();

        for (char c : text.toCharArray()) {
            sb.append((encode(c, key)));
        }
        return sb.toString();
    }

    public Character encode(Character c, int key) {
        boolean LetterCase = false;
        if (Character.isUpperCase(c)) {
            LetterCase = true;
            c = Character.toLowerCase(c);
        }

        if (ALPHABET.contains(c)) {
            Collections.rotate(encodedAlphabet, key);
            int index = ALPHABET.indexOf(c);
            Character result = encodedAlphabet.get(index);
            if (LetterCase) {
                result = Character.toUpperCase(result);
            }
            return result;
        } else {
            return c;
        }

    }
}
