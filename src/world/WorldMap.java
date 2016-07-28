package world;


import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class WorldMap {
	public static final int WORLD_WIDTH = 64, WORLD_HEIGHT = 64;
	static int[][] mapA = new int[WORLD_WIDTH][WORLD_HEIGHT];
	static World worldMap = new World();


	
	public WorldMap(String filename) {
		try{
			BufferedImage map = ImageIO.read(new File("./res/"+filename));
			mapA = pixelColour(map);
		}catch(Exception e){
			e.printStackTrace();
		}

	}	
	public static int[][] pixelColour(BufferedImage image) {

	      int[][] result = new int[WORLD_HEIGHT][WORLD_WIDTH];

	      for (int x = 0; x < WORLD_WIDTH; x++) {
	         for (int y = 0; y < WORLD_HEIGHT; y++) {
	            result[x][y] = image.getRGB(x, y);

	         }
	      }

	      return result;
	   }

	public void generate(){
		for(int x = 0;x < WORLD_WIDTH;x++){
			for(int y = 0;y < WORLD_HEIGHT;y++){
				int col = mapA[x][y];

				switch (col& 0xFFFFFF){
				case 0x0000FF:
					worldMap.setTile(Tile.water, x, y);

					break;
				case 0xFF0000:
					worldMap.setTile(Tile.stone, x, y);

					break;
				case 0x00FF00:
					worldMap.setTile(Tile.grass, x, y);
	
					break;
				default:
					System.out.println("block fail");
					break;
				}
			}
		}
	}
	
}
