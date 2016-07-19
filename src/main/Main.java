package main;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL;

import entity.Player;
import world.Tile;
import world.TileRenderer;
import world.World;
import world.WorldMap;

public class Main {

	public Main() {
//		Window.setCallbacks();
		
		if(!glfwInit()){
			System.err.println("GLFW Failed to initialise!");
			System.exit(1);
		}
		
		Window window = new Window();
		window.setSize(800, 600);
		window.setFullscreen(false);
		window.createWindow("Erythia");
				
		GL.createCapabilities();
		
		Camera camera = new Camera(800, 600);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		TileRenderer tiles = new TileRenderer();
		
		Shader shader = new Shader("shader");
		
		Player player = new Player();	
		World world = new World();
		WorldMap canosia = new WorldMap("canosia.png");

		canosia.generate();

		
		double frameCap = 1.0/60.0;
		
		double frameTime = 0;
		int frames = 0;
		
		double time = Timer.getTime();
		double unprocessed = 0;
	
		while(window.shouldClose()){
			boolean canRender = false;
			
			double time2 = Timer.getTime();
			double passed = time2 - time;
			unprocessed += passed;
			frameTime += passed;
			time = time2;
			
			while(unprocessed >= frameCap){
				unprocessed -= frameCap;
				canRender = true;
				

			
				
				if(window.getInput().isKeyPressed(GLFW_KEY_ESCAPE)){
					glfwSetWindowShouldClose(window.getWindow(), true);
				}

				
				player.update((float) frameCap, window, camera, world);
				world.correctCamera(camera, window);
				
				window.update();

			}
			
			if(canRender){
				glClear(GL_COLOR_BUFFER_BIT);
		
				world.render(tiles, shader, camera, window);
				player.render(shader, camera);
				
				window.swapBuffers();
				frames++;
			}
		
	
		}
		
		glfwTerminate();
		
	}

	public static void main(String[] args) {
		new Main();

	}

}
