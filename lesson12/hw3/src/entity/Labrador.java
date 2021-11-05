package entity;

public class Labrador extends Dog
{
    private String color;
    private static int breedWeight = 75;

    public Labrador(String name, String color)
    {
        super(name);
        this.color = color;
    }

    public String speak()
    {
        return "WOOF";
    }

    public static int avgBreedWeight()
    {
        return breedWeight;
    }
}
