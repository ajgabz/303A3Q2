import java.util.ArrayList;

public class World {
	private WorldObject[][] myWorld;
	private int numRows;
	private int numCols;
	
	//private ArrayList<Autonomous> autonomousBlocks;
	
	public World(int rows, int columns){
		this.numRows = rows;
		this.numCols = columns;
		
		//this.autonomousBlocks = new ArrayList<Position>();
		this.myWorld = new WorldObject[numRows][numCols];
	}
	
	public void step() {
		for (int i = 0; i < this.numRows; i++) {
			for (int j = 0;  j < this.numCols; j++) {
				if (myWorld[i][j] instanceof Autonomous) {
					
				}
			}
		}
	}
	
	
	
	public WorldObject[][] getMyWorld(){
		WorldObject[][] clone = this.myWorld.clone();
		return clone; 
	}
	
	
	public void display(){
		int h = this.numRows; 
		int w = this.numCols; 
		
		for(int j = 0; j < h; j++){
			for(int i = 0; i < w; i++){
				if(this.myWorld[i][j] == null){
					System.out.print(" ");
				}else{
					System.out.print(this.myWorld[i][j].getToken());
				}
			}
		}
		
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isCellEmpty(int x, int y){
		return (this.myWorld[x][y] == null);
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isValidIndex(int x, int y) {
		int maxAccessibleHeight = this.numRows - 1;
		int maxAccessibleWidth = this.numCols - 1;
		
		return ((0 <= x) && (x <= maxAccessibleHeight) && (0 <= y) && (y <= maxAccessibleWidth));
	}
	
	
	public void add(WorldObject obj, int x, int y){
		
		if (isCellEmpty(x,y)) {
			this.myWorld[x][y] = obj;
		} else {
			throw new IllegalArgumentException();
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
