import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            return;
        }
        String[] str = args[0].split(",");
        int wholePart = Integer.parseInt(str[0]);
        if (Integer.parseInt(str[1]) > Short.MAX_VALUE || Integer.parseInt(str[1]) < 0) {
            System.out.println("Fractional part value is out of range or less then 0!");
            return;
        }
        short fractPart = Short.parseShort(str[1]);
        Fraction fraction1 = new Fraction(wholePart, fractPart);
        Fraction fractionOperation = new Fraction(fraction1, args[1]);
        str = args[2].split(",");
        wholePart = Integer.parseInt(str[0]);
        fractPart = Short.parseShort(str[1]);
        Fraction fraction2 = new Fraction(wholePart, fractPart);
        if (fractionOperation.getOperator().equals("+") || fractionOperation.getOperator().equalsIgnoreCase("plus")) {
            System.out.println(Arrays.toString(Fraction.addition(fraction1, fraction2)));
        } else if (fractionOperation.getOperator().equals("-") || fractionOperation.getOperator().equalsIgnoreCase("minus")) {
            System.out.println(Arrays.toString(Fraction.subtraction(fraction1, fraction2)));
        }
    }
}
