// Aaron Gaba (Student ID: 260450580)
// Benjamin Taubenblatt (Student ID: 260626105)

import java.util.Random;

/**
 * An Autonomous object is a Movable object with the ability to move on its own accord.
 * That is, an Autonomous object can generate potential moves, which the World object
 * (that it resides in) will attempt to move.
 */
public class Autonomous extends Movable{
	
	private Random rn = new Random();
	
	/**
	 * Constructs an Autonomous object with the given character token and name description
	 * @param objectToken Character token of the WorldObject
	 * @param objectName Name (description) of the WorldObject
	 */
	public Autonomous(char objectToken, String objectName) {
		super(objectToken, objectName);
	}
	
	/**
	 * The 'step' method returns a random direction for the Autonomous object to move.
	 * Each of the four directions is equally likely.
	 * 
	 * @return Direction for this autonomous object to move
	 */
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
		return newStep;
	}
	

}
