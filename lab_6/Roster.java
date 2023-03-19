import java.util.Optional;

public class Roster extends KeyableMap<Student> {
    
    Roster(String RosterName) {
        super(RosterName);
    }

    Roster(String RosterName, ImmutableMap<String, Student> map) {
        super(RosterName, map);
    }

    @Override
    public Roster put(Student student) {
        return new Roster(this.getKey(), super.getMap().put(student.getKey(), student));
    }

    @Override
    public String getKey() {
        return super.getKey();
    }

    public String getGrade(String stuID, String moduleCode, String assessTitle) {
        Optional<String> optionalOutput = super.get(stuID).
            flatMap(x -> x.get(moduleCode)).
            flatMap(x -> x.get(assessTitle)).
            map(x -> x.getGrade());

        String output = String.format("No such record: %s %s %s", stuID, moduleCode, assessTitle);
        return optionalOutput.orElse(output);
    }
}
