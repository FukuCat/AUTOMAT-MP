package engine.drawable;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.MouseEvent;

import jGame.model.game.GameObject;
import jGame.model.graphics.Camera;
import jGame.model.input.Input;
import jGame.model.physics.Physics3D;
import jGame.model.timer.SimpleTimer;
import jGame.view.Renderer;

public class BoardObject extends Physics3D implements GameObject{

	private Color fill;
	private int size;
	private int stroke;
	
	private boolean isSelected[];
	private SimpleTimer leftMouseTimer;
	
	public BoardObject() {
		super();
		fill = Color.BLACK;
		size = 300;
		stroke = 3;
		
		isSelected = new boolean[9];
		reset();
	}
	
	public boolean isSpaceSelected(int x, int y){
		return isSelected[x + y * 3];
	}
	
	@Override
	public void reset() {
		leftMouseTimer = new SimpleTimer(0.25f);
		leftMouseTimer.setResult(true);
		for(int i = 0; i < isSelected.length; i++)
			isSelected[i] = false;
		}
	
	@Override
	public void input(Input input, long deltaTime, Camera camera) {
		for(int x = 0; x < 3; x++)
			for(int y = 0; y < 3; y++){
				if(input.getMouseButton(MouseEvent.BUTTON1) && leftMouseTimer.checkTime()){	
					if(input.getMouseX() > (size / 3) * x + position.getX() + camera.getPosition().getX()
							&& input.getMouseX() < (size / 3) * (x + 1) + position.getX() + camera.getPosition().getX())
					if(input.getMouseY() > (size / 3) * y + position.getY() + camera.getPosition().getY()
							&& input.getMouseY() < (size / 3) * (y + 1) + position.getY() + camera.getPosition().getY())
					if(!isSelected[x + y * 3]){
						isSelected[x + y * 3] = true;
						//System.out.println(input.getMouseY());
						//System.out.println((size / 3) * x + position.getY() + camera.getPosition().getY());
						//System.out.println((size / 3) * (x + 1)  + position.getY() + camera.getPosition().getY());
						//System.out.println("BoardObject: Selected at x = "+x+" y = "+y);
						} 
					} else 
						isSelected[x + y * 3] = false;
				}
	}

	@Override
	public void logic(long deltaTime) {}

	@Override
	public void render(Renderer renderer, Camera camera) {
		renderer.getRendIn().setStroke(new BasicStroke(stroke));
		renderer.getRendIn().setColor(fill);
		// vertical lines
		renderer.getRendIn().drawLine(
				(int)(size / 3 + position.getX() + camera.getPosition().getX()),
				(int)(position.getY() + camera.getPosition().getY()),
				(int)(size / 3 + position.getX() + camera.getPosition().getX()), 
				(int)(position.getY() + size + camera.getPosition().getY()));
		renderer.getRendIn().drawLine(
				(int)(size / 3 * 2 + position.getX() + camera.getPosition().getX()),
				(int)(position.getY() + camera.getPosition().getY()),
				(int)(size / 3 * 2 + position.getX() + camera.getPosition().getX()),
				(int)(position.getY() + size + camera.getPosition().getY()));

	    // horizontal lines
		renderer.getRendIn().drawLine(
				(int)(position.getX() + camera.getPosition().getX()), 
				(int)(size / 3 + position.getY() + camera.getPosition().getY()), 
				(int)(position.getX() + size + camera.getPosition().getX()), 
				(int)(size / 3 + position.getY() + camera.getPosition().getY()));
		renderer.getRendIn().drawLine(
				(int)(position.getX() + camera.getPosition().getX()), 
				(int)(size / 3 * 2 + position.getY() + camera.getPosition().getY()), 
				(int)(position.getX() + size + camera.getPosition().getX()), 
				(int)(size / 3 * 2 + position.getY() + camera.getPosition().getY()));
	}

	@Override
	public void close() {}

}
