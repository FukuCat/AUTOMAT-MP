package engine.drawable;

import engine.model.state.pushdownautomata.Stack;
import jGame.model.game.GameObject;
import jGame.model.graphics.Camera;
import jGame.model.input.Input;
import jGame.model.math.Vector2f;
import jGame.model.physics.Physics2D;
import jGame.view.Renderer;

import java.awt.*;

public class StackObject extends Physics2D implements GameObject {

    private Stack stack;

    private Color fill = Color.black;
    private Font font;

    public StackObject(Stack stack){
        super();
        this.stack = stack;
        font = new Font("Courier New", Font.PLAIN, 12);
    }

    @Override
    public void reset() {

    }

    @Override
    public void input(Input input, long deltaTime, Camera camera) {

    }

    @Override
    public void logic(long deltaTime) {

    }

    @Override
    public void render(Renderer renderer, Camera camera) {
        String text = "Stack: "+stack.getString();
        renderer.getRendIn().setColor(fill);
        renderer.getRendIn().setFont(font);
        renderer.getRendIn().drawString(text, position.getX() + camera.getPosition().getX(),
                position.getY() + camera.getPosition().getY());
    }

    @Override
    public void close() {

    }
}
