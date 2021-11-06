package character;

public class Knight extends Character {
    @Override
    public void fight() {
        System.out.println("The Knight hits the enemy with an " + weapon.useWeapon());
    }
}
