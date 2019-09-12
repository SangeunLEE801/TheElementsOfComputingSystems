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
        if (currentCommand.startsWith("@")) return CommandType.A_COMMAND;
        else if (currentCommand.startsWith("(")) return CommandType.L_COMMAND;
        else return CommandType.C_COMMAND;
    }

    public String symbol() throws Exception {
        String regex = "[A-Z0-9]+|\\b\\d+\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(currentCommand);

        if (matcher.find()) return matcher.group();
        else throw new Exception("No matches");
    }

    public String dest() {
        return currentCommand.contains("=") ? currentCommand.split("=")[0]: "";

    }
    public String comp() {
        String[] s = currentCommand.split("\\=|\\;");
        return dest().isEmpty()? s[0]: s[1];
    }
    public String jump() {
        return currentCommand.contains(";") ? currentCommand.split(";")[1]: "";
    }


}
