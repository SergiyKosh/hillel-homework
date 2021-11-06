package character;

public class King extends Character {
    @Override
    public void fight() {
        System.out.println("The King hits the enemy with an " + weapon.useWeapon());
    }
}
