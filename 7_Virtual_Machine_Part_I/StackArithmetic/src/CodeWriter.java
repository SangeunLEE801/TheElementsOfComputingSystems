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

    public void writeArithmetic(String command) {
        switch (command) {
            case "add":
                break;
            case "sub":
                break;
            case "neg":
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
                break;
        }
    }

    public void writePushPop(CommandType command, String segment, int index) {

    }

    public void close() throws IOException {
        writer.close();
    }
}
