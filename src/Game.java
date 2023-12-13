import java.util.Random;
import java.util.Scanner;

public class Game {
	Utils utils = new Utils();
	Random rand = new Random();
	Scanner s = new Scanner(System.in);
	final int minHeight = 600;
	final int minWidth = 800;
	
	public Game() {
		Player player = createPlayer();
		Player bot = createBot(rand);
		int turn = coinflip();
		boolean isGameOver = false;
		do {
			switch (turn) {
				case 0: {
					System.out.println("Player's Turn");
					displayAttacks(player);
					int turnAttack = utils.instertInt("chose a ability: ", 0, 3);
					player.getRobot().attack(player.getRobot().attacks[turnAttack], bot.getRobot());
					turn = 1;
					isGameOver = checkWinner(player, bot);
					break;
				}
				case 1: {
					System.out.println("Enemy's Turn");
					bot.getRobot().attack(bot.getRobot().attacks[botIA()], player.getRobot());
					turn = 0;
					isGameOver = checkWinner(player, bot);
					break;
				}
			}
		} while(!isGameOver);
	}
	
	public Player createPlayer() {
		boolean error;
		String name;
		do {
			error = false;
			System.out.println("Insert player name: ");
			name = s.nextLine();
			if (name.length() > 8) {
				System.out.println("Name can't be longer than 8 letters");
				error = true;
			}
		} while (error);
		
		System.out.println("0. Titanium Robot");
		System.out.println("1. Platinum Robot");
		int playerSelect = utils.instertInt("Select a robot: ", 0, 1);
		
		Robot.Type robotType = Robot.Type.values()[playerSelect];
		
		Robot playerRobot = robotType.create.get();
		return new MainPlayer(name, playerRobot);
	}

	public static Player createBot(Random rand) {
		Robot.Type robotType = Robot.Type.values()[rand.nextInt(0, 2)];
		Robot playerRobot = robotType.create.get();
		return new Bot("Bot", playerRobot);
		
		
	}
	
	public int coinflip() {
		boolean StarterPlayer = (rand.nextBoolean())? true:false;;
		if (StarterPlayer == true) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public void displayAttacks(Player player) {
		for (int i = 0; i < player.getRobot().attacks.length; i++) {
			System.out.print(i + ". " + player.getRobot().attacks[i].getName());
			if (player.getRobot().getEnergy() < player.getRobot().attacks[i].getEnergyCost()) {
				System.out.print("\t (insufficient energy)");
			}
			System.out.println();
		}
	}
	
	public int botIA() {
		int botAttack = rand.nextInt(0, 4);
		return botAttack;
	}
	
	public boolean checkWinner(Player player, Player bot) {
		boolean gameOver = false;
		if (player.getRobot().getHealth() <= 0) {
			gameOver = true;
			System.out.println("¡Bot Wins!");
		} else if (bot.getRobot().getHealth() <= 0) {
			gameOver = true;
			System.out.println("¡" + player.getName() + " Wins!");
		} else if (player.getRobot().getEnergy() < 50 && bot.getRobot().getEnergy() < 50) {
			System.out.println("¡It's a tie!");
			System.out.println("Both of you run out of energy");
			gameOver = true;
		}
		
		
		return gameOver;
	}
}
