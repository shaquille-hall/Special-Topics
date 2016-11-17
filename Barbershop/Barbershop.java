import java.util.*;

class Barbershop {

	public static void main (String [] args) {
		Queue<String> mainLine = new LinkedList<String>();
		Queue<String> seanLine = new LinkedList<String>();
		Queue<String> coryLine = new LinkedList<String>();
		Scanner name = new Scanner(System.in);
		String customerName = "";
		String barber = "";
		int haircutComplete = 0;


		System.out.print("Welcome to the shop! \nWhat's your name?");
		customerName = name.next();


		System.out.print("Welcome " + customerName + " Are you here to see Sean or Cory?\n");
		barber = name.next();

		if (barber.compareTo("sean") == 0 || barber.compareTo("Sean") == 0) {
			seanLine.add(barber);
		} else if (barber.compareTo("cory") == 0 || barber.compareTo("Cory") == 0) {
			coryLine.add(barber);
		} else {
			mainLine.add(barber);
		}

		haircutComplete++;

		System.out.print("Number of people in main line: " + seanLine.size() + "\n");
		System.out.print("Number of people in Sean's line: " + seanLine.size() + "\n");
		System.out.print("Number of people in Cory's line: " + seanLine.size() + "\n");
		System.out.print("Number of people who received haircuts: " + haircutComplete + "\n");


	}
}