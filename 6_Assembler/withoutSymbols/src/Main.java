import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, Exception {
        String fileName = args[0].split("\\.asm")[0]+".hack";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        Parser parser = new Parser(args[0]);

        while (parser.hasMoreCommands()) {
            parser.advance();
            if (parser.getCurrentCommand().isEmpty()) continue;

            switch (parser.commandType()) {
                case A_COMMAND:
                    String aout = convertSignedBinary(parser.symbol());
                    writer.write(aout); writer.newLine();
                    break;
                case C_COMMAND:
                    String c=parser.comp();
                    String d=parser.dest();
                    String j=parser.jump();
                    String cc = Code.comp(c);
                    String dd = Code.dest(d);
                    String jj = Code.jump(j);
                    String a = c.contains("M") ? "1" : "0";
                    String cout = "111" + a + cc + dd + jj;
                    writer.write(cout); writer.newLine();

                    break;
            }
            writer.flush();
        }
        writer.close();
    }

    private static String convertSignedBinary(String symbol) {
        int decimal = Integer.parseInt(symbol);
        String binary;
        if (decimal < 0) binary = Integer.toString((int) Math.pow(2, 16) - decimal);
        else if (decimal == 0) binary = String.format("%016d",0);
        else binary = String.format("%016d", Long.parseLong(Integer.toBinaryString(decimal)));
        return binary;
    }
}
