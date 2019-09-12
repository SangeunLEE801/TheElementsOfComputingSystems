import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SymbolTable {
    private Map<String, Integer> symbolT;

    public SymbolTable() {
        this.symbolT = Stream.of(
                new AbstractMap.SimpleImmutableEntry<>("R0", 0),
                new AbstractMap.SimpleImmutableEntry<>("R1", 1),
                new AbstractMap.SimpleImmutableEntry<>("R2", 2),
                new AbstractMap.SimpleImmutableEntry<>("R3", 3),
                new AbstractMap.SimpleImmutableEntry<>("R4", 4),
                new AbstractMap.SimpleImmutableEntry<>("R5", 5),
                new AbstractMap.SimpleImmutableEntry<>("R6", 6),
                new AbstractMap.SimpleImmutableEntry<>("R7", 7),
                new AbstractMap.SimpleImmutableEntry<>("R8", 8),
                new AbstractMap.SimpleImmutableEntry<>("R9", 9),
                new AbstractMap.SimpleImmutableEntry<>("R10", 10),
                new AbstractMap.SimpleImmutableEntry<>("R11", 11),
                new AbstractMap.SimpleImmutableEntry<>("R12", 12),
                new AbstractMap.SimpleImmutableEntry<>("R13", 13),
                new AbstractMap.SimpleImmutableEntry<>("R14", 14),
                new AbstractMap.SimpleImmutableEntry<>("R15", 15),
                new AbstractMap.SimpleImmutableEntry<>("SP", 0),
                new AbstractMap.SimpleImmutableEntry<>("LCL", 1),
                new AbstractMap.SimpleImmutableEntry<>("ARG", 2),
                new AbstractMap.SimpleImmutableEntry<>("THIS", 3),
                new AbstractMap.SimpleImmutableEntry<>("THAT", 4),
                new AbstractMap.SimpleImmutableEntry<>("SCREEN", 16384),
                new AbstractMap.SimpleImmutableEntry<>("KBD", 24576))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void addEntry(String symbol, int address) {
        symbolT.put(symbol, address);
    }

    public boolean contains(String symbol) {
        return symbolT.containsKey(symbol);
    }

    public int getAddress(String symbol) {
        return symbolT.get(symbol);
    }
}