import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Parser parser = new Parser(args[0]);
        CodeWriter writer = new CodeWriter(args[0]);

        while (parser.hasMoreCommands()) {
            parser.advance();
            if (parser.getCurrentCommand().isEmpty()) continue;

            switch (parser.commandType()) {
                case C_ARITHMETIC:
                    writer.writeArithmetic(parser.arg1());
                    break;
                case C_PUSH:
                    writer.writePushPop(CommandType.C_PUSH, parser.arg1(), parser.arg2());
                    break;
                case C_POP:
                    writer.writePushPop(CommandType.C_POP, parser.arg1(), parser.arg2());
                    break;
                case C_LABEL:
                    break;
                case C_GOTO:
                    break;
                case C_IF:
                    break;
                case C_FUNCTION:
                    break;
                case C_RETURN:
                    break;
                case C_CALL:
                    break;
            }
        }

        writer.close();
    }
}
