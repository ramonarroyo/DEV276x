import java.util.Scanner;

public class Crypto {
    public static void main(String[] args){
        System.out.print("What would you like to encrypt? ");
        Scanner input = new Scanner(System.in);
        String text = input.nextLine();
        System.out.print("How many times would you like to shift the code for encryption? ");
        int shift = input.nextInt();
        System.out.print("How many letters per group would you like this to be encrypted in? ");
        int groups = input.nextInt();
        String cyphertext = encryptString(text, shift, groups);
        System.out.println("This is your code: " + cyphertext);
    }
    public static String normalizeText(String text){
        // implement regex that replaces all characters not in A-Z and a-z with an empty character
        text = text.replaceAll("[^A-Za-z]+", "").toUpperCase();
        return text;
    }
    public static String caesarify(String input, int key){
        String result = "";
        String alphabet = shiftAlphabet(0); // get alphabet
        String shift = shiftAlphabet(key); // get shifted alphabet
        for (int i = 0; i < input.length(); i++){
            int index = alphabet.indexOf(input.charAt(i)); // index of char in alphabet
            result += shift.charAt(index); // find shifted char with previous index
        }
        return result;
    }
    public static String shiftAlphabet(int shift) {
        int start = 0;
        if (shift < 0) {
            start = (int) 'Z' + shift + 1;
        } else {
            start = 'A' + shift;
        }
        String result = "";
        char currChar = (char) start;
        for(; currChar <= 'Z'; ++currChar) {
            result = result + currChar;
        }
        if(result.length() < 26) {
            for(currChar = 'A'; result.length() < 26; ++currChar) {
                result = result + currChar;
            }
        }
        return result;
    }

    public static String groupify(String code, int lettersPerGroup){
        String result = "";
        while (code.length() % lettersPerGroup != 0){ // add an x if not evenly divisible
            code += "x";
        }
        for (int i = 0; i < code.length(); i+=lettersPerGroup){ // add a space every key number
            result += code.substring(i, i+lettersPerGroup) + " ";
        }
        return result;
    }

    public static String encryptString(String text, int shift, int group){
        String code = normalizeText(text);
        code = caesarify(code, shift);
        code = groupify(code, group);
        return code;
    }
}
