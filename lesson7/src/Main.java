import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Main {
    private String event;
    private double correlation;

    private Main() {
    }

    private Main(String event, double correlation) {
        this.event = event;
        this.correlation = correlation;
    }

    private static void parseFile(boolean[] arrOfBooleans, String[][] arrOfStrings) throws IOException {
        int counter = 0;

        String line;
        String[] tmp;
        for(BufferedReader reader = new BufferedReader(new FileReader("src/res/journalEvents_ru.csv")); (line = reader.readLine()) != null; arrOfStrings[counter++] = (String[])Arrays.copyOf(tmp, tmp.length - 1)) {
            tmp = line.split(",");
            arrOfBooleans[counter] = Boolean.parseBoolean(tmp[tmp.length - 1]);
        }

    }

    private static double phi(int[] table) {
        return (double)(table[3] * table[0] - table[2] * table[1]) / Math.sqrt((double)((table[2] + table[3]) * (table[0] + table[1]) * (table[1] + table[3]) * (table[0] + table[2])));
    }

    private static boolean contains(String[] arrOfStrings, String event) {
        String[] var2 = arrOfStrings;
        int var3 = arrOfStrings.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String x = var2[var4];
            if (x.equals(event)) {
                return true;
            }
        }

        return false;
    }

    private static int[] tableFor(String[][] arrOfStrings, boolean[] arrOfBooleans, String event) {
        int[] table = new int[]{0, 0, 0, 0};

        for(int i = 0; i < arrOfBooleans.length; ++i) {
            int index = 0;
            if (contains(arrOfStrings[i], event)) {
                ++index;
            }

            if (arrOfBooleans[i]) {
                index += 2;
            }

            int var10002 = table[index]++;
        }

        return table;
    }

    private static String[] getAllEvents(String[][] arrOfStrings) {
        String[] allEvents = new String[0];
        int index = 0;
        String[][] var3 = arrOfStrings;
        int var4 = arrOfStrings.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String[] current = var3[var5];
            allEvents = (String[])Arrays.copyOf(allEvents, allEvents.length + current.length);
            System.arraycopy(current, 0, allEvents, index, current.length);
            index += current.length;
        }

        Arrays.sort(allEvents);
        return allEvents;
    }

    private static int sizeOfArrWithoutDuplicates(String[] arr, int size) {
        if (size != 0 && size != 1) {
            String[] temp = new String[size];
            int j = 0;

            int i;
            for(i = 0; i < size - 1; ++i) {
                if (!arr[i].equals(arr[i + 1])) {
                    temp[j++] = arr[i];
                }
            }

            temp[j++] = arr[size - 1];

            for(i = 0; i < j; ++i) {
                arr[i] = temp[i];
            }

            return j;
        } else {
            return size;
        }
    }

    private static String[] eventsWithoutDuplicates(String[] arr) {
        int size = sizeOfArrWithoutDuplicates(arr, arr.length);
        String[] newArr = new String[size];

        for(int i = 0; i < size; ++i) {
            newArr[i] = arr[i];
        }

        return newArr;
    }

    private static void showEventsWithoutDuplicates(String[][] arrOfStrings, boolean[] arrOfBooleans, String[] eventsWithoutDuplications) {
        String[] var3 = eventsWithoutDuplications;
        int var4 = eventsWithoutDuplications.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String x = var3[var5];
            Main correlation = correlation(x, arrOfStrings, arrOfBooleans);
            System.out.printf("%-25s %s\n", correlation.event, correlation.correlation);
        }

    }

    private static Main finalCorrelation(Main correlation) {
        Main finCorr = new Main();
        if (correlation.correlation > 0.1D || correlation.correlation < -0.1D) {
            finCorr.event = correlation.event;
            finCorr.correlation = correlation.correlation;
        }

        return finCorr;
    }

    private static void showFinalCorrelation(String[] events, String[][] arrOfStrings, boolean[] arrOfBooleans) {
        String[] var3 = events;
        int var4 = events.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String x = var3[var5];
            Main finalCorrelation = finalCorrelation(correlation(x, arrOfStrings, arrOfBooleans));
            if (finalCorrelation.event != null) {
                System.out.printf("%-25s %f\n", finalCorrelation.event, finalCorrelation.correlation);
            }
        }

    }

    private static Main correlation(String event, String[][] arrOfStrings, boolean[] arrOfBooleans) {
        Main correlation = new Main();
        correlation.event = event;
        correlation.correlation = phi(tableFor(arrOfStrings, arrOfBooleans, event));
        return correlation;
    }

    public static void sortCorr(Main[] arrForMinCorr) {
        for(int x = 0; x < arrForMinCorr.length; ++x) {
            for(int i = 0; i < arrForMinCorr.length - 1; ++i) {
                if (arrForMinCorr[i].correlation > arrForMinCorr[i + 1].correlation) {
                    String tmp1 = arrForMinCorr[i].event;
                    double tmp2 = arrForMinCorr[i].correlation;
                    arrForMinCorr[i].event = arrForMinCorr[i + 1].event;
                    arrForMinCorr[i].correlation = arrForMinCorr[i + 1].correlation;
                    arrForMinCorr[i + 1].event = tmp1;
                    arrForMinCorr[i + 1].correlation = tmp2;
                }
            }
        }

    }

    private static int sizeOfFinCorrArr(String[] events, String[][] arrOfStrings, boolean[] arrOfBooleans) {
        int size = 0;
        String[] var4 = events;
        int var5 = events.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            String x = var4[var6];
            Main finalCorrelation = finalCorrelation(correlation(x, arrOfStrings, arrOfBooleans));
            if (finalCorrelation.event != null) {
                ++size;
            }
        }

        return size;
    }

    private static Main[] arrOfFinalCorrelations(String[] events, String[][] arrOfStrings, boolean[] arrOfBooleans) {
        int size = sizeOfFinCorrArr(events, arrOfStrings, arrOfBooleans);
        if (size < 1) {
            return null;
        } else {
            Main[] arr = new Main[size];
            int i = 0;

            while(i < arr.length) {
                String[] var6 = events;
                int var7 = events.length;

                for(int var8 = 0; var8 < var7; ++var8) {
                    String x = var6[var8];
                    Main finCorr = finalCorrelation(correlation(x, arrOfStrings, arrOfBooleans));
                    if (finCorr.event != null) {
                        arr[i] = new Main(finCorr.event, finCorr.correlation);
                        ++i;
                    }
                }
            }

            return arr;
        }
    }

    public static Main maxCorr(Main[] arrForMinCorr) {
        sortCorr(arrForMinCorr);
        return arrForMinCorr[arrForMinCorr.length - 1];
    }

    public static Main minCorr(Main[] arrForMaxCorr) {
        sortCorr(arrForMaxCorr);
        return arrForMaxCorr[0];
    }

    public static String[][] arrOfFoundedCorrelation(String[][] arrOfStrings, String event1, String event2) {
        String[][] arr = arrOfStrings;

        for(int i = 0; i < arr.length; ++i) {
            if (contains(arr[i], event1) && !contains(arr[i], event2)) {
                arr[i] = (String[])Arrays.copyOf(arr[i], arr[i].length + 1);
                String event = event1 + " - " + event2;
                arr[i][arr[i].length - 1] = event;
            }
        }

        return arr;
    }

    private static void showFoundedCorrelation(String[] newEvents, String[][] arrOfFoundedCorr, boolean[] arrOfBooleans) {
        String[] var3 = newEvents;
        int var4 = newEvents.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String x = var3[var5];
            Main corr = correlation(x, arrOfFoundedCorr, arrOfBooleans);
            if (corr.correlation == 1.0D) {
                System.out.printf("%-25s %f\n", corr.event, corr.correlation);
                break;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        int size = (int)Files.lines(Path.of("src/res/journalEvents_ru.csv")).count();
        boolean[] arrOfBooleans = new boolean[size];
        String[][] arrOfStrings = new String[size][];
        parseFile(arrOfBooleans, arrOfStrings);
        String[] allEvents = getAllEvents(arrOfStrings);
        String[] eventsWithoutDuplications = eventsWithoutDuplicates(allEvents);
        System.out.println("\nAll correlations:\n------------------------------------------");
        showEventsWithoutDuplicates(arrOfStrings, arrOfBooleans, eventsWithoutDuplications);
        System.out.println("------------------------------------------");
        System.out.println("\nCorrelations from -0.1 until 0.1:\n------------------------------------------");
        showFinalCorrelation(eventsWithoutDuplications, arrOfStrings, arrOfBooleans);
        Main[] arrOfFinCorrs = arrOfFinalCorrelations(eventsWithoutDuplications, arrOfStrings, arrOfBooleans);
        System.out.println("------------------------------------------");
        String[][] arrOfFoundedCorr = arrOfFoundedCorrelation(arrOfStrings, maxCorr(arrOfFinCorrs).event, minCorr(arrOfFinCorrs).event);
        String[] newEvents = getAllEvents(arrOfFoundedCorr);
        Arrays.sort(newEvents);
        PrintStream var10000 = System.out;
        String var10001 = maxCorr(arrOfFinCorrs).event;
        var10000.println("\nCorrelation for '" + var10001 + "-" + minCorr(arrOfFinCorrs).event + "':\n------------------------------------------");
        showFoundedCorrelation(newEvents, arrOfFoundedCorr, arrOfBooleans);
        System.out.println("------------------------------------------");
    }
}
