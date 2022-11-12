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


    public void encodeFile(String FilePath, int key) {
        Path path = Paths.get(FilePath);
        try {
            String text = Files.readString(path);
            String encodedText = encode(text, key);

            StringBuilder sb = new StringBuilder(FilePath);
            sb.insert(FilePath.length() - 4, "(encoded)");
            Path outFilePath = Paths.get(sb.toString());
            Files.write(outFilePath, encodedText.getBytes(StandardCharsets.UTF_8));

        } catch (IOException e) {
            throw new RuntimeException(e);
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
