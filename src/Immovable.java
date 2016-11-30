
public class Immovable extends WorldObject {

	public Immovable(char objectToken, String objectName) {
		super(objectToken, objectName);
		// TODO Auto-generated constructor stub
	}
	
	public Direction step(){
		Direction nonstep = NORTH;
		return nonstep;
	}

}
