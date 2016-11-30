
public class Movable extends WorldObject{

	public Movable(char objectToken, String objectName) {
		super(objectToken, objectName);
	}
	
	public Direction step(){
		return NULLSTEP;
	}
}
