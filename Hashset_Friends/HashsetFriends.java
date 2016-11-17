import java.io.*;
import java.util.*;

public class HashsetFriends {
	public static void main(String args[]) {
		HashSet<String> friends = new HashSet<String>();
		Scanner friendInput = new Scanner(System.in);
		Scanner redoInput = new Scanner(System.in);
		int numberOfFriends = 0;
		String nameOfFriend = "";
		String runAgain = "y";

		do {

			do {
				System.out.println("Enter the name of your " + (++numberOfFriends) +
					" friend(s).");

				System.out.println("Type 'done' if you are finished.");
			
				nameOfFriend = friendInput.nextLine();

				if (nameOfFriend.compareTo("done") != 0) {
					friends.add(nameOfFriend);
				}

 			} while(nameOfFriend.compareTo("done") != 0);

 			System.out.println("Here is your list of friends in no particular order:");
 			for (String friend : friends) {
 				System.out.println(friend);
 			}

 			friends.clear();

 			System.out.println("Do you want to run again? (y/n)");
 			runAgain = redoInput.nextLine();

		} while (runAgain.compareTo("y") == 0 || runAgain.compareTo("Y") == 0);
	}
}