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

    }

    public void writePushPop(CommandType command, String segment, int index) {

    }
}
