import java.util.Random;
import java.util.function.Supplier;

public abstract class Robot {
	private int health;
	private int energy;
	private final Attack.Type weakness;
	private final Attack.Type resistance;
	Attack attacks[] = new Attack[4];
	State state;
	
	Robot(int health, int energy, Attack.Type weakness, Attack.Type resistance) {
		this.health = health;
		this.energy = energy;
		this.weakness = weakness;
		this.resistance = resistance;
	}
	
	public enum Type {
		//Aclaro de una que me parecia horrible hacer un switch asi que me fije en internet y le pregunte a tinchikupi
		TITANIUM("titanium", TitaniumRobot::new),
		PLATINUM("platinum", PlatinumRobot::new);
		
		public final Supplier<Robot> create;
		public final String name;
		
		private Type (String name, Supplier<Robot> create) {
			this.name = name;
			this.create = create;
		}
	}
	
	public enum State {
		SHIELDED,
		STUNNED;
	}
	
	@Override
	public String toString() {
		return """
			Health: %-30d  Energy: %d
			Attack 1: %-30sAttack 2: %s
			Attack 3: %-30sAttack 4: %s
			""".formatted(health, energy, attacks[0].getName(), attacks[1].getName(), attacks[2].getName(), attacks[3].getName());
	}
	
	public void attack(Attack attack, Robot objective) {
		//La verdad es mas facil (y en mi opinion mas logico) crear un solo metodo que puede hacer todo, al menos en este 
		//ejercicio que solo se puede recibir daño mediante ataques
		Random rand = new Random();
		if (this.energy >= attack.getEnergyCost()) {
			this.energy -= attack.getEnergyCost();
			if (rand.nextInt(0, 101) <= attack.getHitChance()) {
				double multiplier;
				if (attack.getType() == objective.weakness) {
					multiplier = 2;
					System.out.println(attack.getName() + " is strong. Crit strike!");
				} else if (attack.getType() == objective.resistance){
					multiplier = 0.5;
					System.out.println(attack.getName() + " is weak. Half damage!");
				}
					else {
					multiplier = 1;
					System.out.println(attack.getName() + " is effective");
				}
				objective.health -= (attack.getDamage() * multiplier);
			} else System.out.println("You've Missed.");
		} else System.out.println("You' dont Have enough energy, lose turn");
		
		System.out.println("Enemy's health: " + objective.health);
	}
	
	public Attack[] getAttacks() {
		return attacks;
	}
	
	public int getEnergy() {
		return energy;
	}
	
	public int getHealth() {
		return health;
	}
}

