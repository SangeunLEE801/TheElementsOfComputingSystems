import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Code {

    private static Map<String, String> comp = Stream.of(new String[][] {
        { "0", "101010" },
        { "1", "111111" },
        { "-1", "111010" },
        { "D", "001100" },
        { "A", "110000" },
        { "M", "110000" },
        { "!D", "001101" },
        { "!A", "110001" },
        { "!M", "110001" },
        { "-D", "001111" },
        { "-A", "110011" },
        { "-M", "110011" },
        { "D+1", "011111" },
        { "A+1", "110111" },
        { "M+1", "110111" },
        { "D-1", "001110" },
        { "A-1", "110010" },
        { "M-1", "110010" },
        { "D+A", "000010" },
        { "D+M", "000010" },
        { "D-A", "010011" },
        { "D-M", "010011" },
        { "A-D", "000111" },
        { "M-D", "000111" },
        { "D&A", "000000" },
        { "D&M", "000000" },
        { "D|A", "010101" },
        { "D|M", "010101" }
    }).collect(Collectors.collectingAndThen(
            Collectors.toMap(data -> data[0], data -> data[1]),
            Collections::<String, String> unmodifiableMap));

    private static Map<String, String> dest = Stream.of(new String[][] {
            { "M", "001" },
            { "D", "010" },
            { "MD", "011" },
            { "A", "100" },
            { "AM", "101" },
            { "AD", "110" },
            { "AMD", "111" }
    }).collect(Collectors.collectingAndThen(
            Collectors.toMap(data -> data[0], data -> data[1]),
            Collections::<String, String> unmodifiableMap));

    private static Map<String, String> jump = Stream.of(new String[][] {
            { "JGT", "001" },
            { "JEQ", "010" },
            { "JGE", "011" },
            { "JLT", "100" },
            { "JNE", "101" },
            { "JLE", "110" },
            { "JMP", "111" }
    }).collect(Collectors.collectingAndThen(
            Collectors.toMap(data -> data[0], data -> data[1]),
            Collections::<String, String> unmodifiableMap));

    public static String dest(String instruction) {
        if (instruction.isEmpty()) return "000";
        else return dest.get(instruction);
    }

    public static String comp(String instruction) {
        if (instruction.isEmpty()) return "000";
        else return comp.get(instruction);
    }

    public static String jump(String instruction) {
        if (instruction.isEmpty()) return "000";
        else return jump.get(instruction);
    }

}
