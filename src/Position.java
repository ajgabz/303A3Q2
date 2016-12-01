public class Position {
	private int i;
	private int j;
	
	public Position(int iObj, int jObj){
		this.i = iObj;
		this.j = jObj;
	}
	
	public int getI(){
		return this.i;
	}
	
	public int getJ(){
		return this.j;
	}
	
	public boolean equals(Position other) {
		return ((this.i == other.getI()) && (this.j == other.getJ()));
	}
	
}