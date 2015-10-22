import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleExpression {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine().trim().replaceAll("\\s+", "");

        String[] expression = input.split("[-+]");
        List<BigDecimal> nums = new ArrayList<>();

        for (String anExpression : expression) {
            nums.add(new BigDecimal(anExpression));
        }

        BigDecimal expressionResult = nums.get(0);
        nums.remove(0);

        Pattern pattern = Pattern.compile("[+-]");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            expressionResult = doMath(expressionResult, nums.get(0), matcher.group());
            nums.remove(0);
        }

        System.out.println(expressionResult);
    }

    private static BigDecimal doMath(BigDecimal expressionResult, BigDecimal operand, String operator) {
        BigDecimal res = expressionResult;

        switch (operator) {
            case "+":
                res = res.add(operand);
                break;
            default:
                res = res.subtract(operand);
                break;
        }

        return res;
    }
}