class Module extends KeyableMap<Assessment> {

    Module(String moduleName) {
        super(moduleName);
    }

    Module(String moduleName, ImmutableMap<String, Assessment> map) {
        super(moduleName, map);
    }

    // must override put method ,, why?
    @Override
    public Module put(Assessment assessment) {
        return new Module(this.getKey(), super.getMap().put(assessment.getKey(), assessment));
    }

    @Override
    public String getKey() {
        return super.getKey();
    }
}
