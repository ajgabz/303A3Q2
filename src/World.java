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
	
	public boolean canAddAt(int x, int y){
		if(this.myWorld[x][y] == null){
			return true;
		}else{
			return false; 
		}
	}
	
	public void add(WorldObject obj){
		int h = this.height; 
		int w = this.width; 
		
		for(int i = 0; i < w; i++){
			for(int j = 0; j < h; j++){
				if(canAddAt(i,j) == true){
					if(obj instanceof Autonomous ){
						Position pos = new Position(i,j);
						positions.add(pos);
					}
					this.myWorld[i][j] = obj;
					return;
				}
			}
		}
		
		throw new IllegalArgumentException();
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
