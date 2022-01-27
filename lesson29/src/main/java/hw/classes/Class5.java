package hw.classes;

import hw.annotations.Start;

@Start(priority = 5, method = "initialize")
public class Class5 {
    public void run() {
        System.out.println("exception");
    }
}
