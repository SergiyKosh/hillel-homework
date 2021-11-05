package multi_array;

public class SpiralArray {
    public static void main(String[] args) {
        int Size = 8;
        int Max = Size * Size;

        int RC = 4;
        int[] x = new int[RC];
        int[] y = new int[RC];

        int[][] Sp = new int[Size][Size];

        for (int i = 0; i < RC; i++) {
            x[i] = y[i] = 0;
        }
        x[1] = y[1] = Size - 1;
        x[3] = 1;

        for (int i = 0; i < Max; i++) {
            Sp[x[2]][y[2]] = i + 1;
            x[2] = x[2] + x[3];
            y[2] = y[2] + y[3];
            if (y[3] == 0) {
                if ((x[2] > x[0]) && (x[3] > 0)) {
                    x[3] = 1;
                }
                if ((x[2] < x[1]) && (x[3] < 0)) {
                    x[3] = -1;
                }
                if ((x[2] == x[1]) && (x[3] > 0)) {
                    x[3] = 0;
                    x[1]--;
                    y[3] = 1;
                }
                if ((x[2] == x[0]) && (x[3] < 0)) {
                    x[3] = 0;
                    x[0]++;
                    y[3] = -1;
                }
            }
            if (x[3] == 0) {
                if ((y[2] > y[0]) && (y[3] > 0)) {
                    y[3] = 1;
                }
                if ((y[2] < y[1]) && (y[3] < 0)) {
                    y[3] = -1;
                }
                if ((y[2] == y[1]) && (y[3] > 0)) {
                    y[3] = 0;
                    y[1]--;
                    x[3] = -1;
                }
                if ((y[2] == x[0]) && (y[3] < 0)) {
                    y[3] = 0;
                    y[0]++;
                    x[3] = 1;
                }
            }
        }

        System.out.println();
        for (int i = 0; i < Size; i++) {
            for (int j = 0; j < Size; j++) {
                System.out.format("%4d", Sp[j][i]);
            }
            System.out.println();
        }
    }
}
