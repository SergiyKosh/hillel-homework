package incapsulation;

public class TestVariable {
    public static void main(String[] args) {
        double first = 99;
        TestVariable tv = new TestVariable();

        System.out.println("Main method:" + first);
        tv.testMethod(first);
        System.out.println("Main method:" + first);
    }

    public void testMethod(double first) {
        System.out.println("Test method:" + first);
        first = 11;
        System.out.println("Test method:" + first);
    }
}
