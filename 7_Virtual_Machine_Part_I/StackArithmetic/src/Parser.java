import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private Scanner scanner;

    public String getCurrentCommand() {
        return this.currentCommand;
    }

    private String currentCommand;
    public Parser(String fileName) throws IOException {
        scanner = new Scanner(new File(fileName));
    }

    public boolean hasMoreCommands() {
        return scanner.hasNextLine();
    }

    public void advance() {
        currentCommand = scanner.nextLine();
        currentCommand = currentCommand.replaceAll("//.*|\\s|^$", "");
    }

    public CommandType commandType() {
        if (currentCommand.startsWith("push")) return CommandType.C_PUSH;
        else if (currentCommand.startsWith("pop")) return CommandType.C_POP;
        else if (currentCommand.startsWith("label")) return CommandType.C_LABEL;
        else if (currentCommand.startsWith("goto")) return CommandType.C_GOTO;
        else if (currentCommand.startsWith("if")) return CommandType.C_IF;
        else if (currentCommand.startsWith("function")) return CommandType.C_FUNCTION;
        else if (currentCommand.startsWith("return")) return CommandType.C_RETURN;
        else if (currentCommand.startsWith("call")) return CommandType.C_CALL;
        else return CommandType.C_ARITHMETIC;
    }

    public String arg1() {
        return currentCommand.split(" ")[0];
    }

    public String arg2() {
        return currentCommand.split(" ")[1];
    }
}
