import java.util.ArrayList;

public class World {
	private WorldObject[][] myWorld;
	private int numRows;
	private int numCols;
	
	private ArrayList<Position> previouslyVisited = new ArrayList<Position>();
	
	public World(int rows, int columns){
		this.numRows = rows;
		this.numCols = columns;
		
		//this.autonomousBlocks = new ArrayList<Position>();
		this.myWorld = new WorldObject[numRows][numCols];
	}
	
	public void step() {
		
		Direction attemptedStep;
		
		for (int i = 0; i < this.numRows; i++) {
			for (int j = 0;  j < this.numCols; j++) {
				if ((myWorld[i][j] instanceof Autonomous) && (!alreadyMoved(i,j))) {
					attemptedStep = ((Autonomous) myWorld[i][j]).step();
					System.out.println("Attempting to move Autonomous Object: " + myWorld[i][j].getName() + " " + attemptedStep.toString());
					System.out.println("Current Position of " + myWorld[i][j].getName() + ":(" + i + "," + j + ")");
					if (move(i,j, attemptedStep)) {
						System.out.println("Move Successful!");
						this.previouslyVisited.add(newLocation(i, j, attemptedStep));
						Position newStep = newLocation(i, j, attemptedStep);
						System.out.println("New Position: (" + newStep.getI() + "," + newStep.getJ() + ")");
					} else {
						System.out.println("Move Unsuccessful!");
					}
				}
			}
		}
		
		this.previouslyVisited.clear();
	}
	
	private Position newLocation(int oldX, int oldY, Direction attemptedMove) {
		int newX = oldX; 
		int newY = oldY;
		
		switch (attemptedMove) {
		case NORTH:
			newX--;
			break;
		case SOUTH:
			newX++;
			break;
		case EAST:
			newY++;
			break;
		case WEST:
			newY--;
			break;
		}
		
		Position newLoc = new Position(newX, newY);
		return newLoc;
	}
	
	private boolean alreadyMoved(int x, int y) {
		return (this.previouslyVisited.contains(new Position(x, y)));
	}
	
	
	private boolean move(int i, int j, Direction attemptedMove) {
		int xSearch = i; 
		int ySearch = j;
		
		switch (attemptedMove) {
		case NORTH:
			xSearch--;
			break;
		case SOUTH:
			xSearch++;
			break;
		case EAST:
			ySearch++;
			break;
		case WEST:
			ySearch--;
			break;
		}
		
		// First, check the boundary conditions
		if (isValidIndex(xSearch, ySearch)) {
			if (myWorld[xSearch][ySearch] == null) {
				swap(i, j, xSearch, ySearch);
				return true;
			} else if (myWorld[xSearch][ySearch] instanceof Immovable) {
				return false;
			} else if (myWorld[xSearch][ySearch] instanceof Movable) {
				if (move(xSearch, ySearch, attemptedMove)) {
					swap(i, j, xSearch, ySearch);
					return true;
				} else {
					return false;
				}
			}
		} 
		
		return false;
		
	}
	
	private void swap(int r1, int c1, int r2, int c2) {
		WorldObject temp = this.myWorld[r1][c1];
		this.myWorld[r1][c1] = this.myWorld[r2][c2];
		this.myWorld[r2][c2] = temp;
	}
	
	/*private boolean move(int i, int j, Direction attemptedMove) {
		int xSearch = i; int ySearch = j; int delta; int boundary;
		
		
		
		//Check border conditions first
		if (isMoveWithinBorders(i, j, attemptedMove)) {
			
			if (attemptedMove == Direction.NORTH) {
				for (int i = x; i >= 0; i--) {
					
			}
			
			
			switch (attemptedMove) {
				case NORTH:
					boundary = 0;
					delta = -1;
					break;
				case SOUTH:
					boundary = this.numRows - 1;
					delta = 1;
					break;
				case EAST:
					boundary = this.numCols - 1;
					delta = 1;
					break;
				case WEST:
					boundary = 0;
					delta = -1;
					break;
			}
			
			while 
			
		}
		
	}*/
	
	private boolean isMoveWithinBorders(int i, int j, Direction attemptedMove) {
		int newRow = i;
		int newCol = j;
		
		switch (attemptedMove) {
			case NORTH:
				newRow--;
				break;
			case SOUTH:
				newRow++;
				break;
			case EAST:
				newCol++;
				break;
			case WEST:
				newCol--;
				break;
		}
		
		return (isValidIndex(newRow, newCol));
		}
		
	
	
	
	public WorldObject[][] getMyWorld(){
		WorldObject[][] clone = this.myWorld.clone();
		return clone; 
	}
	
	
	public void display(){
		int h = this.numRows; 
		int w = this.numCols; 
		
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if(this.myWorld[i][j] == null){
					System.out.print("░");
				}else{
					System.out.print(this.myWorld[i][j].getToken());
				}
			}
			System.out.println("");
		}
		
		
		/*for(int j = 0; j < h; j++){
			for(int i = 0; i < w; i++){
				if(this.myWorld[i][j] == null){
					System.out.print(" ");
				}else{
					System.out.print(this.myWorld[i][j].getToken());
				}
			}
		}*/
		
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
		World testWorld = new World(6,6);
		
		Autonomous a1 = new Autonomous('A', "a1");
		Autonomous a2 = new Autonomous('A', "a2");
		Autonomous a3 = new Autonomous('A', "a3");
		
		Immovable i1 = new Immovable('I', "i1");
		Immovable i2 = new Immovable('I', "i2");
		Immovable i3 = new Immovable('I', "i3");
		Immovable i4 = new Immovable('I', "i4");
		
		Movable m1 = new Movable('M', "m1");
		Movable m2 = new Movable('M', "m2");
		Movable m3 = new Movable('M', "m3");
		
		testWorld.add(a1, 1, 0);
		//testWorld.add(a2, 2, 1);
		//testWorld.add(a3, 4, 2);
		
		//testWorld.add(i1, 2, 0);
		//testWorld.add(i2, 2, 3);
		//testWorld.add(i3, 4, 0);
		//testWorld.add(i4, 4, 3);
		
		testWorld.add(m1, 1, 1);
		testWorld.add(m2, 1, 3);
		//testWorld.add(m3, 3, 2);
		
		testWorld.display();
		System.out.println("-------------");
		for (int i = 0; i <= 9; i++) {
			System.out.println("Step " + (i+1));
			testWorld.step();
			testWorld.display();
			System.out.println("-------------");
		}
		
	}

}
