package engine;

import engine.scenes.Machine01Scene;
import engine.scenes.StartScene;
import jGame.JGameDriver;

import javax.swing.*;
import java.awt.*;

/**
 * Created by fukon on 6/24/2017.
 */
public class MachineProject extends JGameDriver {

    public MachineProject(String title, int w, int h, int fps){
        super(title, w, h, fps);
        // Load game scenes
        registerScene(new StartScene("START_GAME"));
        registerScene(new Machine01Scene("MACHINE_01"));
        initialScene("MACHINE_01");

    }

    @Override
    public void start(){
        JFrame window = new JFrame(game.getTitle());
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.setSize(new Dimension(game.getWidth(), game.getHeight()));
        JPanel container = new JPanel();
        FlowLayout layout = new FlowLayout();
        layout.setVgap(0);
        layout.setHgap(0);
        container.setLayout(layout);
        container.add(game);

        // center
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - game.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - game.getHeight()) / 2);
        window.setLocation(x, y);

        window.add(container);
        window.pack();
        window.setVisible(true);
    }

    class ControlPanel extends JPanel{

    }
}
