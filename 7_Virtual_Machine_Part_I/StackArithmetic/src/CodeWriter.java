import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CodeWriter {

    private BufferedWriter writer;

    public CodeWriter(File file) throws IOException {
        String filename = file.getName().replace(".vm", ".asm");
        writer = new BufferedWriter(new FileWriter(filename));
    }

    public void writeArithmetic(String command) throws IOException {
        StringBuilder assembly = new StringBuilder();

        switch (command) {
            case "add":
                break;
            case "sub":
                break;
            case "neg":
                /**
                 * addr = SP - 1;
                 * *addr = (*addr) * (-1);
                 */
                assembly.append("@SP").append("\n")
                        .append("D=M-1").append("\n")
                        .append("@addr").append("\n")
                        .append("M=D").append("\n")
                        .append("@addr").append("\n")
                        .append("D=M").append("\n")
                        .append("M=-D").append("\n");
                break;
            case "eq":
                break;
            case "gt":
                break;
            case "lt":
                break;
            case "and":
                break;
            case "or":
                break;
            case "not":
                /**
                 * addr = SP - 1;
                 * *addr = !(*addr);
                 */
                assembly.append("@SP").append("\n")
                        .append("D=M-1").append("\n")
                        .append("@addr").append("\n")
                        .append("M=D").append("\n")
                        .append("@addr").append("\n")
                        .append("D=M").append("\n")
                        .append("M=!D").append("\n");
                break;
        }
        writer.write(assembly.toString());
    }

    public void writePushPop(CommandType command, String segment, int index) {

    }

    public void close() throws IOException {
        writer.close();
    }
}
