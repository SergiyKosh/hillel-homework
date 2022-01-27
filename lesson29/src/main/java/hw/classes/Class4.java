package hw.classes;

import hw.annotations.Start;

@Start(priority = 100, method = "initialize")
public class Class4 {
    public void initialize() {
        System.out.println("Class4");
    }
}
