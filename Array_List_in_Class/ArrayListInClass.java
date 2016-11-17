import java.util.*;

public class ArrayListInClass {
	public static void main(String args[]) {
		List<String> favoriteColours = new ArrayList<String>();
		Scanner colourInput = new Scanner(System.in);
		String colour = "";
		while (!colour.equals("done")) {
			System.out.println("Enter your favorite color. Type 'done' when you're finished: ");
			colour = colourInput.next();
			favoriteColours.add(colour);
		}

		System.out.println("Your favorite colours are: " + favoriteColours);
	}
}