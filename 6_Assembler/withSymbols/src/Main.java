import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {
        String fileName = args[0].split("\\.asm")[0]+".hack";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        Parser parser1 = new Parser(args[0]);
        Parser parser2 = new Parser(args[0]);
        SymbolTable symbolTable = new SymbolTable();

        // First pass
        int romAdr = 0;
        while (parser1.hasMoreCommands()) {
            parser1.advance();
            if (parser1.getCurrentCommand().isEmpty()) continue;

            switch (parser1.commandType()) {
                case A_COMMAND:
                case C_COMMAND:
                    romAdr++;
                    break;
                case L_COMMAND:
                    symbolTable.addEntry(parser1.symbol(), romAdr);
                    break;
            }
        }

        // Second pass
        int varAdr = 16;
        while (parser2.hasMoreCommands()) {
            parser2.advance();
            if (parser2.getCurrentCommand().isEmpty()) continue;

            switch (parser2.commandType()) {
                case A_COMMAND:
                    int symbol;
                    if (parser2.getCurrentCommand().matches("@\\d+")) {
                        symbol = Integer.parseInt(parser2.symbol());
                    } else {
                        if (symbolTable.contains(parser2.symbol())) {
                            symbol = symbolTable.getAddress(parser2.symbol());
                        } else {
                            symbolTable.addEntry(parser2.symbol(), varAdr);
                            symbol = varAdr++;
                        }
                    }
                    writeAcommand(writer, symbol);
                    break;

                case C_COMMAND:
                    String c= parser2.comp();
                    String d= parser2.dest();
                    String j= parser2.jump();
                    String cc = Code.comp(c);
                    String dd = Code.dest(d);
                    String jj = Code.jump(j);
                    String a = c.contains("M") ? "1" : "0";
                    String cout = "111" + a + cc + dd + jj;
                    writer.write(cout);
                    writer.newLine();

                    break;
            }
            writer.flush();
        }
        writer.close();
    }

    private static void writeAcommand(BufferedWriter writer, int symbol) throws Exception {
        String aout = convertSignedBinary(symbol);
        writer.write(aout);
        writer.newLine();
    }

    private static String convertSignedBinary(int decimal) {
        String binary;
        if (decimal < 0) binary = Integer.toString((int) Math.pow(2, 16) - decimal);
        else if (decimal == 0) binary = String.format("%016d",0);
        else binary = String.format("%016d", Long.parseLong(Integer.toBinaryString(decimal)));
        return binary;
    }
}
