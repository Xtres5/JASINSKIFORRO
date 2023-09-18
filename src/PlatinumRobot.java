
public class PlatinumRobot extends Robot{

	public PlatinumRobot() {
		super(1200, 1000, Attack.Type.IMPACT, Attack.Type.CORROSION);
		attacks[0] = new MachineGun();
		attacks[1] = new Acid();
		attacks[2] = new Fire();
		attacks[3] = new Shield();
	}

}
