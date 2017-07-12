package engine.scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import engine.Logic;
import engine.drawable.BoardObject;
import engine.drawable.NumberObject;
import jGame.model.game.GameScene;
import jGame.model.graphics.Camera;
import jGame.model.input.Input;
import jGame.model.math.Vector2i;
import jGame.model.timer.SimpleTimer;
import jGame.view.Renderer;

public class PlayScene extends GameScene{

	private Camera camera;
	private Logic logic;
	private SimpleTimer rKeyTimer;
	
	private BoardObject board;
	private NumberObject[] pieces;
	
	private int move;
	private int match;
	
	private Font font;
	
	public PlayScene(String name) {
		super(name);
		board = new BoardObject();
		pieces = new NumberObject[9];
		camera = new Camera();
		logic = new Logic();
		setDoneLoading(true);
		rKeyTimer = new SimpleTimer(0.25f);
		rKeyTimer.setResult(true);
	}

	@Override
	public void init() {
		move = 1;
		match = -1;
		camera.reset();
		board.reset();
		/*
		for(NumberObject g: pieces)
			if(g != null){
				g.close();
				g = null;
			}
			*/
		for(int i = 0; i < pieces.length; i++)
			if(pieces[i] != null){
				pieces[i].close();
				pieces[i] = null;
			}
		logic.reset();
		font = new Font("default", Font.PLAIN, 12);
	}

	@Override
	public void input(Input input, long deltaTime) {
		board.input(input, deltaTime, camera);
		if(rKeyTimer.checkTime() && input.getKeyboardKey(KeyEvent.VK_R)){
			init();
		}
		
	}

	@Override
	public void logic(long deltaTime) {
		for(int x = 0; x < 3; x++)
			for(int y = 0; y < 3; y++)
				if(board.isSpaceSelected(x, y) && pieces[x + y * 3] == null && !logic.getOver()){
					logic.nextPlayerMove(new Vector2i(x + 1, y + 1), move);
					match = logic.endGame(logic.getOver());
					
					pieces[x + y * 3] = move % 2 == 0? new NumberObject(x, y, Color.ORANGE, move): new NumberObject(x, y, Color.BLUE, move);
					move++;
					move %= 10;
					//logic.printStatus();
					
					if(match == Logic.FIRST || match == Logic.SECOND){
						for(int i = 1; i <= 4; i++)
							if(logic.isWinning(i))
								setWinningSpaceToColor(i, Color.GREEN);
						}
					if(logic.getOver())
						move--;
					//System.out.println(match);
				}
	}
	
	public void setWinningSpaceToColor(int type, Color color){
		switch(type){
		case 1:
			pieces[0 + 0 * 3].setColor(color);
			pieces[0 + 1 * 3].setColor(color);
			pieces[0 + 2 * 3].setColor(color);
			break;
		case 2:
			pieces[2 + 0 * 3].setColor(color);
			pieces[2 + 1 * 3].setColor(color);
			pieces[2 + 2 * 3].setColor(color);
			break;
		case 3:
			pieces[0 + 0 * 3].setColor(color);
			pieces[1 + 1 * 3].setColor(color);
			pieces[2 + 2 * 3].setColor(color);
			break;
		case 4:
			pieces[2 + 0 * 3].setColor(color);
			pieces[1 + 1 * 3].setColor(color);
			pieces[0 + 2 * 3].setColor(color);
			break;
		}
	}

	@Override
	public void render(Renderer renderer) {
		for(NumberObject g: pieces)
			if(g != null)
				g.render(renderer, camera);
		board.render(renderer, camera);

		renderer.getRendIn().setColor(Color.BLACK);
		renderer.getRendIn().setFont(font);
		if(!logic.getOver())
			renderer.getRendIn().drawString("Turn: "+ (move % 2 == 0? "Orange":"Blue"), 
					camera.getPosition().getX() + 8,
					camera.getPosition().getY() + 400 - 82);
		else if(match == Logic.FIRST || match == Logic.SECOND)
			renderer.getRendIn().drawString("Winner is "+ (move % 2 == 0? "Orange!":"Blue!"), 
				camera.getPosition().getX() + 8,
				camera.getPosition().getY() + 400 - 82);
		else
			renderer.getRendIn().drawString("Game is drawn!", 
				camera.getPosition().getX() + 8,
				camera.getPosition().getY() + 400 - 82);
		
		renderer.getRendIn().drawString("Rules first player to get a total of 15 in the first or last", 
				camera.getPosition().getX() + 8,
				camera.getPosition().getY() + 400 - 42);
		renderer.getRendIn().drawString("    column or any diagonal wins!", 
				camera.getPosition().getX() + 8,
				camera.getPosition().getY() + 400 - 26);
	}

	@Override
	public void close() {
		board.close();
		for(NumberObject g: pieces)
			if(g != null)
				g.close();
		
	}

}
