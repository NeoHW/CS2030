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
        // if no such student exists
        if (optionalStudent.isEmpty()) {
            return new Roster(this.getKey(),
                super.getMap().put(stuID,
                    new Student(stuID, new ImmutableMap<String, Module>().put(stuID,
                    new Module(moduleCode, new ImmutableMap<String,Assessment>().put(moduleCode,
                    new Assessment(assessTitle, grade)))))));
        }

        // now we know there is a student
        Student student = optionalStudent.get();
        
        Optional<Module> optionalModule = student.getMap().get(moduleCode);
        Module module = optionalModule.orElse(
            new Module(moduleCode, new ImmutableMap<String,Assessment>()));

        return new Roster(this.getKey(),
        super.getMap().put(stuID,
            student.put(
            module.put(
            new Assessment(assessTitle, grade)))));

    }
}
