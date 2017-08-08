package engine.scenes;

import jGame.model.external.ExternalAction;
import jGame.model.game.GameScene;
import jGame.model.input.Input;
import jGame.view.Renderer;

public class StartScene extends GameScene{

	public StartScene(String name) {
		super(name);
		// TODO Auto-generated constructor stub
		getExternalActions().put("MACHINE_01", () -> gameSceneManager.changeScene("MACHINE_01"));
		getExternalActions().put("MACHINE_02", () -> gameSceneManager.changeScene("MACHINE_02"));
		getExternalActions().put("MACHINE_07", () -> gameSceneManager.changeScene("MACHINE_07"));
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
		String sAction = getActionQueue().poll();
		ExternalAction a = null;
		if(sAction != null)
			a = getExternalActions().get(sAction);
		if(a != null)
			a.run();
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
