import java.util.Scanner;

class Main {
        
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numRecords = scanner.nextInt();
        scanner.nextLine(); //Important if your input ends with “\n”!

        Roster roster = new Roster("class");
        String output = "";

        // reading in the records
        for (int i = 0; i < numRecords; i++) {
            String stuID = scanner.next();
            String moduleCode = scanner.next();
            String assessTitle = scanner.next();
            String grade = scanner.next();
            roster = roster.add(stuID, moduleCode, assessTitle, grade);
            scanner.nextLine();
        }

        while (scanner.hasNext()) {
            String stuID = scanner.next();
            String moduleCode = scanner.next();
            String assessTitle = scanner.next();
            output = output + roster.getGrade(stuID, moduleCode, assessTitle) + "\n";
            scanner.nextLine();
        }

        scanner.close();
        System.out.print(output);
    }
}
