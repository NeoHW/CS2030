import java.util.Optional;
import java.util.Map;

class Student implements Keyable {
    private final String studentName;
    private final ImmutableMap<String, Module> map;

    Student(String studentName) {
        this.studentName = studentName;
        this.map = new ImmutableMap<String, Module>();
    }

    Student(String studentName, ImmutableMap<String, Module> map) {
        this.studentName = studentName;
        this.map = map;
    }

    @Override
    public String getKey() {
        return this.studentName;
    }

    public Student put(Module module) {
        return new Student(this.studentName, this.map.put(module.getKey(), module));
    }
    
    public Optional<Module> get(String key) {
        return this.map.get(key);
    }
    
    @Override
    public String toString() {
        String output = this.studentName + ": {";
        boolean haveMap = false;
        for (Map.Entry<String,Module> e : map) {
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
