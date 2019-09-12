import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Map<String, Integer> symbolT;

    public SymbolTable() {
        this.symbolT = new HashMap<String, Integer>() {};
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