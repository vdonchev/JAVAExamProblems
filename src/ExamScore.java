import java.util.Scanner;
import java.util.TreeMap;

public class ExamScore {

    static TreeMap<Integer, TreeMap<String, Double> studentsStats;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
           int num = scanner.nextLine();
        }

        studentsStats = new TreeMap<>();

        String command;
        while (!(command = scanner.nextLine()).contains("-")) {
            String[] tokens = command
                    .substring(1)
                    .substring(0, command.length() - 1)
                    .split("\\s*\\|\\s*");

            String studentName = tokens[0].trim();
            int studentScore = Integer.parseInt(tokens[1].trim());
            double studentGrade = Double.parseDouble(tokens[2].trim());

            if (!studentsStats.containsKey(studentScore)) {
                studentsStats.put(studentScore, new TreeMap<>());
            }

            studentsStats.get(studentScore).put(studentName, studentGrade);
        }

        for (Integer score : studentsStats.keySet()) {
            System.out.printf(
                    "%d -> %s; avg=%.2f\n",
                    score,
                    studentsStats.get(score).keySet(),
                    studentsStats.get(score).values().stream()
                            .mapToDouble(value -> value)
                            .average()
                            .getAsDouble());
        }
    }
}
