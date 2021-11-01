import java.util.ArrayList;

public class Fraction {
    private int wholePart;
    private short fractionalPart;
    private String operator;

    public String getOperator() {
        return operator;
    }

    public Fraction(int wholePart, short fractionalPart) {
        this.wholePart = wholePart;
        this.fractionalPart = fractionalPart;
    }

    public Fraction(String operator) {
        this.operator = operator;
    }

    public Fraction(Fraction fr, String operator) {
        this(operator);
        this.wholePart = fr.wholePart;
        this.fractionalPart = fr.fractionalPart;
    }

    public static int[] addition(Fraction num1, Fraction num2) {
        int num1WholePart = num1.wholePart;
        int num2WholePart = num2.wholePart;
        int num1FractionalPart = num1.fractionalPart;
        int num2FractionalPart = num2.fractionalPart;
        int count1 = sumOfDigits(1, num1FractionalPart);
        int count2 = sumOfDigits(1, num2FractionalPart);
        if (num1WholePart < 0) num1FractionalPart *= -1;
        if (num2WholePart < 0) num2FractionalPart *= -1;
        if (count1 > count2) num2FractionalPart = getNewFract((count1 - count2), num2FractionalPart);
        if (count2 > count1) {
            num1FractionalPart = getNewFract((count2 - count1), num1FractionalPart);
            count1 += (count2 - count1);
        }
        int wholePart = num1WholePart + num2WholePart;
        int fractPart = num1FractionalPart + num2FractionalPart;
        int count;
        if (fractPart < 0) count = sumOfDigits(1, -1 * fractPart);
        else count = sumOfDigits(1, fractPart);
        if (count > count1) {
            ArrayList<Integer> lst = new ArrayList<>(convertFractToList(fractPart, new ArrayList<>()));
            int wholePartForSum = getWholePart(lst.get(0), 1, (count - count1), lst);
            wholePart += wholePartForSum;
            int count3 = sumOfDigits(1, (short) wholePartForSum);
            fractPart = getFractionPart(lst.get(count3), count3, lst);
        }
        int[] res = {wholePart, fractPart};
        return res;
    }

    public static int[] subtraction(Fraction num1, Fraction num2) {
        int num1WholePart = num1.wholePart;
        int num2WholePart = num2.wholePart;
        int num1FractionalPart = num1.fractionalPart;
        int num2FractionalPart = num2.fractionalPart;
        int count1 = sumOfDigits(1, num1FractionalPart);
        int count2 = sumOfDigits(1, num2FractionalPart);
        if (num1WholePart < 0) num1FractionalPart *= -1;
        if (num2WholePart < 0) num2FractionalPart *= -1;
        if (count1 > count2) num2FractionalPart = getNewFract((count1 - count2), num2FractionalPart);
        if (count2 > count1) {
            num1FractionalPart = getNewFract((count2 - count1), num1FractionalPart);
            count1 += (count2 - count1);
        }
        int wholePart = num1WholePart - num2WholePart;
        int fractPart = num1FractionalPart - num2FractionalPart;
        int count;
        if (fractPart < 0) count = sumOfDigits(1, -1 * fractPart);
        else count = sumOfDigits(1, fractPart);
        if (count > count1) {
            ArrayList<Integer> lst = new ArrayList<>(convertFractToList(fractPart, new ArrayList<>()));
            int wholePartOfSubtr = getWholePart(lst.get(0), 1, (count - count1), lst);
            wholePart -= wholePartOfSubtr;
            int count3 = sumOfDigits(1, wholePartOfSubtr);
            fractPart = getFractionPart(lst.get(count3), count3, lst);
        }
        int[] res = {wholePart, fractPart};
        return res;
    }

    private static int sumOfDigits(int count, int fractionalPart) {
        int counter = count;
        int fractPart = fractionalPart;
        if (fractPart < 10) {
            return counter;
        }
        fractPart /= 10;
        counter++;
        return sumOfDigits(counter, fractPart);
    }

    private static int getNewFract(int count, int fractionalPart) {
        int fract = fractionalPart;
        int counter = count;
        if (counter > 0) {
            fract *= 10;
            --counter;
            return getNewFract(counter, fract);
        }
        return fract;
    }

    private static ArrayList<Integer> convertFractToList(int frPart, ArrayList<Integer> digits) {
        if (frPart == 0) return digits;
        digits.add(0, frPart % 10);
        frPart /= 10;
        return convertFractToList(frPart, digits);
    }

    private static int getWholePart(int wholePart, int counter, int count, ArrayList<Integer> lst) {
        if (counter == count) {
            return wholePart;
        }
        counter++;
        wholePart = 10 * wholePart + lst.get(count++);
        return getWholePart(wholePart, counter, count, lst);
    }

    private static int getFractionPart(int fractPart, int counter, ArrayList<Integer> lst) {
        if (counter == lst.size() - 1) {
            return fractPart;
        }
        counter++;
        fractPart = 10 * fractPart + lst.get(counter);
        return getFractionPart(fractPart, counter, lst);
    }
}
