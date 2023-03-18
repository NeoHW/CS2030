import java.util.Optional;
import java.util.Map;

class KeyableMap<V extends Keyable> implements Keyable {
    private final String name;
    private final ImmutableMap<String, V> map;

    KeyableMap(String name) {
        this.name = name;
        this.map = new ImmutableMap<String, V>();
    }

    KeyableMap(String name, ImmutableMap<String, V> map) {
        this.name = name;
        this.map = map;
    }

    @Override
    public String getKey() {
        return this.name;
    }

    public ImmutableMap<String, V> getMap() {
        return this.map;
    }

    public KeyableMap<V> put(V item) {
        return new KeyableMap<V>(this.name, this.map.put(item.getKey(), item));
    }
    
    public Optional<V> get(String key) {
        return this.map.get(key);
    }

    @Override
    public String toString() {
        String output = this.name + ": {";
        boolean haveMap = false;
        for (Map.Entry<String, V> e : map) {
            output = output + e.getValue() + ", ";
            haveMap = true;
        }
        if (haveMap) {
            output = output.substring(0, output.length() - 2);
        }
        output = output + "}";
        return output;
    }
}
