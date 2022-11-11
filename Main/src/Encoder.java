import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Encoder {
    public void encodeFile(String FilePath,int key){
        Path path= Paths.get(FilePath);
        try {
            String text=Files.readString(path);
            String encodedText=encode(text,key);

            StringBuilder sb=new StringBuilder(FilePath);
            sb.insert(FilePath.length()-4,"(encoded)");
            Path outFilePath=Paths.get(sb.toString());
            Files.write(outFilePath,encodedText.getBytes(StandardCharsets.UTF_8));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String encode (String text, int key){
        StringBuilder sb=new StringBuilder();

        for (char c : text.toCharArray()) {
            sb.append((encode(c,key)));
        }
        return sb.toString();
    }
    public Character encode(Character c,int key){
        boolean LetterCase=false;
        if(Character.isUpperCase(c)){
            LetterCase=true;
            c=Character.toLowerCase(c);
        }
        List<Character> alphabet=new ArrayList<>();
        alphabet.add('a');
        alphabet.add('b');
        alphabet.add('c');
        alphabet.add('d');
        alphabet.add('e');
        alphabet.add('f');
        alphabet.add('g');
        alphabet.add('h');
        alphabet.add('i');
        alphabet.add('j');
        alphabet.add('k');
        alphabet.add('l');
        alphabet.add('m');
        alphabet.add('n');
        alphabet.add('o');
        alphabet.add('p');
        alphabet.add('q');
        alphabet.add('r');
        alphabet.add('s');
        alphabet.add('t');
        alphabet.add('u');
        alphabet.add('v');
        alphabet.add('w');
        alphabet.add('x');
        alphabet.add('y');
        alphabet.add('z');
        if (alphabet.contains(c)){
            List<Character> encodedAlphabet=new ArrayList<>(alphabet);
            Collections.rotate(encodedAlphabet,key);
            int index=alphabet.indexOf(c);
            Character result=encodedAlphabet.get(index);
            if(LetterCase){
                result=Character.toUpperCase(result);
            }
            return result;
        }else return c;

    }
}
