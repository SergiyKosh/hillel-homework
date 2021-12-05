package second;

class X {
    static int x = 3131; //3131

    static class Y {
        static int y = x++; //3132

        static class Z {
            static int z = y++; //3133
        }
    }
}
