import java.util.InputMismatchException;
import java.util.Scanner;

public class Utils {
	Scanner s = new Scanner(System.in);

	public int instertInt(String text, int min, int max) {
		boolean error;
		int number = 0;
		do {
			error = false;
			try {
				System.out.println(text);
				number = s.nextInt();
				if (number < min || number > max) {
					System.out.println("Out of bounds");
					error = true;
				}
			} catch (InputMismatchException e) {
				System.out.println("Wrong data type");
				error = true;
			}
			
			s.nextLine();
		} while(error);
		return number;
		
	}
}

