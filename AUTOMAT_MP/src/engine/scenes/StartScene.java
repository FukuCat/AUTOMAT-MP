package engine.scenes;

import jGame.model.game.GameScene;
import jGame.model.input.Input;
import jGame.view.Renderer;

public class StartScene extends GameScene{

	public StartScene(String name) {
		super(name);
		// TODO Auto-generated constructor stub
		setDoneLoading(true);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void input(Input input, long deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logic(long deltaTime) {
		// TODO Auto-generated method stub
		getGameSceneManager().changeScene("PLAY_GAME");
	}

	@Override
	public void render(Renderer renderer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

}
