package engine.drawable;

import jGame.model.game.GameObject;
import jGame.model.graphics.Camera;
import jGame.model.input.Input;
import jGame.model.math.Vector2f;
import jGame.model.physics.Physics2D;
import jGame.view.Renderer;

import java.awt.*;

/**
 * Created by fukon on 7/12/2017.
 */
public class StateObject extends Physics2D implements GameObject {

    private static final Color ACTIVE = Color.GREEN;
    private static final Color INACTIVE = Color.BLACK;
    private static final int DIAMETER = 50;

    private Vector2f initialPos;
    private Color fill;
    private int size;
    private int stroke;
    private String name;

    private boolean isFinal;

    public StateObject(String name, int x, int y){
        super();
        size = DIAMETER;
        setInitialPos(new Vector2f(x,y));
        setName(name);
        reset();
    }

    public StateObject(String name){
        super();
        size = DIAMETER;
        setInitialPos(new Vector2f());
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
        fill = INACTIVE;
        stroke = 2;
        position.setValue(getInitialPos().getX(), getInitialPos().getY());
    }

    @Override
    public void input(Input input, long deltaTime, Camera camera) {

    }

    @Override
    public void logic(long deltaTime) {

    }

    @Override
    public void render(Renderer renderer, Camera camera) {
        renderer.getRendIn().setStroke(new BasicStroke(stroke));
        renderer.getRendIn().setColor(fill);
        renderer.getRendIn().drawOval(
                (int)(position.getX() + camera.getPosition().getX()),
                (int)(position.getY() + camera.getPosition().getY()),
                size, size);

        // final
        if(isFinal)
            renderer.getRendIn().drawOval(
                    (int)(position.getX() + camera.getPosition().getX() + 4),
                    (int)(position.getY() + camera.getPosition().getY() + 4),
                    size - 8, size - 8);

        renderer.getRendIn().drawString(
                name,
                (int)(position.getX() + camera.getPosition().getX() - 3) + size / 2,
                (int)(position.getY() + camera.getPosition().getY() + 2) + size / 2);
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

    public Vector2f getInitialPos() {
        return initialPos;
    }

    public void setInitialPos(Vector2f initialPos) {
        this.initialPos = initialPos;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }
}
