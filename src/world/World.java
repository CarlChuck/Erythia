package world;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import main.Camera;
import main.Shader;
import main.Window;


public class World {
		static final int view = 24;
		private static byte[] tiles;
		private static int width;
		private int height;
		static int scale;
		
		private Matrix4f world;
		
		public World() {
			width = WorldMap.WORLD_WIDTH;
			height = WorldMap.WORLD_HEIGHT;
			scale = 64;
			
			tiles = new byte[width * height];
			
			world = new Matrix4f().setTranslation(new Vector3f(0));
			world.scale(scale);
		}
		
		public void render(TileRenderer render, Shader shader, Camera cam, Window window) {
			int posX = ((int)cam.getPosition().x + (window.getWidth()/2)) / (scale);
			int posY = ((int)cam.getPosition().y - (window.getHeight()/2)) / (scale);
			
			for(int i = 0; i < view; i++) {
				for(int j = 0; j < view; j++) {
					Tile t = getTile(i-posX, j+posY);
					if(t != null) {
						render.renderTile(t, i - posX, -j - posY, shader, world, cam);
					}

				}
			}
			
		}
		
		public void correctCamera(Camera camera, Window window) {
			Vector3f pos = camera.getPosition();
			
			int w = -width * scale;
			int h = height * scale;
			
			if(pos.x > -(window.getWidth()/2)+scale) 
				pos.x = -(window.getWidth()/2)+scale;
			if(pos.x < w + (window.getWidth()/2) + scale)
				pos.x = w + (window.getWidth()/2) + scale;
			
			if(pos.y < (window.getHeight()/2)-scale)
				pos.y = (window.getHeight()/2)-scale;
			if(pos.y > h-(window.getHeight()/2)-scale)
				pos.y = h-(window.getHeight()/2)-scale;
		}
		
		public void setTile(Tile tile, int x, int y) {
			tiles[x + y * width] = tile.getId();
		}

		public static Tile getTile(int x, int y) {
			try {
				return Tile.tiles[tiles[x + y * width]];
			}catch(ArrayIndexOutOfBoundsException e) {
				return null;
			}

		}

		public int getScale() { 
			return scale; 
			}
	}