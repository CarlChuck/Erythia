package world;


public class Tile {
	public static Tile tiles[] = new Tile[16];
	public static byte not = 0;
	
	public static final Tile grass = new Tile("grass").isSolid(false);
	public static final Tile stone = new Tile("stone").isSolid(true);
	public static final Tile water = new Tile("water").isSolid(true);
	
	private byte id;
	private String texture;
	public boolean isSolid = false;
	
	public Tile(String texture) {
		this.id = not;
		not++;
		this.texture = texture;
		if(tiles[id] != null)
			throw new IllegalStateException("Tiles at ["+id+"] is already being used!");
		tiles[id] = this;
	}

	public byte getId( ) {
		return id;
	}

	public String getTexture() {
		return texture;
	}

	public Tile isSolid(boolean isSolid){
		this.isSolid = isSolid;
		return this;

	}
	public boolean isSolid() {
		return isSolid;
	}


}