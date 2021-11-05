package entity;

public class Dog {
    protected String name;

    public Dog(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public String speak()
    {
        return "Woof";
    }
}
