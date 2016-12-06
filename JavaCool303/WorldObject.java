// Aaron Gaba (Student ID: 260450580)
// Benjamin Taubenblatt (Student ID: 260626105)

/**
 * A WorldObject is the generic form of the block inhabitants of a World.
 * For identification and descriptive purposes, every WorldObject has a name
 * and for the purposes of displaying, every WorldObject has a character token.
 */
public abstract class WorldObject {

	private char token;
	private String name;
	
	/**
	 * Constructs a WorldObject with the given character token and name description
	 * @param objectToken Character token of the WorldObject
	 * @param objectName Name (description) of the WorldObject
	 */
	public WorldObject(char objectToken, String objectName) {
		this.token = objectToken;
		this.name = objectName;
	}
	
	/**
	 * @return The WorldObject's character token
	 */
	public char getToken() {
		return token;
	}
	/** The 'setToken' sets the given WorldObject's character token.
	 *  All characters, except for the empty block indicator character '░',
	 *  are legal.
	 * @param token 
	 */
	public void setToken(char token) {
		if (token != '░') {
		  this.token = token;
		} else {
			throw new IllegalArgumentException("Cannot use the empty block indicator character '░' as a token.");
		}	
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
