import java.util.Scanner;

public class Game {

	Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		Game myGame = new Game();
		System.out.println("Welcome To Bash Game!!");
		System.out.println("\nWhat is Bash Game?\n\n"
				+ "There is only a pile of n items, and two people take turns from this pile of items.\n"
				+ "It is stipulated to take at least one at a time, and at most m.\n"
				+ "The people who take all the item will LOSE.\n");
		myGame.pveMode();
	}

	public void pveMode() {
		int totalItem = 20;
		int maxItemPerRound = 3;
		String ask = "";
		System.out.println("PVE Mode\n");
		System.out.println("By DEFAULT: n = 20, m = 3, minimum per round = 1");
		System.out.println(
				"Do you wish to change the \"Total Item\"(n) and \"Maximum Item Taken Each Round\"(m)? (Y = Yes/ N = No)");
		ask = input.nextLine();
		if (ask.equals("Y") || ask.equals("y")) {
			System.out.println("\nEnter n(less than 1 is not available): ");
			totalItem = input.nextInt();
			System.out.println("\nEnter m: ");
			maxItemPerRound = input.nextInt();
			if (totalItem < 1) {
				System.out.println("n less than 1, set to DEFAULT(n = 20)!");
				totalItem = 20;
			}
			if (maxItemPerRound < 1) {
				System.out.println("m less than 1, set to DEFAULT(m = 3)!");
				maxItemPerRound = 3;
			}
		} else if (ask.equals("N") || ask.equals("n")) {
			System.out.println("DEFAULT");
		} else {
			System.out.println("Your input either \"Y\" or \"N\", set to DEFAULT");
		}

		System.out.println("\nGame Start!!\n");
		System.out.println("n = "+totalItem+", m = "+maxItemPerRound+", minimum per round = 1\n");
		boolean end = false;
		boolean playerfirst;
		int a = maxItemPerRound + 1;
		int k = totalItem / a;
		int b = totalItem - (a * k);
		int cFirstTaken = 0;
		if (b == 1) {
			playerfirst = true;
		} else {
			playerfirst = false;
		}
		if (!playerfirst) {
			if (b == 0) {
				cFirstTaken = maxItemPerRound;
			} else {
				cFirstTaken = b - 1;
			}
		}
		boolean firstRound = true;
		int number = 0;
		int cNumber;
		do {
			if (playerfirst) {
				System.out.println("Current Item Number: " + totalItem);
				number = player1(maxItemPerRound, totalItem);
				totalItem -= number;
				System.out.println("Current Item Number: " + totalItem);
				if (totalItem == 0) {
					System.out.println("Player 1 LOSE");
					break;
				}
				cNumber = computer(maxItemPerRound, number);
				totalItem -= cNumber;
				if (totalItem == 0) {
					System.out.println("Computer LOSE");
					break;
				}
			} else {
				System.out.println("Current Item Number: " + totalItem);
				if (firstRound) {
					cNumber = cFirstTaken;
					firstRound = false;
					System.out.println("\nComputer Turn!!");
					System.out.println("Number of Taken: " + cNumber);
				} else {
					cNumber = computer(maxItemPerRound, number);
				}
				totalItem -= cNumber;
				System.out.println("Current Item Number: " + totalItem);
				if (totalItem == 0) {
					System.out.println("Computer LOSE");
					break;
				}
				number = player1(maxItemPerRound, totalItem);
				totalItem -= number;
				if (totalItem == 0) {
					System.out.println("Current Item Number: " + totalItem);
					System.out.println("Player 1 LOSE");
					break;
				}
			}
		} while (!end);
	}

	public int computer(int max, int numberplayertake) {
		int num = max + 1 - numberplayertake;
		System.out.println("\nComputer Turn!!");
		System.out.println("Number of Taken: " + num);
		return num;
	}

	public int player1(int max, int left) {
		System.out.println("\nPlayer 1 Turn!!");
		System.out.print("Number of Taken(Not More Than " + max + "): ");
		int number = input.nextInt();
		while (number > max || number > left) {
			System.out.println("");
			if (number > max) {
				System.out.println("The input:"+number+" is more than number m: " + max);
			}
			if (number > left) {
				System.out.println("The number n:" + left + " is less than input: " + number);
			}
			System.out.println("\nPlease Enter Again.");
			System.out.print("Number of Taken(Not More Than " + max + "): ");
			number = input.nextInt();
		}
		return number;
	}
}
