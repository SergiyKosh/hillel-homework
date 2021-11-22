import java.util.*;

class MyTranslator {
    private String englishWord;
    private String russianWord;
    private HashMap<String, String> words;

    MyTranslator() {
        words = new HashMap<>();
        initMap(words);
    }

    void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    void setRussianWord(String russianWord) {
        this.russianWord = russianWord;
    }

    void addWord() {
        words.put(englishWord.toUpperCase(), russianWord.toUpperCase());
    }

    private String getPhrase(List<String> phrase) {
        String str = "";
        for (String x : phrase) {
            str += x + " ";
        }
        return str;
    }

    //сделал так, чтобы в случае, при котором символ будет отделён от других слов, не вызывалось меню
    private void initMap(HashMap<String, String> words) {
        words.put(",", ",");
        words.put(".", ".");
        words.put("-", "-");
        words.put("+", "+");
        words.put("*", "*");
        words.put("/", "/");
        words.put("|", "|");
        words.put("=", "=");
        words.put("%", "%");
        words.put("$", "$");
        words.put("^", "^");
        words.put("?", "?");
        words.put(":", ":");
        words.put("@", "@");
        words.put("'", "'");
        words.put("&", "&");
        words.put("(", "(");
        words.put(")", ")");
        words.put("!", "!");
        words.put("№", "№");
        words.put(";", ";");
        words.put("<", "<");
        words.put(">", ">");
        words.put("`", "`");
        words.put("~", "~");
        words.put("ʼ", "ʼ");
    }

    private String getWordFromPhrase(String key) {

        if (!Character.isLetter(key.charAt(0)) && key.length() > 1) {
            key = key.substring(1);
        }

        if (!Character.isLetter(key.charAt(key.length() - 1)) && key.length() > 1) {
            key = key.substring(0, key.length() - 1);
        }

        String finalKey = key;

        return words.entrySet()
                .stream()
                .filter(x -> x.getKey().equalsIgnoreCase(finalKey))
                .findFirst()
                .toString();
    }

    private String menu(String word) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Word " + word + " was not found in the Map." +
                "\n1 - Add this word to the Map" +
                "\n2 - Don't translate this word" +
                "\n3 - Replace with null" +
                "\n4 - Throw exception" +
                "\n-> ");

        switch (scanner.nextLine()) {
            case "1":
                System.out.print("Input translate for " + word + ": ");
                words.put(word, scanner.nextLine().toUpperCase());
                return words.get(word);
            case "2":
                return word;
            case "3":
                return null;
            case "4":
                throw new RuntimeException();
            default:
                System.out.println("This operation does not exists! Try again!");
                return menu(word);
        }
    }

    String translate(List<String> phrase, int i) {
        if (i < phrase.size()) {
            String key = getWordFromPhrase(phrase.get(i));

            if (key.equals("Optional.empty")) {
                if (!Character.isLetter(phrase.get(i).charAt(0)) && phrase.get(i).length() > 1) {
                    phrase.set(i, phrase.get(i).charAt(0) + menu(phrase.get(i)));
                } else if (!Character.isLetter(phrase.get(i).charAt(phrase.get(i).length() - 1))
                        && phrase.get(i).length() > 1) {
                    phrase.set(i, menu(phrase.get(i)) + phrase.get(i).charAt(phrase.get(i).length() - 1));
                } else {
                    phrase.set(i, menu(phrase.get(i)));
                }
            } else {
                if (!Character.isLetter(phrase.get(i).charAt(0)) && phrase.get(i).length() > 1) {
                    phrase.set(i, phrase.get(i).charAt(0) + key
                            .split("=")[1]
                            .replaceAll("]", ""));
                } else if (!Character.isLetter(phrase.get(i).charAt(phrase.get(i).length() - 1))
                        && phrase.get(i).length() > 1) {
                    phrase.set(i, key
                            .split("=")[1]
                            .replaceAll("]", "")
                            + phrase.get(i)
                            .charAt(phrase.get(i).length() - 1));
                } else {
                    phrase.set(i, key
                            .split("=")[1]
                            .replaceAll("]", ""));
                }
            }
            i++;
            return translate(phrase, i);
        }
        return getPhrase(phrase);
    }
}
