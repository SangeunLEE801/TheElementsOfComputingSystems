import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, Exception {
        String fileName = args[0].split("\\.asm")[0]+".hack";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        Parser parser = new Parser(args[0]);
        SymbolTable symbolTable = new SymbolTable();

        // First pass
        int romAdr = 0;
        while (parser.hasMoreCommands()) {
            parser.advance();
            if (parser.getCurrentCommand().isEmpty()) continue;

            switch (parser.commandType()) {
                case A_COMMAND:
                case C_COMMAND:
                    romAdr++;
                    break;
                case L_COMMAND:
                    symbolTable.addEntry(parser.symbol(), romAdr+1);
                    break;
            }
        }
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
