import java.util.Random;

public class Autonomous extends Movable{
	
	private RandomMotion engine;
	private Random rn = new Random();
	
	
	public Autonomous(char objectToken, String objectName) {
		super(objectToken, objectName);
		//this.engine = move;
		// TODO Auto-generated constructor stub
	}
	
	public Direction step() {
		int step = rn.nextInt(4);
		Direction newStep = Direction.NORTH;
		switch (step) {
			case 0:
				newStep = Direction.NORTH;
				break;
			case 1:
				newStep = Direction.EAST;
				break;
			case 2:
				newStep = Direction.WEST;
				break;
			case 3:
				newStep = Direction.SOUTH;
				break;
		}
		newStep = Direction.EAST;
		return newStep;
	
	}
	

}
