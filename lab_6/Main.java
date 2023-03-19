import java.util.Scanner;

class Main {
    private static final int RECORD_NUM = 4;
        
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numRecords = scanner.nextInt();
        scanner.nextLine(); //Important if your input ends with “\n”!

        Roster roster = new Roster("class");
        String output = "";

        while (scanner.hasNext()) {
            // reading in the records
            for (int i = 0; i < numRecords; i++) {
                for (int j = 0; j < RECORD_NUM; j++) {
                    String stuID = scanner.next();
                    String moduleCode = scanner.next();
                    String assessTitle = scanner.next();
                    String grade = scanner.next();
                    roster.add(stuID, moduleCode, assessTitle, grade);
                }
            }
    
            // reading in the queries
            while (true) {
                try {
                    String stuID = scanner.next();
                    String moduleCode = scanner.next();
                    String assessTitle = scanner.next();
                    output = output + roster.getGrade(stuID, moduleCode, assessTitle) + "\n";
                } catch (java.util.NoSuchElementException e) {
                    break;
                }
            }
        }
        
        System.out.println(output);
    }
}
