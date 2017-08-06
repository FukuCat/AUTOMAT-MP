package engine.drawable;

import jGame.model.game.GameObject;
import jGame.model.graphics.Camera;
import jGame.model.input.Input;
import jGame.model.math.Vector2f;
import jGame.model.physics.Physics2D;
import jGame.view.Renderer;

import java.awt.*;

public class StackObject extends Physics2D implements GameObject {
    private static final Color ACTIVE = Color.GREEN;
    private static final Color INACTIVE = Color.BLACK;
    private static final int DIAMETER = 50;

    private StringBuilder sb;

    private Vector2f initialPos;
    private Color fill;
    private int size;
    private int stroke;
    private String name;

    public StackObject(float x, float y, String name){
        super();
        size = DIAMETER;
        initialPos = new Vector2f(x,y);
        setName(name);
        reset();
    }

    public void isActive(boolean value){
        if(value)
            fill = ACTIVE;
        else
            fill = INACTIVE;
    }

    @Override
    public void reset() {
        sb = new StringBuilder();
        fill = INACTIVE;
        stroke = 2;
        position.setValue(initialPos.getX() - size / 2, initialPos.getY() - size / 2);
    }

    @Override
    public void input(Input input, long deltaTime, Camera camera) {

    }

    @Override
    public void logic(long deltaTime) {

    }

    @Override
    public void render(Renderer renderer, Camera camera) {

    }

    @Override
    public void close() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
