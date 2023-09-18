

public class TitaniumRobot extends Robot{

	
	public TitaniumRobot() {
		super(1000, 1500, Attack.Type.FIRE, Attack.Type.IMPACT);
		attacks[0] = new MachineGun();
		attacks[1] = new Acid();
		attacks[2] = new Fire();
		attacks[3] = new ElectromagneticInterference();
	}

}
