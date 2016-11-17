import java.io.*;
import java.util.*;

public class WaitList {
	static Map<String, Integer> patient = new HashMap<String, Integer>();
	static HashSet<Map<String, Integer>> patients = new HashSet<Map<String, Integer>>();
	static HashSet<Map<String, Integer>> patientsCalled = new HashSet<Map<String, Integer>>();
	static Scanner input = new Scanner(System.in);

	public static void main(String args[]) {
		int functionToCall = 0;
		String moreInfo = "";
	
		do {
			displayMainMenu();	
			functionToCall = Integer.parseInt(input.nextLine());

			if (functionToCall == 1) {
				addPatient();

			} else if (functionToCall == 2) {
				callPatient();

			} else if (functionToCall == 3) {
				currentNumberofPatients();

			} else if (functionToCall == 4) {
				currentlyInLine();

			} else if (functionToCall == 5) {
				previouslyCalled();

			}

			System.out.print("Back to Main Menu: (y/n) ");
			moreInfo = input.nextLine();

		} while (moreInfo.equals("y") || moreInfo.equals("Y"));
	}

	private static void displayBlankLine() {
		System.out.print("\n");
	}

	private static void displayMainMenu() {
		displayBlankLine();
		displayBlankLine();
		System.out.println("Main Menu: ");
		System.out.println("Please select your choice from below:");
		displayBlankLine();
		System.out.println("1. Add Patient");
		System.out.println("2. Call Patient Up");
		System.out.println("3. Get the Current Number of Patients Waiting");
		System.out.println("4. View Patients in Line");
		System.out.println("5. Patients Previously Called");
		displayBlankLine();
	}

	private static void addPatient() {
		String name = "";
		String isFinishedAdding = "";
		int priority = -1;

		do {
			displayBlankLine();
			System.out.print("Please enter the name of the patient: ");
			name = input.nextLine();

			System.out.print("Please enter the priority of this patient: ");
			priority = Integer.parseInt(input.nextLine());

			patient.put(name, priority);
			patients.add(patient);
			patient = new HashMap<String, Integer>();

			displayBlankLine();
			System.out.print("Would you like to add another patient? (y/n): ");
			isFinishedAdding = input.nextLine();

		} while (isFinishedAdding.equals("y") || isFinishedAdding.equals("Y"));
		
	}

	private static void displayCallPatientMenu() {
		displayBlankLine();
		System.out.println("How would you like to call the next patient?");
		System.out.println("1. By Name");
		System.out.println("2. By Priority");
	}

	private static void callPatient() {
		Map<String, Integer> calledPatient = new HashMap<String, Integer>();
		int choice = 0;

		displayCallPatientMenu(); 
		System.out.print("Ans: ");
		do {
			choice = Integer.parseInt(input.nextLine());

			if (choice == 1) {
				calledPatient = callPatientByName();
			} else if (choice == 2) {
				calledPatient = callPatientWithHighestPriority();
			}

			if (choice != 1 && choice != 2) {
				System.out.print("Invalid selection. Please try again: ");
			}

		} while (choice != 1 && choice != 2);

		patientsCalled.add(calledPatient);
		patients.remove(calledPatient);
	}

	private static Map<String, Integer> callPatientByName() {
		String name = "";
		boolean isPatientFound = false; 
		Map<String, Integer> patientToCall = new HashMap<String, Integer>();
		
		displayBlankLine();
		System.out.print("Enter the name of the patient you would like to call: ");
		name = input.nextLine();

		for (Map<String, Integer> currentPatient : patients) {
			System.out.println(patient);
			Set m = currentPatient.entrySet();
			Iterator i = m.iterator();
			while (i.hasNext() && !isPatientFound) {
				Map.Entry me = (Map.Entry)i.next();
				if (me.getKey().equals(name)) {
					System.out.println(me);
					patientToCall = currentPatient;
					isPatientFound = true;
					break;
									
				}	
			}
		}

		return patientToCall;
	}

	private static Map<String, Integer> callPatientWithHighestPriority() {
		boolean isPatientFound = false; 
		Map<String, Integer> patientToCall = new HashMap<String, Integer>();

		for (int i = 10; i > 0; i--) {
			for (Map<String, Integer> currentPatient : patients) {
				Set m = currentPatient.entrySet(); 
				Iterator j = m.iterator();
				while (j.hasNext() && !isPatientFound) {
					Map.Entry me = (Map.Entry)j.next();

					if (me.getValue() == i) {
						displayBlankLine();
						System.out.println(me.getKey() + " has the highest priority.");
						patientToCall = currentPatient;
						isPatientFound = true;
						break;					
					}

				}
			}
		}

		return patientToCall;	
	}

	private static void currentNumberofPatients() {
		displayBlankLine();
		System.out.println("The number of patients currently waiting is: " + patients.size());

	}

	private static void currentlyInLine() {
		displayBlankLine();
		System.out.println("The patients currently in line and their priorities are: ");
		for (Map currentPatient : patients) {
			Set m = currentPatient.entrySet();
			Iterator i = m.iterator();
			while (i.hasNext()) {
				Map.Entry me = (Map.Entry)i.next();
				System.out.println(me.getKey() + " : " + me.getValue());
			}
		}
	}

	private static void previouslyCalled() {
		displayBlankLine();
		System.out.println("The patients previously called were: ");
		for (Map currentPatient : patientsCalled) {
			Set m = currentPatient.entrySet();
			Iterator i = m.iterator();
			while (i.hasNext()) {
				Map.Entry me = (Map.Entry)i.next();
				System.out.println(me.getKey() + " : " + me.getValue());
			}
		}


	}
	
}