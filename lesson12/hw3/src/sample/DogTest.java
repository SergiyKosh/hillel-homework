package sample;

import entity.Dog;

public class DogTest {
    public static void main(String[] args) {
        Dog dog = new Dog("Spike");
        System.out.println(dog.getName() + " says " + dog.speak());
    }
}
