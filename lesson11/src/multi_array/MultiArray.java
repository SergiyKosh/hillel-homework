package multi_array;

public class MultiArray {
    public static void main(String[] args) {
//        int SIZE = 10;
//        char[][] graph = new char[SIZE][SIZE];
//
//        for (int i = 0; i < SIZE; i++) {
//            for (int j = 0; j < SIZE; j++) {
//                graph[i][j] = '#';
//            }
//        }
//
//        for (int i = 0; i < SIZE; i++) {
//            for (int j = 0; j < SIZE; j++) {
//                System.out.print(graph[i][j]);
//            }
//            System.out.println();
//        }

//        int SIZE = 10;
//        char[][] graph = new char[SIZE][SIZE];
//
//        for (int i = 0; i < SIZE; i++) {
//            for (int j = 0; j < SIZE; j++) {
//                if (i == 0 || i == SIZE - 1 || j == 0 || j == SIZE - 1) {
//                    graph[i][j] = '#';
//                } else {
//                    graph[i][j] = ' ';
//                }
//            }
//        }
//
//        for (int i = 0; i < SIZE; i++) {
//            for (int j = 0; j < SIZE; j++) {
//                System.out.print(graph[i][j]);
//            }
//            System.out.println();
//        }

        int SIZE = 5;
        char[][] graph = new char[SIZE][];

        for (int i = 0; i < graph.length; i++) {
            System.out.println(graph[i] == null);
        }

        for (int i = 0; i < graph.length; i++) {
            int size = (int) (Math.round(Math.random() * 50) + 25);
            graph[i] = new char[size];
        }

        for (int i = 0; i < graph.length; i++) {
            System.out.println(graph[i].length);
        }
    }
}

