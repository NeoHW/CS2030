import java.util.Optional;

class Module implements Keyable {
    private final String moduleName;
    private final ImmutableMap<String, Assessment> map;

    Module(String moduleName) {
        this.moduleName = moduleName;
        this.map = new ImmutableMap<String, Assessment>;
    }

    @Override
    public String getkey() {

    }

    public Module put(Assessment assessment) {

    }
    
    public Optional<Assessment> get(String key) {

    }
    
    @Override
    public String toString() {

    }
}
