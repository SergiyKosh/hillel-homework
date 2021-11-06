package character;

public class Queen extends Character {
    @Override
    public void fight() {
        System.out.println("The Queen hits the enemy with an " + weapon.useWeapon());
    }
}
