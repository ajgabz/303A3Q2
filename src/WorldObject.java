
public abstract class WorldObject {

	private char token;
	private String name;
	
	public WorldObject(char objectToken, String objectName) {
		this.token = objectToken;
		this.name = objectName;
	}
	
	/**
	 * @return the token
	 */
	public char getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(char token) {
		this.token = token;
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
