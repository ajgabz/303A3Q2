// Aaron Gaba (Student ID: 260450580)
// Benjamin Taubenblatt (Student ID: 260626105)

import java.util.ArrayList;

public class World {
	private WorldObject[][] myWorld;
	private int numRows;
	private int numCols;
	
	//For each step, this list keeps track of the new positions of 
	//autonomous objects that have moved.  This prevents autonomous objects
	//from getting multiple turns, as we iterate through the 2D world.
	
	private ArrayList<Position> previouslyVisited = new ArrayList<Position>();
	
	/**
	 * The World constructor constructs a new 2D world of size [rows][columns].
	 * @param rows Number of rows in the 2D world
	 * @param columns Number of columns in the 2D world
	 */
	public World(int rows, int columns){
		if ((rows > 0) && (columns > 0)) {
			this.numRows = rows;
			this.numCols = columns;
			this.myWorld = new WorldObject[numRows][numCols];
		} else {
			throw new IllegalArgumentException("The number of rows and columns must both be positive integers.");
		}
		
	}
	
	/**
	 * The 'step' method advances the simulation of the setup world by one step.
	 * That is, each autonomous object can make one step, for which other objects
	 * may move because of collisions caused by these moving autonomous objects.
	 */
	public void step() {
		
		Direction attemptedStep;
		
		for (int i = 0; i < this.numRows; i++) {
			for (int j = 0;  j < this.numCols; j++) {
				
				//If the WorldObject at position (i,j) is an Autonomous object,
				//which has not already been moved, then we will attempt to move it.
				if ((myWorld[i][j] instanceof Autonomous) && (!alreadyMoved(i,j))) {
					attemptedStep = ((Autonomous) myWorld[i][j]).step();
					
					//Attempt to move the given Autonomous object in the direction
					//that it generated.  If it can be moved, record its new position.
					if (move(i,j, attemptedStep)) {
						this.previouslyVisited.add(newLocation(i, j, attemptedStep));
					} 
				}
			}
		}
		
		//After we have traversed through the entire world and (attempted) to move all
		//Autonomous objects, this step of the simulation has now finished and thus,
		//we will clear the list of positions for the next step.
		this.previouslyVisited.clear();
	}
	
	/**
	 * Given a starting row and column and a direction, the 'newLocation' method
	 * generates a Position object of the new row and column under that direction.
	 * @param oldX Starting row
	 * @param oldY Starting column
	 * @param attemptedMove Direction to be moved
	 * @return
	 */
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
	
	/**
	 * The 'alreadyMoved' method checks if the given position (x,y) 
	 * is recorded in the previouslyVisited list.  If yes, it returns true.
	 * @param x Specified row
	 * @param y Specified column
	 * @return True, if (x,y) is contained in the list previouslyVisited; false, otherwise.
	 */
	private boolean alreadyMoved(int x, int y) {
		return (this.previouslyVisited.contains(new Position(x, y)));
	}
	
	/**
	 * The 'move' method recursively attempts to move the specified Movable object at position (x,y)
	 * under the specified direction.  As a precondition, the object at position (i,j) must be a Movable object.
	 * @param i
	 * @param j
	 * @param attemptedMove 
	 * @return True, if the object has been moved and false, if it can't be moved.
	 */
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
			//If an attempted move hits an immovable object, return false
			} else if (myWorld[xSearch][ySearch] instanceof Immovable) {
				return false;
			//If an attempted move hits a movable object, check to see
			//if said movable object can move in the same direction
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
	
	/**
	 * The 'swap' method swaps WorldObjects held at positions (r1, c1) and (r2, c2).
	 * As a precondition, (r1, c1) and (r2, c2) are both valid positions in the world.
	 * @param r1
	 * @param c1
	 * @param r2
	 * @param c2
	 */
	private void swap(int r1, int c1, int r2, int c2) {
		WorldObject temp = this.myWorld[r1][c1];
		this.myWorld[r1][c1] = this.myWorld[r2][c2];
		this.myWorld[r2][c2] = temp;
	}
	
	
	public WorldObject[][] getMyWorld(){
		WorldObject[][] clone = this.myWorld.clone();
		return clone; 
	}
	
	/**
	 * The 'display' method prints to the screen a 2D representation of the world,
	 * where at each occupied block, that WorldObject's token is printed.
	 * 
	 * To denote an empty block in the world, the ASCII character '░' is printed.
	 */
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
	}
	
	/**
	 * The 'isCellEmpty' method checks to see if there is a WorldObject held at position (x,y).
	 * As a precondition, (x,y) is a valid location.
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean isCellEmpty(int x, int y){
		return (this.myWorld[x][y] == null);
	}
	
	/**
	 * Given x,y parameters, the 'isValidIndex' returns true if the 2D index of World[x][y]
	 * is valid.
	 * @param x Row parameter
	 * @param y Column parameter
	 * @return Boolean if World[x][y] is a valid location
	 */
	public boolean isValidIndex(int x, int y) {
		int maxAccessibleHeight = this.numRows - 1;
		int maxAccessibleWidth = this.numCols - 1;
		
		return ((0 <= x) && (x <= maxAccessibleHeight) && (0 <= y) && (y <= maxAccessibleWidth));
	}
	
	/**
	 * The 'add' method attempts to store the given WorldObject at the specified location
	 * in the World.  If the specified location is already occupied by another WorldObject,
	 * an error results.
	 * @param obj The WorldObject to be stored
	 * @param x The specified location's row
	 * @param y The specified location's column
	 */
	public void add(WorldObject obj, int x, int y){
		if (isValidIndex(x,y)) {
			if (isCellEmpty(x,y)) {
				this.myWorld[x][y] = obj;
			} else {
				throw new IllegalArgumentException("The location specified is already occupied by another WorldObject.");
			}
		} else {
			throw new IllegalArgumentException("The location specified is not a valid location in this world.");
		}
		
	}
	
	public static void main(String[] args) {
World testWorld = new World(10,15);
		
		Autonomous a1 = new Autonomous('A', "a1");
		Autonomous a2 = new Autonomous('A', "a2");
		Autonomous a3 = new Autonomous('A', "a3");
		Autonomous a4 = new Autonomous('A', "a4");
		Autonomous a5 = new Autonomous('A', "a5");
		Autonomous a6 = new Autonomous('A', "a6");
		Autonomous a7 = new Autonomous('A', "a7");	
		Autonomous a8 = new Autonomous('A', "a8");
		Autonomous a9 = new Autonomous('A', "a9");

		
		Immovable i1 = new Immovable('I', "i1");
		Immovable i2 = new Immovable('I', "i2");
		Immovable i3 = new Immovable('I', "i3");
		Immovable i4 = new Immovable('I', "i4");
		Immovable i5 = new Immovable('I', "i5");
		
		
		Movable m1 = new Movable('M', "m1");
		Movable m2 = new Movable('M', "m2");
		Movable m3 = new Movable('M', "m3");
		Movable m4 = new Movable('M', "m4");
		Movable m5 = new Movable('M', "m5");
		Movable m6 = new Movable('M', "m6");
		Movable m7 = new Movable('M', "m7");
		Movable m8 = new Movable('M', "m8");
		Movable m9 = new Movable('M', "m9");
		Movable m10 = new Movable('M', "m10");
		Movable m11 = new Movable('M', "m11");
		Movable m13 = new Movable('M', "m13");
		Movable m14 = new Movable('M', "m14");
		Movable m15 = new Movable('M', "m15");

		//adding autonomous objects
		testWorld.add(a1, 1, 0);
		testWorld.add(a2, 2, 1);
		testWorld.add(a3, 1, 7);
		testWorld.add(a4, 8, 13);
		testWorld.add(a5, 8, 12);
		testWorld.add(a6, 9, 0);
		testWorld.add(a7, 0, 14);
		testWorld.add(a8, 0, 5);
		testWorld.add(a9, 1, 2);



		//adding immovable objects
		testWorld.add(i1, 2, 0);
		testWorld.add(i2, 5, 8);
		testWorld.add(i3, 3, 13);
		testWorld.add(i4, 4, 3);
		testWorld.add(i5, 0, 3);

		//adding movable objects
		testWorld.add(m1, 1, 1);
		testWorld.add(m2, 1, 3);
		testWorld.add(m3, 9, 1);
		testWorld.add(m4, 9, 13);
		testWorld.add(m5, 7, 13);
		testWorld.add(m6, 8, 0);
		testWorld.add(m7, 0, 6);
		testWorld.add(m8, 5, 7);
		testWorld.add(m9, 0, 12);
		testWorld.add(m10, 0, 13);
		testWorld.add(m11, 1, 14);
		testWorld.add(m13, 1, 5);
		testWorld.add(m14, 1, 6);
		testWorld.add(m15, 2, 2);


		System.out.println("Initial World");
		System.out.println("---------------");
		testWorld.display();
		System.out.println("---------------");
		for (int i = 0; i <= 99; i++) {
			System.out.println("Step " + (i+1));
			testWorld.step();
			testWorld.display();
			System.out.println("---------------");
		}
		
	}

}
