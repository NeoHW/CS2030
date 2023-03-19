import java.util.Optional;

public class Roster extends KeyableMap<Student> {
    
    Roster(String rosterName) {
        super(rosterName);
    }

    Roster(String rosterName, ImmutableMap<String, Student> map) {
        super(rosterName, map);
    }

    @Override
    public Roster put(Student student) {
        return new Roster(this.getKey(), super.getMap().put(student.getKey(), student));
    }

    @Override
    public String getKey() {
        return super.getKey();
    }
    
    // level 4
    String getGrade(String stuID, String moduleCode, String assessTitle) {
        Optional<String> optionalOutput = super.get(stuID)
            .flatMap(x -> x.get(moduleCode))
            .flatMap(x -> x.get(assessTitle))
            .map(x -> x.getGrade());

        String output = String.format("No such record: %s %s %s", stuID, moduleCode, assessTitle);
        return optionalOutput.orElse(output);
    }

    // level 5
    Roster add(String stuID, String moduleCode, String assessTitle, String grade) {
        
        Optional<Student> optionalStudent = super.getMap().get(stuID);
        Student student = optionalStudent.orElse(new Student(stuID));
        
        Optional<Module> optionalModule = student.getMap().get(moduleCode);
        Module module = optionalModule.orElse(new Module(moduleCode));

        module = module.put(new Assessment(assessTitle, grade));
        student = student.put(module);

        return this.put(student);
    }
}
