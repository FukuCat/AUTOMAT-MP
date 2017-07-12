package engine.drawable;

import java.awt.BasicStroke;
import java.awt.Color;

import jGame.model.game.GameObject;
import jGame.model.graphics.Camera;
import jGame.model.input.Input;
import jGame.model.physics.Physics3D;
import jGame.view.Renderer;

public class XObject extends Physics3D implements GameObject{

	private Color fill;
	private int size;
	private int stroke;
	
	public XObject(int x, int y){
		super();
		fill = Color.RED;
		size = 100;
		stroke = 3;
		
		position.setValue(x * size, y * size, 0.0f);
	}

	@Override
	public void reset() {}
	
	@Override
	public void input(Input input, long deltaTime, Camera camera) {}

	@Override
	public void logic(long deltaTime) {}

	@Override
	public void render(Renderer renderer, Camera camera) {
		renderer.getRendIn().setStroke(new BasicStroke(stroke));
		renderer.getRendIn().setColor(fill);
		
		renderer.getRendIn().drawLine(
				(int)(position.getX()), 
				(int)(position.getY()), 
				(int)(position.getX()+ size), 
				(int)(position.getY()+ size));
		renderer.getRendIn().drawLine(
				(int)(position.getX() + size), 
				(int)(position.getY()),
				(int)(position.getX()),
				(int)(position.getY()+ size));
	}

	@Override
	public void close() {}

}
