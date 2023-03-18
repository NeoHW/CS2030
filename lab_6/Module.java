import java.util.Optional;
import java.util.Map;

class Module implements Keyable {
    private final String moduleName;
    private final ImmutableMap<String, Assessment> map;

    Module(String moduleName) {
        this.moduleName = moduleName;
        this.map = new ImmutableMap<String, Assessment>();
    }

    Module(String moduleName, ImmutableMap<String, Assessment> map) {
        this.moduleName = moduleName;
        this.map = map;
    }

    @Override
    public String getKey() {
        return this.moduleName;
    }

    public Module put(Assessment assessment) {
        return new Module(this.moduleName, this.map.put(assessment.getKey(), assessment));
    }
    
    public Optional<Assessment> get(String key) {
        return this.map.get(key);
    }
    
    @Override
    public String toString() {
        String output = this.moduleName + ": {";
        boolean haveMap = false;
        for (Map.Entry<String,Assessment> e : map) {
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
