import java.io.*;
import java.util.*;


public class Opera {
	// static HashSet<Map<String, Integer>> patrons = new HashSet<Map<String, Integer>>();
	// static HashSet<Map<String, Integer>> patientsCalled = new HashSet<Map<String, Integer>>();
	
	static HashSet<String> patrons = new HashSet<String>();
	static Scanner input = new Scanner(System.in);
	String email = "";

	public static void main(String[] args) {
		int functionToCall = 0;
		String moreInfo = "";
	
		initializeListOfPatrons();
		do {
			displayMainMenu();	
			functionToCall = Integer.parseInt(input.nextLine());

			if (functionToCall == 1) {
				getCurrentNumberOfPatrons();

			} else if (functionToCall == 2) {
				addPatron();

			} else if (functionToCall == 3) {
				removePatron();
			} else if (functionToCall == 4) {
				clearList(); 

			}else if (functionToCall == 5) {
				checkForPatron();

			} else if (functionToCall == 6) {
				printListOfPatrons();

			} else if (functionToCall == 7) {
				exitProgram();
			}

			System.out.print("Back to Main Menu: (y/n) ");
			moreInfo = input.nextLine();

		} while (moreInfo.equals("y") || moreInfo.equals("Y"));
	}

	private static void initializeListOfPatrons() {
		patrons.add("daddio@ymail.com");
		patrons.add("king_man_dem@outlook.com");
		patrons.add("bossla@askSomeoneBoutMe.com");
	}

	private static void displayMainMenu() {
		displayBlankLine();
		displayBlankLine();
		System.out.println("Main Menu: ");
		System.out.println("Please select your choice from below:");
		displayBlankLine();
		System.out.println("1. Get Number of Patrons Inside");
		System.out.println("2. Add Patron to List");
		System.out.println("3. Remove Patron From List");
		System.out.println("4. Clear The List Of All Patrons");
		System.out.println("5. Check List For Name");
		System.out.println("6. Print List of Patrons");
		System.out.println("7. Exit Program");

		displayBlankLine();
	}

	private static void getCurrentNumberOfPatrons() {
		System.out.println("There are " + (patrons.size() + " patrons inside."));

	}

	private static void regulateNumberOfPatrons() {
		Integer amountOfPatrons = patrons.size();

		if (amountOfPatrons > 250) {
			System.out.println("You are over capacity.");
		} 

		if (amountOfPatrons > 300) {
			System.out.println("You have exceeded the max number of patrons you've ever had.");
		}

		if (amountOfPatrons >= 500) {
			System.out.println("You never expected to reach this number of patrons.");
		}
	}

	private static void addPatron() {
		System.out.println("What is the email address of the person you'd like to add to the list? ");
		String newPatron = input.nextLine();
		patrons.add(newPatron);
	}

	private static void removePatron() {
		System.out.println("Enter the email address of the person you'd like to remove from the list: ");
		String patientToRemove = input.nextLine();

		if (currentlyOnList(patientToRemove)) {
			patrons.remove(patientToRemove);
			System.out.println(patientToRemove + " has been removed from the list: ");
		} else {
			System.out.println(patientToRemove + " is not on the list.");

		}
	}

	private static void clearList() {
		patrons.clear();
		System.out.println("Your list has been cleared");
	}

	private static void checkForPatron() {
		System.out.println("Enter the email address of the person you'd like to check for: ");
		String patientToCheckFor = input.nextLine();

		if (currentlyOnList(patientToCheckFor)) {
			System.out.println(patientToCheckFor + " is on the list.");
		} else {
			System.out.println(patientToCheckFor + " is not on the list.");

		}
	}

	private static void printListOfPatrons() {
		System.out.println("Here is your list of patrons in no particular order:");
		for (String patron : patrons) {
			System.out.println(patron);
		}
	}

	private static void exitProgram() {
		System.out.println("Exiting Program... Goodbye!");
		System.exit(0);
	}

	private static boolean currentlyOnList(String patron) {
		return patrons.contains(patron);
	}

	private static void displayBlankLine() {
		System.out.print("\n");
	}


}