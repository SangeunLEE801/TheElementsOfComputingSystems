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
                /**
                 * second = *(SP -1);
                 * SP--;
                 * first = *(SP - 1);
                 * SP--;
                 * *SP = first + second;
                 * SP++;
                 */
                assembly.append("@SP").append("\n") // second
                        .append("D=M-1").append("\n")
                        .append("@second").append("\n")
                        .append("M=D").append("\n")
                        .append("@SP").append("\n") // SP--;
                        .append("M=M-1").append("\n")
                        .append("@SP").append("\n") // first
                        .append("D=M-1").append("\n")
                        .append("@SP").append("\n") // SP--;
                        .append("M=M-1").append("\n")
                        .append("@second").append("\n")
                        .append("A=M").append("\n")
                        .append("D=D+A").append("\n") // first + second. D still has the value of first
                        .append("@SP").append("\n")
                        .append("M=M+1").append("\n");

                break;
            case "sub":
                /**
                 * SP--;
                 * second = *SP;
                 * SP--;
                 * first = *SP;
                 * *SP = first - second;
                 * SP++;
                 */
                assembly.append("@SP").append("\n") // second
                        .append("D=M-1").append("\n")
                        .append("@second").append("\n")
                        .append("M=D").append("\n")
                        .append("@SP").append("\n") // SP--;
                        .append("M=M-1").append("\n")
                        .append("@SP").append("\n") // first
                        .append("D=M-1").append("\n")
                        .append("@SP").append("\n") // SP--;
                        .append("M=M-1").append("\n")
                        .append("@second").append("\n")
                        .append("A=M").append("\n")
                        .append("D=D-A").append("\n") // first - second. D still has the value of first
                        .append("@SP").append("\n")
                        .append("M=M+1").append("\n");
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
                assembly.append("@SP").append("\n") // second
                        .append("D=M-1").append("\n")
                        .append("@second").append("\n")
                        .append("M=D").append("\n")
                        .append("@SP").append("\n") // SP--;
                        .append("M=M-1").append("\n")
                        .append("@SP").append("\n") // first
                        .append("D=M-1").append("\n")
                        .append("@SP").append("\n") // SP--;
                        .append("M=M-1").append("\n")
                        .append("@second").append("\n")
                        .append("D=D-M").append("\n") // first - second. D still has the value of first
                        .append("@EQUAL").append("\n")
                        .append("D;JEQ").append("\n")
                        .append("@SP").append("\n")
                        .append("M=0").append("\n")
                        .append("(EQUAL)").append("\n")
                        .append("   @SP").append("\n")
                        .append("   M=-1").append("\n")
                        .append("@SP").append("\n")
                        .append("M=M+1").append("\n");

                break;
            case "gt":
                assembly.append("@SP").append("\n") // second
                        .append("D=M-1").append("\n")
                        .append("@second").append("\n")
                        .append("M=D").append("\n")
                        .append("@SP").append("\n") // SP--;
                        .append("M=M-1").append("\n")
                        .append("@SP").append("\n") // first
                        .append("D=M-1").append("\n")
                        .append("@SP").append("\n") // SP--;
                        .append("M=M-1").append("\n")
                        .append("@second").append("\n")
                        .append("D=D-M").append("\n") // first - second. D still has the value of first
                        .append("@GREATER_THAN").append("\n")
                        .append("D;JGT").append("\n")
                        .append("@SP").append("\n")
                        .append("M=0").append("\n")
                        .append("(GREATER_THAN)").append("\n")
                        .append("   @SP").append("\n")
                        .append("   M=-1").append("\n")
                        .append("@SP").append("\n")
                        .append("M=M+1").append("\n");
                break;
            case "lt":
                assembly.append("@SP").append("\n") // second
                        .append("D=M-1").append("\n")
                        .append("@second").append("\n")
                        .append("M=D").append("\n")
                        .append("@SP").append("\n") // SP--;
                        .append("M=M-1").append("\n")
                        .append("@SP").append("\n") // first
                        .append("D=M-1").append("\n")
                        .append("@SP").append("\n") // SP--;
                        .append("M=M-1").append("\n")
                        .append("@second").append("\n")
                        .append("D=D-M").append("\n") // first - second. D still has the value of first
                        .append("@LESS_THAN").append("\n")
                        .append("D;JGT").append("\n")
                        .append("@SP").append("\n")
                        .append("M=0").append("\n")
                        .append("(LESS_THAN)").append("\n")
                        .append("   @SP").append("\n")
                        .append("   M=-1").append("\n")
                        .append("@SP").append("\n")
                        .append("M=M+1").append("\n");
                break;
            case "and":
                /**
                 * SP--;
                 * second = *SP;
                 * SP--;
                 * first = *SP;
                 * *SP = first & second;
                 * SP++;
                 */
                assembly.append("@SP").append("\n") // second
                        .append("D=M-1").append("\n")
                        .append("@second").append("\n")
                        .append("M=D").append("\n")
                        .append("@SP").append("\n") // SP--;
                        .append("M=M-1").append("\n")
                        .append("@SP").append("\n") // first
                        .append("D=M-1").append("\n")
                        .append("@SP").append("\n") // SP--;
                        .append("M=M-1").append("\n")
                        .append("@second").append("\n")
                        .append("A=M").append("\n")
                        .append("D=D&A").append("\n") // first & second. D still has the value of first
                        .append("@SP").append("\n")
                        .append("M=M+1").append("\n");
                break;
            case "or":
                /**
                 * SP--;
                 * second = *SP;
                 * SP--;
                 * first = *SP;
                 * *SP = first | second;
                 * SP++;
                 */
                assembly.append("@SP").append("\n") // second
                        .append("D=M-1").append("\n")
                        .append("@second").append("\n")
                        .append("M=D").append("\n")
                        .append("@SP").append("\n") // SP--;
                        .append("M=M-1").append("\n")
                        .append("@SP").append("\n") // first
                        .append("D=M-1").append("\n")
                        .append("@SP").append("\n") // SP--;
                        .append("M=M-1").append("\n")
                        .append("@second").append("\n")
                        .append("A=M").append("\n")
                        .append("D=D|A").append("\n") // first | second. D still has the value of first
                        .append("@SP").append("\n")
                        .append("M=M+1").append("\n");
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
        writer.flush();
    }

    public void writePushPop(CommandType command, String segment, int index) throws IOException {
        StringBuilder assembly = new StringBuilder();

        switch (command) {
            case C_POP:
                switch (segment) {
                    case "local":
                        break;
                    case "argument":
                        break;
                    case "this":
                        break;
                    case "that":
                        break;
                    case "temp":
                        break;
                    case "static":
                        break;
                    case "pointer":
                        break;
                    case "constant":
                        break;
                }
                break;

            case C_PUSH:
                switch (segment) {
                    case "local":
                        assembly.append("@").append(index) // LCL + i
                                .append("D=A")
                                .append("@LCL")
                                .append("D=M+D")
                                .append("@addr") // addr = LCL + i
                                .append("M=D")
                                .append("D=M") // *addr
                                .append("@SP") // *SP = *addr
                                .append("A=M").append("\n")
                                .append("M=D").append("\n")
                                .append("@SP").append("\n") // SP++
                                .append("M=M+1").append("\n");

                        break;
                    case "argument":
                        assembly.append("@").append(index) // ARG + i
                                .append("D=A")
                                .append("@ARG")
                                .append("D=M+D")
                                .append("@addr") // addr = ARG + i
                                .append("M=D")
                                .append("D=M") // *addr
                                .append("@SP") // *SP = *addr
                                .append("A=M").append("\n")
                                .append("M=D").append("\n")
                                .append("@SP").append("\n") // SP++
                                .append("M=M+1").append("\n");
                        break;
                    case "this":
                        assembly.append("@").append(index) // ARG + i
                                .append("D=A")
                                .append("@THIS")
                                .append("D=M+D")
                                .append("@addr") // addr = ARG + i
                                .append("M=D")
                                .append("D=M") // *addr
                                .append("@SP") // *SP = *addr
                                .append("A=M").append("\n")
                                .append("M=D").append("\n")
                                .append("@SP").append("\n") // SP++
                                .append("M=M+1").append("\n");
                        break;
                    case "that":
                        assembly.append("@").append(index) // ARG + i
                                .append("D=A")
                                .append("@THAT")
                                .append("D=M+D")
                                .append("@addr") // addr = ARG + i
                                .append("M=D")
                                .append("D=M") // *addr
                                .append("@SP") // *SP = *addr
                                .append("A=M").append("\n")
                                .append("M=D").append("\n")
                                .append("@SP").append("\n") // SP++
                                .append("M=M+1").append("\n");
                        break;
                    case "temp":
                        break;
                    case "static":
                        break;
                    case "pointer":
                        break;
                    case "constant":
                        assembly.append("@").append(index).append("\n") // D = i
                                .append("D=A").append("\n")
                                .append("@SP").append("\n") // *SP = D
                                .append("A=M").append("\n")
                                .append("M=D").append("\n")
                                .append("@SP").append("\n") // SP++
                                .append("M=M+1").append("\n");
                        break;
                }
                break;
        }
        writer.write(assembly.toString());
        writer.flush();
    }

    public void close() throws IOException {
        writer.close();
    }
}
