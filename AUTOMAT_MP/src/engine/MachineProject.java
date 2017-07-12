package engine;

import engine.scenes.StartScene;
import jGame.JGameDriver;
/**
 * Created by fukon on 6/24/2017.
 */
public class MachineProject extends JGameDriver {

    public MachineProject(String title, int w, int h, int fps){
        super(title, w, h, fps);
        // Load game scenes
        registerScene(new StartScene("START_GAME"));
        initialScene("START_GAME");

    }
}
