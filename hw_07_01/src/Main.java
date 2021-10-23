import utils.Constants;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static java.util.Arrays.*;

public class Main {
    private static void parseFile(boolean[] arrOfBooleans, String[][] arrOfStrings) throws IOException {
        String line;
        String[] tmp;
        int counter = 0;
        BufferedReader reader = new BufferedReader(new FileReader(Constants.PATH));

        while ((line = reader.readLine()) != null) {
            tmp = line.split(",");
            arrOfBooleans[counter] = Boolean.parseBoolean(tmp[tmp.length - 1]);
            arrOfStrings[counter++] = copyOf(tmp, tmp.length - 1);
        }
    }

    public static double phi(int[] table) {
        return ((table[3] * table[0] - table[2] * table[1]) /
                Math.sqrt((table[2] + table[3]) *
                        (table[0] + table[1]) *
                        (table[1] + table[3]) *
                        (table[0] + table[2])
                ));
    }

    public static int[] tableFor(String[][] arrOfStrings, boolean[] arrOfBooleans, String event) {
        int[] table = {0, 0, 0, 0};

        for (int i = 0; i < arrOfBooleans.length; i++) {
            int index = 0;
            if (contains(arrOfStrings[i], event)) index += 1;
            if (arrOfBooleans[i]) index += 2;
            table[index] += 1;
        }

        return table;
    }

    public static boolean contains(String[] arrOfStrings, String event) {
        for (String x : arrOfStrings) {
            if (x.equals(event)) return true;
        }
        return false;
    }

    public static String[] doubleArrayToArray(String[][] arrOfStrings) {
        String[] newArrOfStrings = new String[0];
        int index = 0;
        for (String[] current : arrOfStrings) {
            newArrOfStrings = copyOf(newArrOfStrings, newArrOfStrings.length + current.length);
            System.arraycopy(current, 0, newArrOfStrings, index, current.length);
            index += current.length;
        }

        return newArrOfStrings;
    }

    public static String[] sortArray(String[] arr) {
        Arrays.sort(arr);
        return arr;
    }

    public static int sizeOfArrWithoutDuplicates(String[] arr, int size) {
        if (size == 0 || size == 1) return size;
        String[] temp = new String[size];
        int j = 0;
        for (int i = 0; i < size - 1; i++) {
            if (!arr[i].equals(arr[i + 1])) temp[j++] = arr[i];
        }
        temp[j++] = arr[size - 1];
        if (j >= 0) System.arraycopy(temp, 0, arr, 0, j);
        return j;
    }

    public static String[] events(String[] arr) {
        int size = sizeOfArrWithoutDuplicates(arr, arr.length);
        String[] newArr = new String[size];
        System.arraycopy(arr, 0, newArr, 0, size);
        return newArr;
    }

    public static String[][] correlation(String[] events, String[][] arrOfStrings, boolean[] arrOfBooleans) {
        String[][] correlation = new String[events.length][2];
        for (int i = 0; i < correlation.length; i++) {
            correlation[i][0] = events[i];
            correlation[i][1] = String.valueOf(phi(tableFor(arrOfStrings, arrOfBooleans, events[i])));
        }
        return correlation;
    }

    public static String[][] finalCorrelation(String[][] correlation) {
        int size = 0;
        for (String[] strings : correlation) {
            if (Double.parseDouble(strings[1]) > 0.1 || Double.parseDouble(strings[1]) < -0.1) {
                size++;
            }
        }
        String[][] finalCorrelation = new String[size][2];
        int count = 0;

        for (String[] strings : correlation) {
            if (Double.parseDouble(strings[1]) > 0.1 || Double.parseDouble(strings[1]) < -0.1) {
                finalCorrelation[count][0] = strings[0];
                finalCorrelation[count][1] = strings[1];
                count++;
            }
        }

        return finalCorrelation;
    }

    public static String[][] sort2DArray(String[][] finalCorr) {
        for (int x = 0; x < finalCorr.length; x++) {
            for (int i = 0; i < finalCorr.length - 1; i++) {
                if (Double.parseDouble(finalCorr[i][1]) > Double.parseDouble(finalCorr[i + 1][1])) {
                    String tmp1 = finalCorr[i][0];
                    String tmp2 = finalCorr[i][1];
                    finalCorr[i][0] = finalCorr[i + 1][0];
                    finalCorr[i][1] = finalCorr[i + 1][1];
                    finalCorr[i + 1][0] = tmp1;
                    finalCorr[i + 1][1] = tmp2;
                }
            }
        }
        return finalCorr;
    }

    public static String[][] minAndMax(String[][] sorted2DArray) {
        String[][] minAndMax = new String[2][2];
        minAndMax[0][0] = sorted2DArray[0][0];
        minAndMax[0][1] = sorted2DArray[0][1];
        minAndMax[1][0] = sorted2DArray[sorted2DArray.length - 1][0];
        minAndMax[1][1] = sorted2DArray[sorted2DArray.length - 1][1];
        return minAndMax;
    }

    public static String[][] arrOfFoundedCorrelation(String[][] arrOfStrings, String event1, String event2) {
        String[][] arr = arrOfStrings;
        for (int i = 0; i < arr.length; i++) {
            if (contains(arr[i], event1) && !contains(arr[i], event2)) {
                arr[i] = Arrays.copyOf(arr[i], arr[i].length + 1);
                String event = event1 + " - " + event2;
                arr[i][arr[i].length - 1] = event;
            }
        }
        return arr;
    }

    public static void main(String[] args) throws IOException {
        int size = (int) Files.lines(Path.of(Constants.PATH)).count();
        boolean[] arrOfBooleans = new boolean[size];
        String[][] arrOfStrings = new String[size][];
        parseFile(arrOfBooleans, arrOfStrings);
        String[] arrayOfStrings = doubleArrayToArray(arrOfStrings);
        String[] sortedArrayOfStrings = sortArray(arrayOfStrings);
        String[] events = events(sortedArrayOfStrings);
        String[][] correlationsArray = correlation(events, arrOfStrings, arrOfBooleans);
        String[][] finalCorrelationsArray = finalCorrelation(correlationsArray);
        String[][] sortedFinalCorrelationsArray = sort2DArray(finalCorrelationsArray);
        String[][] minAndMax = minAndMax(sortedFinalCorrelationsArray);
        String[][] arrOfFoundedCorrelation = arrOfFoundedCorrelation(arrOfStrings, minAndMax[1][0], minAndMax[0][0]);
        String[] sortedCorrelationsArray = sortArray(doubleArrayToArray(arrOfFoundedCorrelation));
        String[] newEvents = events(sortedCorrelationsArray);
        String[][] foundedCorrelationArr = correlation(newEvents, arrOfFoundedCorrelation, arrOfBooleans);

        System.out.println("All correlations:" +
                "\n------------------------------------------");

        for (String[] strings : correlationsArray) {
            System.out.printf("%-25s %f\n", strings[0], Double.parseDouble(strings[1]));
        }

        System.out.println("------------------------------------------");

        System.out.println("Correlations from -0.1 until 0.1:" +
                "\n------------------------------------------");

        for (String[] strings : finalCorrelationsArray) {
            System.out.printf("%-25s %f\n", strings[0], Double.parseDouble(strings[1]));
        }

        System.out.println("------------------------------------------");

        System.out.println("Correlation for '" + minAndMax[1][0] + "':" +
                "\n------------------------------------------");

        for (int i = 0; i < foundedCorrelationArr.length; i++) {
            if (Double.parseDouble(foundedCorrelationArr[i][1]) == 1.0d) {
                System.out.printf("%-25s %f\n", foundedCorrelationArr[i][0], Double.parseDouble(foundedCorrelationArr[i][1]));
            }
        }

        System.out.println("------------------------------------------");
    }
}
