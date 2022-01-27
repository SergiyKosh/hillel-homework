package hw.classes;

import hw.annotations.Start;

@Start(priority = 10, method = "run")
public class Class1 {
    public void run() {
        System.out.println("Class1");
    }
}
