package engine.drawable;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

import jGame.model.game.GameObject;
import jGame.model.graphics.Camera;
import jGame.model.input.Input;
import jGame.model.physics.Physics3D;
import jGame.view.Renderer;

public class NumberObject extends Physics3D implements GameObject{

	private Color fill;
	private int size;
	private FontMetrics metrics = null;
	private Font font;
	private String text;
	private int number;

	public NumberObject(int x, int y, Color color, int number){
		super();
		fill = color;
		size = 100;
		font = new Font("default", Font.BOLD, 40);
		position.setValue(x * size, y * size, 0.0f);
		text = ""+number;
		this.number = number;
	}

	@Override
	public void reset() {}
	
	@Override
	public void input(Input input, long deltaTime, Camera camera) {}

	@Override
	public void logic(long deltaTime) {}

	@Override
	public void render(Renderer renderer, Camera camera) {
		if(metrics == null)
			metrics = renderer.getRendIn().getFontMetrics(font);
		renderer.getRendIn().setColor(fill);
		int x = (size - metrics.stringWidth(text)) / 2;
		int y = ((size - metrics.getHeight()) / 2) + metrics.getAscent();
		renderer.getRendIn().setFont(font);
		renderer.getRendIn().drawString(text, x + position.getX() + camera.getPosition().getX(), 
				y + position.getY() + camera.getPosition().getY());
	}

	@Override
	public void close() {}
	
	public void setColor(Color color){ fill = color; }
	
	public int getNumber(){ return number; }
}
