import java.util.ArrayList;

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
	
	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		} else if (other == null) {
			return false;
		} else if (other instanceof Position) {
			return ((this.i == ((Position) other).getI()) && (this.j == ((Position) other).getJ()));
		}
			return false;
	}
	
	public static void main(String[] args) {
		ArrayList<Position> newList = new ArrayList<Position>();
		Position p1 = new Position(1,2);
		Position p2 = new Position(3,4);
		Position p3 = new Position(1,1);
		Position p4 = new Position(1,2);
		newList.add(p1);
		newList.add(p2);
		
		if (newList.contains(p3)) {
			System.out.println("Element p3 is in the list.");
		} else {
			System.out.println("Element p3 is not in the list.");
		}
		
		if (newList.contains(p4)) {
			System.out.println("Element p4 is in the list.");
		} else {
			System.out.println("Element p4 is not in the list.");
		}
		
		
		
	}
	
}