import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.println("Type in instruction: ");
		String inst = scn.nextLine();
		System.out.println("Your instruction is " + inst);
		scn.close();
	}
}
