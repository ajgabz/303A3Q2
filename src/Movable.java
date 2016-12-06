// Aaron Gaba (Student ID: 260450580)
// Benjamin Taubenblatt (Student ID: 260626105)

/**
 * A Movable object is a WorldObject that can be moved within the World object 
 * that it is placed in. 
 */
public class Movable extends WorldObject{

	/**
	 * Constructs a Movable object with the given character token and name description
	 * @param objectToken Character token of the WorldObject
	 * @param objectName Name (description) of the WorldObject
	 */
	public Movable(char objectToken, String objectName) {
		super(objectToken, objectName);
	}
	
}
