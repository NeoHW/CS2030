class Student extends KeyableMap<Module> {

    Student(String studentName) {
        super(studentName);
    }

    Student(String studentName, ImmutableMap<String, Module> map) {
        super(studentName, map);
    }

    // must override put method ,, why?
    @Override
    public Student put(Module module) {
        return new Student(this.getKey(), super.getMap().put(module.getKey(), module));
    }

    @Override
    public String getKey() {
        return super.getKey();
    }
}
