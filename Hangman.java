import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
// little Hangman game with one or two players (one player is the computer with a list of words)
public class Hangman {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner keyboard = new Scanner(System.in);

        System.out.println("1 or 2 Players?");
        String players = keyboard.nextLine();
        String word;
        if (players.equals("1")) {
            // computer chooses word from list
            Scanner scanner = new Scanner(new File("/Users/crixos/Documents/Courses/Java/Hangman/Words.txt"));


            List<String> words = new ArrayList<>();
            while (scanner.hasNext()){
                words.add(scanner.nextLine()); // add each word to the list
            }

            Random rand = new Random();

            word = words.get(rand.nextInt(words.size())); // choose a random word from the list
        }
        else {
            System.out.println("Player 1, please enter your word: ");
            word = keyboard.nextLine();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                    "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("Ready for player 2! Have fun!");
        }


        //System.out.println(word);

        List<Character> playerGuesses = new ArrayList<>(); // list of player's guesses

        int wrongCount = 0; // number of wrong guesses
        while(true) {

            printHangedMan(wrongCount);

            if(wrongCount >= 6){ // if player has guessed wrong 6 times, they lose
                System.out.println("You lose!");
                System.out.println("The word was: " + word);
                break;
            }
            printWordState(word, playerGuesses);
            if (!getPlayerGuess(keyboard, word, playerGuesses)) {
                wrongCount++;
            }

            if (printWordState(word, playerGuesses)){
                System.out.println("You win!");
                break;
            }

            System.out.println("Please enter your guess for the word: ");
            if(keyboard.nextLine().equals(word)) {
                System.out.println("You win!");
                break;
            }
            else {
                System.out.println("Nope, try again!");
            }
        }
    }

    private static void printHangedMan(int wrongCount) { // prints the separate parts of the hanged man
        System.out.println(" -------");
        System.out.println(" |     |");
        if (wrongCount >= 1) {
            System.out.println(" O");
        }
        if (wrongCount >= 2) {
            System.out.print("\\ ");
            if (wrongCount >= 3) {
                System.out.println("/");
            } else {
                System.out.println("");
            }
        }
        if (wrongCount >= 4) {
            System.out.println(" |");
        }
        if (wrongCount >= 5) {
            System.out.print("/ ");
            if (wrongCount >= 6) {
                System.out.println("\\");
            } else {
                System.out.println("");
            }
        }
        System.out.println("");
        System.out.println("");
    }

    private static boolean getPlayerGuess(Scanner keyboard, String word, List<Character> playerGuesses) {
        // gets player's guess and adds it to the list of guesses
        System.out.println("Please enter a new letter: ");
        String letterGuess = keyboard.nextLine();
        playerGuesses.add(letterGuess.charAt(0));

        return word.contains(letterGuess);
    }

    private static boolean printWordState(String word, List<Character> playerGuesses) {
        // prints the word with the letters that have been guessed
        int correctCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (playerGuesses.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
                correctCount++;
            }
            else {
                System.out.print("-");
            }
        }
        System.out.println();
        return(word.length() == correctCount);
    }
}
