
public abstract class Attack {
	private final String name;
	private final int damage;
	private final int energyCost;
	private final int hitChance;
	private final Type type;
	
	Attack(String name, int damage, int energyCost, int hitChance, Type type) {
		this.name = name;
		this.damage = damage;
		this.energyCost = energyCost;
		this.hitChance = hitChance;
		this.type = type;
	}
	
	public enum Type {
		IMPACT,
		CORROSION,
		FIRE,
		EFFECT;
	}
	
	public String getName() {
		return name;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public int getEnergyCost() {
		return energyCost;
	}
	
	public int getHitChance() {
		return hitChance;
	}
	
	public Type getType() {
		return type;
	}
}
