package engine;

import engine.model.Logic;
import engine.scenes.Machine01Scene;
import engine.scenes.Machine02Scene;
import engine.scenes.Machine07Scene;
import engine.scenes.StartScene;
import jGame.JGameDriver;
import jGame.model.game.GameScene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by fukon on 6/24/2017.
 */
public class MachineProject extends JGameDriver {

    private ControlPanel cp;
    private GameScene sStart, sM01, sM02, sM07;

    public MachineProject(String title, int w, int h, int fps){
        super(title, w - 180, h, fps);
        // Load game scenes
        sStart = new StartScene("START_GAME");
        sM01 = new Machine01Scene("MACHINE_01");
        sM02 = new Machine02Scene("MACHINE_02");
        sM07 = new Machine07Scene("MACHINE_07");
        cp = new ControlPanel(180, h, sStart, sM01, sM02, sM07);
        registerScene(sStart);
        registerScene(sM01);
        registerScene(sM02);
        registerScene(sM07);
        initialScene("START_GAME");

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
        container.add(cp);
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

    class ControlPanel extends JPanel implements ActionListener {
        public static final int BUTTON_HEIGHT = 40;
        public static final int PANEL_SPACING_X = 20;
        public static final int PANEL_SPACING_Y = 5;

        private static final long serialVersionUID = 1L;

        private int panelWidth, panelHeight;
        private JLabel lbl_select;
        private JLabel lbl_input;
        private JButton btn_machine01;
        private JButton btn_machine02;
        private JButton btn_machine07;
        private JButton btn_enter;
        private JButton btn_next;
        private JButton btn_play;
        private JButton btn_pause;
        private JTextField txt_input;

        private int width, height;

        private Logic logic;
        private GameScene sStart, sM01, sM02, sM07;
        private GameScene currentScene;

        private boolean isLoaded;
        private boolean isRunning;

        public ControlPanel(int width, int height, GameScene sStart, GameScene sM01, GameScene sM02, GameScene sM07) {
            this.sStart = sStart;
            this.sM01 = sM01;
            this.sM02 = sM02;
            this.sM07 = sM07;
            this.width = width;
            this.height = height;
            setPreferredSize(new Dimension(width, height));
            setPanelHeight(height);
            setPanelWidth(width);
            isLoaded = false;
            isRunning = false;
            currentScene = sStart;
            init();
        }

        public void init() {
            this.setAlignmentX(LEFT_ALIGNMENT);
            this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            lbl_select = new JLabel("Select a machine");
            lbl_input = new JLabel("Input");

            btn_machine01 = new JButton("Load Machine 1");
            btn_machine02 = new JButton("Load Machine 2");
            btn_machine07 = new JButton("Load Machine 7");
            btn_enter = new JButton("Submit");
            btn_next = new JButton("Next State");
            btn_play = new JButton("Play");
            btn_pause = new JButton("Pause");

            txt_input = new JTextField();


            btn_machine01.setMaximumSize(new Dimension(width, 30));
            btn_machine02.setMaximumSize(new Dimension(width, 30));
            btn_machine07.setMaximumSize(new Dimension(width, 30));
            btn_enter.setMaximumSize(new Dimension(width, 30));
            txt_input.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
            btn_play.setMaximumSize(new Dimension(width, 30));
            btn_pause.setMaximumSize(new Dimension(width, 30));
            btn_next.setMaximumSize(new Dimension(width, 30));

            btn_machine01.addActionListener(this);
            btn_machine02.addActionListener(this);
            btn_machine07.addActionListener(this);
            btn_enter.addActionListener(this);
            btn_play.addActionListener(this);
            btn_pause.addActionListener(this);
            btn_next.addActionListener(this);

            this.add(lbl_select);
            this.add(Box.createRigidArea(new Dimension(0, 5)));
            this.add(btn_machine01);
            this.add(btn_machine02);
            this.add(btn_machine07);
            this.add(Box.createRigidArea(new Dimension(0, 5)));
            this.add(lbl_input);
            this.add(txt_input);
            this.add(btn_enter);
            this.add(Box.createRigidArea(new Dimension(0, 5)));
            this.add(btn_play);
            this.add(btn_pause);
            this.add(btn_next);

            draw();
        }

        public void draw() {

                if (!isLoaded) {
                    txt_input.setEnabled(false);
                    txt_input.setBackground(Color.LIGHT_GRAY);
                    btn_enter.setEnabled(false);
                    btn_play.setEnabled(false);
                    btn_pause.setEnabled(false);
                    btn_next.setEnabled(false);
                } else {
                    txt_input.setEnabled(true);
                    txt_input.setBackground(Color.WHITE);
                    btn_enter.setEnabled(true);
                    btn_play.setEnabled(true);
                    btn_pause.setEnabled(true);
                    btn_next.setEnabled(true);
                }

                if(txt_input.getText().length() <= 0) {
                    btn_play.setEnabled(false);
                    btn_pause.setEnabled(false);
                    btn_next.setEnabled(false);
                } else{
                    if (isRunning) {
                    btn_next.setEnabled(false);
                    btn_play.setEnabled(false);
                    btn_pause.setEnabled(true);
                } else {
                    btn_next.setEnabled(true);
                    btn_play.setEnabled(true);
                    btn_pause.setEnabled(false);
                }
                }



            this.validate();
            this.repaint();
        }


        public int getPanelWidth() {
            return panelWidth;
        }

        public void setPanelWidth(int panelWidth) {
            this.panelWidth = panelWidth;
        }

        public int getPanelHeight() {
            return panelHeight;
        }

        public void setPanelHeight(int panelHeight) {
            this.panelHeight = panelHeight;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == btn_machine01) {
                txt_input.setText("");
                Logic.getInstance().setInput("");
                currentScene.getActionQueue().add("MACHINE_01");
                currentScene = sM01;
                isLoaded = true;
                draw();
            }
            if (e.getSource() == btn_machine02) {
                txt_input.setText("");
                Logic.getInstance().setInput("");
                currentScene.getActionQueue().add("MACHINE_02");
                currentScene = sM02;
                isLoaded = true;
                draw();
            }
            if (e.getSource() == btn_machine07) {
                txt_input.setText("");
                Logic.getInstance().setInput("");
                currentScene.getActionQueue().add("MACHINE_07");
                currentScene = sM07;
                isLoaded = true;
                draw();
            }
            if (e.getSource() == btn_enter) {
                String input = txt_input.getText();
                if (input.length() > 0) {
                    isRunning = false;
                    Logic.getInstance().setInput(input);
                    currentScene.getActionQueue().add("RESET");
                    draw();
                }
            }
            if (e.getSource() == btn_play) {
                String input = txt_input.getText();
                if (input.length() > 0) {
                    isRunning = true;
                    Logic.getInstance().setInput(input);
                    currentScene.getActionQueue().add("PLAY");
                    draw();
                }
            }
            if (e.getSource() == btn_pause) {
                isRunning = false;
                currentScene.getActionQueue().add("PAUSE");
                draw();
            }
            if (e.getSource() == btn_next) {
                String input = txt_input.getText();
                if (input.length() > 0) {
                    Logic.getInstance().setInput(input);
                    currentScene.getActionQueue().add("NEXT");
                }
            }

        }
    }
}
