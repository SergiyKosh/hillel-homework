package game;

import character.King;
import character.Knight;
import character.Queen;
import character.Troll;
import weapon.*;

import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);

    public character.Character characterSelection() {
        System.out.print("Choose a character:\n1 - King\n2 - Knight\n3 - Queen\n4 - Troll\n-> ");
        character.Character character;
        switch (scanner.nextLine()) {
            case "1":
                character = new King();
                break;
            case "2":
                character = new Knight();
                break;
            case "3":
                character = new Queen();
                break;
            case "4":
                character = new Troll();
                break;
            default:
                System.out.print("This character does not exists. Try again!");
                return characterSelection();
        }
        return character;
    }

    public WeaponBehavior weaponSelection() {
        System.out.print("Choose a weapon:\n1 - Axe\n2 - Box and arrow\n3 - Knife\n4 - Sword\n-> ");
        WeaponBehavior weapon;
        switch (scanner.nextLine()) {
            case "1":
                weapon = new AxeBehavior();
                break;
            case "2":
                weapon = new BoxAndArrowBehavior();
                break;
            case "3":
                weapon = new KnifeBehavior();
                break;
            case "4":
                weapon = new SwordBehavior();
                break;
            default:
                System.out.print("This weapon does not exists. Try again!");
                return weaponSelection();
        }
        return weapon;
    }

    public void selectAction(character.Character character) {
        System.out.print("Select an action:\n1 - Fight\n2 - Change weapon\n3 - Change character\n4 - Exit\n-> ");
        switch (scanner.nextLine()) {
            case "1":
                if (character.getWeapon() != null) {
                    character.fight();
                } else {
                    System.out.println("You need to select weapon!");
                    character.setWeapon(weaponSelection());
                }
                break;
            case "2":
                character.setWeapon(weaponSelection());
                break;
            case "3":
                character = characterSelection();
                break;
            case "4":
                return;
            default:
                System.out.println("This action does not exists! Try again!");
                selectAction(character);
        }
        System.out.print("Choose a new action? (Y/N)\n-> ");
        if (scanner.nextLine().equalsIgnoreCase("Y")) {
            selectAction(character);
        }
    }
}
