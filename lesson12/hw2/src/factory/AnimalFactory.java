package factory;

import entity.Animal;
import entity.Cat;
import entity.Dog;

public class AnimalFactory {
    public static Animal getAnimalByKey(String key) {
        if (key.equals("da")) {
            return new Dog("Арамис");
        } else if (key.equals("db")) {
            return new Dog("Блек");
        } else if (key.equals("dc")) {
            return new Dog("Спайки");
        } else if (key.equals("ca")) {
            return new Cat("Амадей");
        } else if (key.equals("cb")) {
            return new Cat("Баксик");
        } else if (key.equals("cc")) {
            return new Cat("Снежок");
        } else {
            return null;
        }
    }
}
