package world;

public class Tile {
	public static Tile tiles[] = new Tile[16];
	public static byte not = 0;
	
	public static final Tile grass = new Tile("grass");
	public static final Tile stone = new Tile("stone");
	public static final Tile water = new Tile("water");
	
	private byte id;
	private String texture;
	
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

	public String getTexture( ) {
		return texture;
	}
}