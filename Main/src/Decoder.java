import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Decoder {
    public void decodeFile(String FilePath, int key) {
        Path path = Paths.get(FilePath);
        try {
            String text = Files.readString(path);
            String encodedText = decode(text, key);

            StringBuilder sb = new StringBuilder(FilePath);
            sb.insert(FilePath.length() - 4, "(decoded)");
            Path outFilePath = Paths.get(sb.toString());
            Files.write(outFilePath, encodedText.getBytes(StandardCharsets.UTF_8));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String decode(String text, int key) {
        Encoder decoder = new Encoder();
        StringBuilder sb = new StringBuilder();

        for (char c : text.toCharArray()) {
            sb.append((decoder.encode(c, key * -1)));
        }
        return sb.toString();
    }
}
