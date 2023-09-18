
public abstract class  Player {
	private String name;
	private Robot robot;
	
	public Player(String name, Robot robot) {
		this.name = name;
		this.robot = robot;
	}
	
	public String getName() {
		return name;
	}
	
	public Robot getRobot() {
		return robot;
	}
}
