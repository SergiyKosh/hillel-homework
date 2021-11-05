package entity;

public class Yorkshire extends Dog {
    private int breedWeight = 40;

    public Yorkshire(String name) {
        super(name);
    }

    public String speak() {
        return "woof";
    }

    @Override
    public int avgBreedWeight() {
        return breedWeight;
    }
}
