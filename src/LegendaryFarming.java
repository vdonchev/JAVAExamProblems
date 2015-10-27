import java.util.*;

public class LegendaryFarming {

    public static int MAX_NUM_OF_ITERATIONS = 10;
    public static int ITEM_PRICE = 250;

    public static void main(String[] args) {
        List<ValuableMaterial> valuableItems = new ArrayList<>();
        valuableItems.add(new ValuableMaterial("shards", 0, "Shadowmourne"));
        valuableItems.add(new ValuableMaterial("fragments", 0, "Valanyr"));
        valuableItems.add(new ValuableMaterial("motes", 0, "Dragonwrath"));

        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> junk = new TreeMap<>();

        for (int i = 0; i < MAX_NUM_OF_ITERATIONS; i++) {
            String[] tokens = scanner.nextLine().toLowerCase().split(" ");

            for (int j = 1; j < tokens.length; j += 2) {
                String material = tokens[j];
                int materialCount = Integer.parseInt(tokens[j - 1]);
                int currentMaterialCount;

                if(material.equals("shards")
                        || material.equals("fragments")
                        || material.equals("motes")) {

                    for (int k = 0; k < valuableItems.size(); k++) {
                        if(valuableItems.get(k).getName().equals(material)) {
                            currentMaterialCount = valuableItems.get(k).getCount();
                            valuableItems.get(k).setCount(currentMaterialCount + materialCount);

                            if(valuableItems.get(k).getCount() >= ITEM_PRICE) {
                                System.out.println(valuableItems.get(k).getLegendaryItem() + " obtained!");

                                valuableItems.get(k).setCount(valuableItems.get(k).getCount() - ITEM_PRICE);
                                printResult(valuableItems, junk);
                                return;
                            }
                        }
                    }

                } else {
                    if(!junk.containsKey(material)){
                        junk.put(material, 0);
                    }

                    currentMaterialCount = junk.get(material);
                    junk.put(material, materialCount + currentMaterialCount);
                }
            }
        }

        printResult(valuableItems, junk);
    }

    private static void printResult(List<ValuableMaterial> valuableItems, Map<String, Integer> junk) {
        valuableItems.stream()
                .sorted()
                .forEach(valuableMaterial -> System.out.println(valuableMaterial.getName() + ": " + valuableMaterial.getCount()));

        for (Map.Entry<String, Integer> junkMaterial : junk.entrySet()) {
            System.out.println(junkMaterial.getKey() + ": " + junkMaterial.getValue());
        }
    }
}

class ValuableMaterial implements Comparable<ValuableMaterial> {
    private String name;
    private int count;
    private String legendaryItem;

    ValuableMaterial(String name, int count, String legendaryItem) {
        this.name = name;
        this.count = count;
        this.legendaryItem = legendaryItem;
    }

    @Override
    public int compareTo(ValuableMaterial other) {
        if (this.count > other.count) {
            return -1;
        }

        if (this.count < other.count) {
            return 1;
        }

        return this.name.compareTo(other.name);
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getLegendaryItem() {
        return legendaryItem;
    }
}