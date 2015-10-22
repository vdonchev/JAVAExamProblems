import java.util.*;

public class Weightlifting {

    static TreeMap<String, TreeMap<String, Long>> stats;
    public static void main(String[] args) {
        stats = new TreeMap<>();

        Scanner scanner = new Scanner(System.in);
        int lines = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < lines; i++) {
            String[] tokens = scanner.nextLine().trim().split(" ");

            String name = tokens[0];
            String exercise = tokens[1];
            Long weight = Long.parseLong(tokens[2]);
            
            if(!stats.containsKey(name)) {
                stats.put(name, new TreeMap<>());
            }

            if(!stats.get(name).containsKey(exercise)) {
                stats.get(name).put(exercise, 0L);
            }

            Long currentWeight = stats.get(name).get(exercise);
            stats.get(name).put(exercise, weight + currentWeight);
        }

        Set<String> names = stats.keySet();

        for (String name : names) {
            System.out.printf("%s : ", name);

            TreeMap<String, Long> res = stats.get(name);
            Set<String> exerc = res.keySet();
            boolean first = true;
            for (String s : exerc) {
                if(first) {
                    System.out.printf("%s - %d kg", s, res.get(s));
                    first = false;
                } else {
                    System.out.printf(", %s - %d kg", s, res.get(s));
                }
            }

            System.out.println();
        }
    }
}