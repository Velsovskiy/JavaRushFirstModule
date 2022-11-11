public class Main {
    public static void main(String[] args) {
        String command=args[0];
        String FilePath=args[1];
        int keyFirst= Integer.parseInt(args[2]);
        int key=keyFirst%26;
        if("encode".equals(command)){
            System.out.println("encode");
            Encoder encoder=new Encoder();
            encoder.encodeFile(FilePath,key);
        }else if("decode".equals(command)){
            System.out.println("decode");
            Decoder decoder=new Decoder();
            decoder.decodeFile(FilePath,key);
        }
        //ssss
    }

}
