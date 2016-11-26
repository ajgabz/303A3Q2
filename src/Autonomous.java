
public class Autonomous extends Movable{

	private RandomMotion engine;
	
	public Autonomous(char objectToken, String objectName, RandomMotion move) {
		super(objectToken, objectName);
		this.engine = move;
		// TODO Auto-generated constructor stub
	}
	
	public Direction step(){
		return engine.randomStep();
	}

}
