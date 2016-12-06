// Aaron Gaba (Student ID: 260450580)
// Benjamin Taubenblatt (Student ID: 260626105)

/**
 * An Immovable object is a WorldObject that cannot be moved within the World object 
 * that it is placed in.
 */
public class Immovable extends WorldObject {

	/**
	 * Constructs an Immovable object with the given character token and name description
	 * @param objectToken Character token of the WorldObject
	 * @param objectName Name (description) of the WorldObject
	 */
	public Immovable(char objectToken, String objectName) {
		super(objectToken, objectName);
	}
	
}
