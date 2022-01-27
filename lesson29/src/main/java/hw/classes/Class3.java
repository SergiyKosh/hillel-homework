package hw.classes;

import hw.annotations.Start;

@Start(priority = 1, method = "run")
public class Class3 {
    public void run() {
        System.out.println("Class3");
    }
}
