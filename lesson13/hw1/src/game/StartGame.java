package game;

public class StartGame {
    public static void main(String[] args) {
        Menu menu = new Menu();
        character.Character character = menu.characterSelection();
        menu.selectAction(character);
    }
}
