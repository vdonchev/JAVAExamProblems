import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserLogs {

    static TreeMap<String, LinkedHashMap<String, Integer>> log;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        log = new TreeMap<>();

        String command;
        while (!(command = scanner.nextLine()).equals("end")) {

            Pattern pattern = Pattern.compile("IP=(?<ip>.*?) message='[^\\']*' user=(?<user>.{3,50})");
            Matcher matcher = pattern.matcher(command);
            while (matcher.find()) {
                String ipAddress = matcher.group("ip");
                String username = matcher.group("user");

                if (!log.containsKey(username)) {
                    log.put(username, new LinkedHashMap<>());
                }

                if (!log.get(username).containsKey(ipAddress)) {
                    log.get(username).put(ipAddress, 0);
                }

                int ipOccurrences = log.get(username).get(ipAddress);
                log.get(username).put(ipAddress, ipOccurrences + 1);
            }
        }

        for (String entry : log.keySet()) {

            System.out.printf("%s: \n", entry);

            boolean first = true;
            for (Map.Entry<String, Integer> ip : log.get(entry).entrySet()) {
                if (first) {
                    System.out.printf("%s => %d", ip.getKey(), ip.getValue());
                    first = false;
                } else {
                    System.out.printf(", %s => %d", ip.getKey(), ip.getValue());
                }
            }

            System.out.printf(".\n");
        }
    }
}