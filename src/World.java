import java.util.ArrayList;

public class World {
	private WorldObject[][] myWorld;
	private int height;
	private int width;
	private ArrayList<Position> positions;
	
	public World(int worldHeight, int worldWidth){
		this.height = worldHeight;
		this.width = worldWidth;
		
		this.positions = new ArrayList<Position>();
		this.myWorld = new WorldObject[height][width];
		
	}
	
	public WorldObject[][] getMyWorld(){
		WorldObject[][] clone = this.myWorld.clone();
		return clone; 
	}
	
	
	public void display(){
		int h = this.height; 
		int w = this.width; 
		
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
		int maxAccessibleHeight = this.height - 1;
		int maxAccessibleWidth = this.width - 1;
		
		return ((0 <= x) && (x <= maxAccessibleHeight) && (0 <= y) && (y <= maxAccessibleWidth));
	}
	
	
	public void add(WorldObject obj, int x, int y){
		
		if (isCellEmpty(x,y)) {
			this.myWorld[x][y] = obj;
			if (obj instanceof Autonomous) {
				Position pos = new Position(x,y);
				positions.add(pos);
			}
		} else {
			throw new IllegalArgumentException();
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
