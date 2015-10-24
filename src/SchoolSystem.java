import java.util.*;

public class SchoolSystem {

    static TreeMap<String, TreeMap<String, ArrayList<Integer>>> gradesLog;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int numberOfCommands = scanner.nextInt();
        scanner.nextLine();

        gradesLog = new TreeMap<>();

        for (int i = 0; i < numberOfCommands; i++) {
            String[] tokens = scanner.nextLine().trim().split(" ");

            String fullName = tokens[0] + " " + tokens[1];
            String subject = tokens[2];
            int currentGrade = Integer.parseInt(tokens[3]);

            if (!gradesLog.containsKey(fullName)) {
                gradesLog.put(fullName, new TreeMap<>());
            }

            if (!gradesLog.get(fullName).containsKey(subject)) {
                gradesLog.get(fullName).put(subject, new ArrayList<>());
            }

            gradesLog.get(fullName).get(subject).add(currentGrade);
        }

        for (String studentName : gradesLog.keySet()) {
            System.out.printf("%s: ", studentName);

            ArrayList<String> allAverageGrades = new ArrayList<>();
            for (Map.Entry<String, ArrayList<Integer>> subject : gradesLog.get(studentName).entrySet()) {
                allAverageGrades.add(String.format(
                        "%s - %.2f",
                        subject.getKey(),
                        subject.getValue().stream()
                                .mapToDouble(value -> value)
                                .average()
                                .getAsDouble()));
            }

            System.out.printf("[%s]\n", String.join(", ", allAverageGrades));
        }
    }
}