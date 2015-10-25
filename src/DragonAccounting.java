import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;

public class DragonAccounting {

    // 60/100 points - incomplete -> :(
    static BigDecimal capital;
    static int elapsedDays;
    static LinkedList<Day> workers;

    public static void main(String[] args) {

        Locale.setDefault(Locale.ROOT);

        workers = new LinkedList<>();
        workers.add(new Day(0, BigDecimal.ZERO)); // set index 0 as unavailable
        elapsedDays = 0;

        Scanner scanner = new Scanner(System.in);

        capital = new BigDecimal(scanner.nextLine());

        String command;
        while (!(command = scanner.nextLine()).equals("END")) {
            elapsedDays++;

            String[] tokens = command.trim().split(";");
            long hiredWorkers = Long.parseLong(tokens[0]);
            long firedWorkers = Long.parseLong(tokens[1]);
            BigDecimal monthlySalary = new BigDecimal(tokens[2]);

            hireWorkers(hiredWorkers, monthlySalary); // step 1

            if (elapsedDays > 365) {
                riseSalaries(); // step 2
            }

            if(elapsedDays % 30 == 0) {
                paySalaries(); // step 3
            }

            fireWorkers(firedWorkers); // step 4

            for (int i = 3; i < tokens.length; i++) {
                calcIncomeExpense(tokens[i]); // step 5
            }

            if (capital.compareTo(new BigDecimal(0)) < 0) {
                System.out.println("BANKRUPTCY: " + capital.abs().setScale(4, BigDecimal.ROUND_DOWN)); // step 6
                return;
            }
        }

        long numberOfWorkers = workers.stream()
                .filter(s -> s.getWorkers() > 0)
                .mapToLong(Day::getWorkers)
                .sum(); // count all active workers

        System.out.println(
                numberOfWorkers + " " + capital.setScale(4, BigDecimal.ROUND_DOWN)); // print result

//        System.out.println();
//        for (int i = 1; i < workers.size(); i++) {
//            System.out.println(workers.get(i).getWorkers() + " => " + workers.get(i).getSalary());
//        }
    }

    private static void fireWorkers(long firedWorkers) {
        for (int i = 1; i < elapsedDays; i++) {
            if (workers.get(i).getWorkers() > 0) {
                long remainingWorkers = workers.get(i).getWorkers() - firedWorkers;
                if (remainingWorkers < 0) {
                    firedWorkers -= workers.get(i).getWorkers();
                    workers.get(i).setWorkers(0);
                } else {
                    workers.get(i).setWorkers(workers.get(i).getWorkers() - firedWorkers);
                    break;
                }
            }
        }
    }

    private static void paySalaries() {
        for (int i = 1; i < workers.size(); i++) {

            long currentWorkers = workers.get(i).getWorkers();

            if(currentWorkers > 0) {
                BigDecimal salariesToPay;
                BigDecimal currentSalary = workers.get(i).getSalary();

                int workingDays = elapsedDays - i;
                if (workingDays >= 29) {
                    workingDays = 30;
                } else {
                    workingDays = workingDays + 1;
                }

                salariesToPay = currentSalary
                        .divide(new BigDecimal(30), 9, BigDecimal.ROUND_UP)
                        .setScale(7, BigDecimal.ROUND_DOWN)
                        .multiply(new BigDecimal(workingDays))
                        .multiply(new BigDecimal(workers.get(i).getWorkers()));

                capital = capital.subtract(salariesToPay);
            }
        }
    }

    private static void riseSalaries() {
        int days = elapsedDays - 365;
        while (days > 0) {
            if (workers.get(days).getWorkers() > 0) {
                BigDecimal currentSalary = workers.get(days).getSalary();
                BigDecimal increasedSalary = currentSalary.multiply(new BigDecimal(1.006));
                workers.get(days).setSalary(increasedSalary);
            }

            days -= 365;
        }
    }

    private static void hireWorkers(long hiredWorkers, BigDecimal monthlySalary) {
        workers.add(new Day(hiredWorkers, monthlySalary));
    }

    private static void calcIncomeExpense(String token) {
        String[] subTokens = token.split(":");

        String objectName = subTokens[0];
        BigDecimal objectValue = new BigDecimal(subTokens[1]);

        switch (objectName) {
            case "Previous years deficit":
            case "Machines":
            case "Taxes":
                capital = capital.subtract(objectValue);
                break;
            default:
                capital = capital.add(objectValue);
                break;
        }
    }
}

class Day {

    private long workers;

    private BigDecimal salary;

    Day(long workers, BigDecimal salary) {
        this.workers = workers;
        this.salary = salary;
    }

    public long getWorkers() {
        return workers;
    }

    public void setWorkers(long workers) {
        this.workers = workers;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}