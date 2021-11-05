package sample;

import entity.Dog;
import entity.Labrador;
import entity.Yorkshire;

public class DogTest {
    public static void main(String[] args) {
        Dog dog = new Dog("Spike") {
            @Override
            public int avgBreedWeight() {
                return 0;
            }
        };
        Labrador labrador = new Labrador("Labrador", "white");
        Yorkshire yorkshire = new Yorkshire("Yorkshire");
        System.out.println(dog.getName() + " says " + dog.speak());
        System.out.println(labrador.getName() + " says " + labrador.speak());
        System.out.println(yorkshire.getName() + " says " + yorkshire.speak());
        System.out.println("Breed weight for labrador: " + labrador.avgBreedWeight());
        System.out.println("Breed weight for yorkshire: " + yorkshire.avgBreedWeight());
    }
}
