package engine.drawable;

import engine.model.state.pushdownautomata.Tape;
import jGame.model.game.GameObject;
import jGame.model.graphics.Camera;
import jGame.model.input.Input;
import jGame.model.physics.Physics2D;
import jGame.view.Renderer;

import java.awt.*;

public class TapeObject extends Physics2D implements GameObject {

    private Tape tape;

    private Color fill = Color.black;
    private Font font;

    private StringBuilder sb;

    public TapeObject(Tape tape){
        super();
        this.tape = tape;
        font = new Font("Courier New", Font.PLAIN, 12);
        sb = new StringBuilder();
        reset();
    }

    @Override
    public void reset() {
        sb.delete(0, sb.length());
    }

    @Override
    public void input(Input input, long deltaTime, Camera camera) {

    }

    @Override
    public void logic(long deltaTime) {

    }

    @Override
    public void render(Renderer renderer, Camera camera) {
        String text = "Tape:  "+tape.getString();
        renderer.getRendIn().setColor(fill);
        renderer.getRendIn().setFont(font);
        renderer.getRendIn().drawString(text, position.getX() + camera.getPosition().getX(),
                position.getY() + camera.getPosition().getY());

        sb.append("       ");
        for(int i = 0; i < (tape.getOffset() + tape.getIndex()); i++)
           sb.append(' ');
        sb.append('^');
        renderer.getRendIn().drawString(sb.toString(), position.getX() + camera.getPosition().getX(),
                position.getY() + camera.getPosition().getY() + 12);
        sb.delete(0, sb.length());
    }

    @Override
    public void close() {

    }
}
