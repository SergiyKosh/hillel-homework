package operations;

public class SwitchFourth {
    public static void main(String[] arg) {
        int number = 2;
        switch (number) {
            case 1:
            case 2:
                System.out.println("One or Two");
                break;
            case 3:
                System.out.println("Three");
                break;
            default:
                System.out.println("Unknown");
        }
    }
}
