package entity;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;


import org.joml.Vector3f;


import main.Camera;
import main.Model;
import main.Shader;
import main.Window;
import world.World;

public class Player {
	private Model model;
	private Animation texture;
	private Transform transform;
	private float speed = 1.0f;
	double angle = 0;

	public Player() {
		float[] vertices = new float[] {
				-1f, 1f, 0, //TOP LEFT     0
				1f, 1f, 0,  //TOP RIGHT    1
				1f, -1f, 0, //BOTTOM RIGHT 2
				-1f, -1f, 0,//BOTTOM LEFT  3
		};
		
		float[] texture = new float[] {
				0,0,
				1,0,
				1,1,
				0,1,
		};
		
		int[] indices = new int[] {
				0,1,2,
				2,3,0
		};
		
		model = new Model(vertices, texture, indices);
		this.texture = new Animation(5, 65, "player");
//		this.texture = new Texture("player.png");
		
		transform = new Transform();
		transform.scale = new Vector3f(32,32,1);
	}
	
	public void update(float delta, Window window, Camera camera, World world){

		float x = (float) window.getCursorPosX();
		float y = (float) window.getCursorPosY();
		double pX = 400;
		double pY = 300;
		double dx = x - pX;
		double dy = y - pY;
		float angle = (float) Math.atan2(dy, dx);
//		System.out.println("Cursor is X: "+ x +" Y: " + y +" Angle: " + angle);
		
		transform.angle = angle;	
	
		if(window.getInput().isKeyDown(GLFW_KEY_A)) 
			transform.pos.add(new Vector3f(-10*delta*speed, 0, 0));
		
		if(window.getInput().isKeyDown(GLFW_KEY_D)) 
			transform.pos.add(new Vector3f(10*delta*speed, 0, 0));
				
		if(window.getInput().isKeyDown(GLFW_KEY_W)) 
			transform.pos.add(new Vector3f(0, 10*delta*speed, 0));
		
		if(window.getInput().isKeyDown(GLFW_KEY_S)) 
			transform.pos.add(new Vector3f(0, -10*delta*speed, 0));		
		
		if(window.getInput().isMouseButtonDown(0)){

		}
		
		camera.setPosition(transform.pos.mul(-world.getScale(), new Vector3f()));

	}
	
	public void render(Shader shader, Camera camera){
		shader.bind();
		shader.setUniform("sampler", 0);
		shader.setUniform("projection", transform.getProjection(camera.getProjection()));
		texture.bind(0);
		model.render();
 
	}
	

}
