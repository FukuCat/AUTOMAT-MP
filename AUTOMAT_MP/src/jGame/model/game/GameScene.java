package jGame.model.game;

import java.util.ArrayList;
import java.util.HashMap;

import jGame.model.input.Input;
import jGame.model.timer.SimpleTimer;
import jGame.view.Renderer;

public abstract class GameScene {

	protected String name;
	protected ArrayList<GameObject> actors;
	protected GameSceneManager gameSceneManager;
	protected boolean isDoneLoading;
	protected boolean isSceneEnded;
	private HashMap<Integer, SimpleTimer> inputTimers;

	public GameScene(String name){
		setActors(new ArrayList<>());
		setName(name);
		setDoneLoading(true);
		setSceneEnded(true);
		setInputTimers(new HashMap<>());
	}
	public GameScene(String name, boolean isDoneLoading){
	    setInputTimers(new HashMap<>());
		setActors(new ArrayList<>());
		setName(name);
		setDoneLoading(isDoneLoading);
		setSceneEnded(true);
	}
	// call loader and init vars -> set doneLoading to true
	public abstract void init();
	
	public abstract void input(Input input, long deltaTime);
	public abstract void logic(long deltaTime);
	public abstract void render(Renderer renderer);

	public abstract void close();
	
	public ArrayList<GameObject> getActors() {
		return actors;
	}

	public void setActors(ArrayList<GameObject> actors) {
		this.actors = actors;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public boolean isDoneLoading() {
		return isDoneLoading;
	}

	public void setDoneLoading(boolean isDoneLoading) {
		this.isDoneLoading = isDoneLoading;
	}

	public boolean isSceneEnded() {
		return isSceneEnded;
	}

	public void setSceneEnded(boolean isSceneEnded) {
		this.isSceneEnded = isSceneEnded;
	}
	public GameSceneManager getGameSceneManager() {
		return gameSceneManager;
	}
	public void setGameSceneManager(GameSceneManager gameSceneManager) {
		this.gameSceneManager = gameSceneManager;
	}

    public HashMap<Integer, SimpleTimer> getInputTimers() {
        return inputTimers;
    }

    public void setInputTimers(HashMap<Integer, SimpleTimer> inputTimers) {
        this.inputTimers = inputTimers;
    }
}
