import java.util.*;

public class DragonArmy {

    static LinkedHashMap<String, TreeMap<String, Double[]>> dragons;

    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);
        Scanner scanner = new Scanner(System.in);

        int numOfDragons = scanner.nextInt();
        scanner.nextLine();

        dragons = new LinkedHashMap<>();
        for (int i = 0; i < numOfDragons; i++) {

            String[] tokens = scanner.nextLine().trim().split(" ");
            String type= tokens[0];
            String name = tokens[1];

            Double damage = (!tokens[2].equals("null")) ? Double.parseDouble(tokens[2]) : 45d;
            Double health = (!tokens[3].equals("null")) ? Double.parseDouble(tokens[3]): 250d;
            Double armor = (!tokens[4].equals("null")) ? Double.parseDouble(tokens[4]) : 10d;

            if(!dragons.containsKey(type)) {
                dragons.put(type, new TreeMap<>());
            }

            if(!dragons.get(type).containsKey(name)) {
                dragons.get(type).put(name, new Double[3]);
            }

            dragons.get(type).put(name, new Double[] {damage, health, armor});
        }

        Set dragonTypes = dragons.keySet();

        for (Object dragonType : dragonTypes) {

            Set dragonNames = dragons.get(dragonType).keySet();
            TreeMap<String, Double[]> avg = dragons.get(dragonType);

            System.out.println(dragonType + "::(" + calculateAverage(avg) + ")");

            for (Object dragonName : dragonNames) {
                System.out.println("-" + dragonName + " -> " + printDragonStats(dragons.get(dragonType).get(dragonName)));
            }
        }
    }

    private static String calculateAverage(TreeMap<String, Double[]> avg) {
        Double avgDamage = 0d;
        Double avgHealth = 0d;
        Double avgArmour = 0d;
        int numOfItems = avg.size();

        for (Double[] doubles : avg.values()) {
            avgDamage += doubles[0];
            avgHealth += doubles[1];
            avgArmour += doubles[2];
        }

        avgDamage /= numOfItems;
        avgHealth/= numOfItems;
        avgArmour /= numOfItems;

        String out = String.format("%.2f/%.2f/%.2f", avgDamage, avgHealth, avgArmour);
        return out;
    }

    private static String printDragonStats(Double[] stats) {
        String out = String.format("damage: %.0f, health: %.0f, armor: %.0f", stats[0], stats[1], stats[2]);
        return out;
    }
}