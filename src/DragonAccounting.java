import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class DragonAccounting {
    static BigDecimal capital;
    static int elapsedDays;
    static ArrayList<BigDecimal> salaries;
    static ArrayList<Long> workerNumber;

    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);
        elapsedDays = 1;
        salaries = new ArrayList<>();
        workerNumber = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        capital = new BigDecimal(scanner.nextLine());
        String command;
        while (!(command = scanner.nextLine()).equals("END")) {
            String[] tokens = command.trim().split(";");
            long hiredWorkers = Long.parseLong(tokens[0]);
            long firedWorkers = Long.parseLong(tokens[1]);
            BigDecimal monthlySalary = new BigDecimal(tokens[2]);

            hireWorkers(hiredWorkers, monthlySalary); // hire
            raiseSalaries(); // rise - fixed by code of https://github.com/EBojilova <- Thanks!
            paySalaries(); // pay
            fireWorkers(firedWorkers); // fire
            calcAdditionalIncomeExpense(tokens); // additional money
            if (checkForBankruptcy()) return; // bankruptcy?

            elapsedDays++;
        }

        printResults();
    }

    private static void printResults() {
        long numberOfWorkers = workerNumber.stream()
                .filter(s -> s > 0)
                .mapToLong(value -> value)
                .sum(); // count all active workers

        System.out.println(
                numberOfWorkers + " " + capital.setScale(4, BigDecimal.ROUND_DOWN)); // print result
    }

    private static boolean checkForBankruptcy() {
        if (capital.compareTo(new BigDecimal(0)) < 0) {
            System.out.println("BANKRUPTCY: " + capital.abs().setScale(4, BigDecimal.ROUND_DOWN)); // step 6
            return true;
        }
        return false;
    }

    private static void calcAdditionalIncomeExpense(String[] tokens) {
        for (int i = 3; i < tokens.length; i++) {
            String[] subTokens = tokens[i].split(":");

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

    private static void fireWorkers(long firedWorkers) {
        for (int i = 0; i < elapsedDays; i++) {
            if (workerNumber.get(i) > 0) {
                long remainingWorkers = workerNumber.get(i) - firedWorkers;
                if (remainingWorkers < 0) {
                    firedWorkers -= workerNumber.get(i);
                    workerNumber.set(i, 0l);
                } else {
                    workerNumber.set(i, workerNumber.get(i) - firedWorkers);
                    break;
                }
            }
        }
    }

    private static void paySalaries() {
        if(elapsedDays % 30 == 0) {
            for (int i = 0; i < salaries.size(); i++) {
                long currentWorkers = workerNumber.get(i);

                if(currentWorkers > 0) {
                    BigDecimal salariesToPay;
                    BigDecimal currentSalary = salaries.get(i);

                    int workingDays = elapsedDays - i;
                    if (workingDays > 30) {
                        workingDays = 30;
                    }

                    salariesToPay = currentSalary
                            .divide(new BigDecimal(30), 9, BigDecimal.ROUND_UP)
                            .setScale(7, BigDecimal.ROUND_DOWN)
                            .multiply(new BigDecimal(workingDays))
                            .multiply(new BigDecimal(workerNumber.get(i)));

                    capital = capital.subtract(salariesToPay);
                }
            }
        }
    }

    private static void raiseSalaries() {
        if (elapsedDays >= 365) {
            int days = elapsedDays - 365;
            while (days >= 0) {
                if (workerNumber.get(days) > 0) {
                    BigDecimal currentSalary = salaries.get(days);
                    BigDecimal increasedSalary = currentSalary.multiply(new BigDecimal(1.006));
                    salaries.set(days, increasedSalary);
                }

                days -= 365;
            }
        }
    }

    private static void hireWorkers(long hiredWorkers, BigDecimal monthlySalary) {
        workerNumber.add(hiredWorkers);
        salaries.add(monthlySalary);
    }
}