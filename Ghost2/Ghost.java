import java.io.*;
import java.util.*;

public class Ghost {
	static boolean player1Turn = false;
	static boolean player2Turn = false;
	static String guessedWord = "";
	static List<String> words = new ArrayList<String>();

	
	public static void main (String args[]) {
		boolean gameOver = false;

		try {
			createDictionaryOfWords();
			playGame();
		} catch (IOException e) {
			System.out.println("Could not read file. Restart Program");
		}

	

	}

	private static List createDictionaryOfWords() throws IOException {
		BufferedReader wordStream = new BufferedReader(new FileReader("words.txt"));
		String word;
		while ((word = wordStream.readLine()) != null) {
			words.add(word);
		}
		
		return words;
	}

	private static void playGame() {
		int i = 0;
		boolean gameOver = false;
		String playerOneGuess = "";
		String playerTwoGuess = "";
		String newCharacter = "";
		String playerTurn = "";
		boolean challengeChosen = false;
		boolean challengeSuccess = false;
		
		while (!gameOver) {
			if (i % 2 == 0) {
				player1Turn = true;
				player2Turn = false;
				playerTurn = "Player 1: ";
				if (i >= 2) {
					challengeChosen = getWordChallenge(playerTurn);
					if (challengeChosen) {

						challengeSuccess = isWord();
						if (challengeSuccess) {
							System.out.println("GameOver");
							break;
						} else {
							System.out.println("Word challenge failed");
						}
					}
				}
	
				playerOneGuess = getWordFromPlayer(playerTurn);
				playerTwoGuess = "";
				
			} else {
				player2Turn = true;
				player1Turn = false;
				playerTurn = "Player 2: ";
				if (i >= 2) {
					challengeChosen = getWordChallenge(playerTurn);
					if (challengeChosen) {
						challengeSuccess = isWord();
						if (challengeSuccess) {
							System.out.println("GameOver");
							break;
						} else {
							System.out.println("Word challenge failed");
						}
					}
				}
				playerTwoGuess = getWordFromPlayer(playerTurn);
				playerOneGuess = "";
		
			}

			newCharacter = playerOneGuess == "" ? playerTwoGuess : playerOneGuess;

			guessedWord += newCharacter;
			System.out.println("newCharacter = " + newCharacter);
			System.out.println("guessedWord = " + guessedWord);
			i++;
		}

	}

	private static boolean isWord() {
		int first = 0;
		int last = words.size() - 1;
		int middle = 0;
		int guessedWordLen = guessedWord.length();
		while (first <= last) {
			middle = (first + last) / 2;
			if (words.get(middle).length() >= guessedWordLen) {
				if (words.get(middle).substring(0, guessedWordLen-1).compareTo(guessedWord) == 0) {
					return true;
				} else if(words.get(middle).substring(0, guessedWordLen-1).compareTo(guessedWord) > 0) {
					first = middle + 1;
				} else {
					last = middle - 1;
				}	
			} else {
				last++;
				first++;
			}	
		}
		return false;
	}

	private static String getWordFromPlayer(String player) {
		System.out.print(player + "Enter a character: ");
		Scanner wordInput = new Scanner(System.in);
		return wordInput.next();
	}

	private static boolean getWordChallenge(String player) {
		boolean found = false;
		Scanner wordChallengeInput = new Scanner(System.in);
		System.out.println(player + " would you like to challenge this word? (y/n)");
		found = ((wordChallengeInput.next()).compareTo("y") == 0);
		return found;
	}
	
}