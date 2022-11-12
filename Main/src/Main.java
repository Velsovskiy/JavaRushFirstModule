public class Main {
    public static void main(String[] args) {
        String command = args[0];
        String filePath = args[1];
        int key = Integer.parseInt(args[2]);
        key = key % 26;
        if ("encode".equals(command)) {
            System.out.println("encode");
            Encoder encoder = new Encoder();
            encoder.encodeFile(filePath, key);
        } else if ("decode".equals(command)) {
            System.out.println("decode");
            Encoder encoder = new Encoder();
            encoder.encodeFile(filePath, -key);
        }

    }

}
