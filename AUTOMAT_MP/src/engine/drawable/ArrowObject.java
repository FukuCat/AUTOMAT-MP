package engine.drawable;

import jGame.model.game.GameObject;
import jGame.model.graphics.Camera;
import jGame.model.input.Input;
import jGame.model.math.Vector2f;
import jGame.model.physics.Physics2D;
import jGame.view.Renderer;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

public class ArrowObject extends Physics2D implements GameObject {

    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final int SELF_UP = 4;
    public static final int SELF_DOWN = 5;
    public static final int SELF_LEFT = 6;
    public static final int SELF_RIGHT = 7;

    private Vector2f initialPos;
    private Color fill;
    private int stroke;
    private String name;

    private AffineTransform tx;
    private Line2D.Double line;
    private Line2D.Double line2;
    private Line2D.Double line3;
    private Polygon arrowHead;

    private int type;

    public ArrowObject(String name, int x, int y, int type){
        super();
        setInitialPos(new Vector2f(x,y));
        setName(name);
        this.type = type;
        reset();
    }

    @Override
    public void reset() {
        setPosition(initialPos.getX(), initialPos.getY());
        tx = new AffineTransform();
        switch (type){
            case SELF_UP:
                line = new Line2D.Double(position.getX() + 25 + 5,
                        position.getY() + 25,
                        position.getX() + 25 + 5,
                        position.getY() + 0);
                line2 = new Line2D.Double(position.getX() + 25 - 5,
                        position.getY() + 25,
                        position.getX() + 25 - 5,
                        position.getY() + 0);
                line3 = new Line2D.Double(position.getX() + 25 + 5,
                        position.getY() + 25,
                        position.getX() + 25 - 5,
                        position.getY() + 25);
                break;
            case UP:
                line = new Line2D.Double(position.getX() + 25,
                        position.getY() + 50,
                        position.getX() + 25,
                        position.getY() + 0);
                break;
            case SELF_DOWN:
                line = new Line2D.Double(position.getX() + 25 + 5,
                        position.getY() + 25,
                        position.getX() + 25 + 5,
                        position.getY() + 50);
                line2 = new Line2D.Double(position.getX() + 25 - 5,
                        position.getY() + 25,
                        position.getX() + 25 - 5,
                        position.getY() + 50);
                line3 = new Line2D.Double(position.getX() + 25 + 5,
                        position.getY() + 25,
                        position.getX() + 25 - 5,
                        position.getY() + 25);
                break;
            case DOWN:
                line = new Line2D.Double(position.getX() + 25,
                        position.getY() + 0,
                        position.getX() + 25,
                        position.getY() + 50);
                break;
            case SELF_LEFT:
                line = new Line2D.Double(position.getX() + 25,
                        position.getY() + 25 + 5,
                        position.getX() + 0,
                        position.getY() + 25 + 5);
                line2 = new Line2D.Double(position.getX() + 0,
                        position.getY() + 25 - 5,
                        position.getX() + 25,
                        position.getY() + 25 - 5);

                line3 = new Line2D.Double(position.getX() + 25,
                        position.getY() + 25 + 5,
                        position.getX() + 25,
                        position.getY() + 25 - 5);
                break;
            case LEFT:
                line = new Line2D.Double(position.getX() + 50,
                        position.getY() + 25,
                        position.getX() + 0,
                        position.getY() + 25);
                break;
            case SELF_RIGHT:
                line = new Line2D.Double(position.getX() + 25,
                        position.getY() + 25 + 5,
                        position.getX() + 50,
                        position.getY() + 25 + 5);
                line2 = new Line2D.Double(position.getX() + 50,
                        position.getY() + 25 - 5,
                        position.getX() + 25,
                        position.getY() + 25 - 5);
                line3 = new Line2D.Double(position.getX() + 25,
                        position.getY() + 25 + 5,
                        position.getX() + 25,
                        position.getY() + 25 - 5);
                break;
            case RIGHT:
                line = new Line2D.Double(position.getX() + 0,
                        position.getY() + 25,
                        position.getX() + 50,
                        position.getY() + 25);
                break;
            default:
            line = new Line2D.Double(
                    0,
                    0,
                    50,
                    50);
        }

        arrowHead = new Polygon();
        arrowHead.addPoint( 0,0);
        arrowHead.addPoint( -5, -5);
        arrowHead.addPoint( 5,-5);
    }

    @Override
    public void input(Input input, long deltaTime, Camera camera) {

    }

    @Override
    public void logic(long deltaTime) {

    }

    private void drawArrowHead(Graphics2D g2d) {
        tx.setToIdentity();
        double angle = Math.atan2(line.y2-line.y1, line.x2-line.x1);
        tx.translate(line.x2, line.y2);
        tx.rotate((angle-Math.PI/2d));

        Graphics2D g = (Graphics2D) g2d.create();
        g.setTransform(tx);
        g.fill(arrowHead);
        g.dispose();
    }

    @Override
    public void render(Renderer renderer, Camera camera) {
        renderer.getRendIn().setColor(Color.black);
        drawArrowHead(renderer.getRendIn());
        renderer.getRendIn().draw(line);
        if(line2 != null)
            renderer.getRendIn().draw(line2);
        if(line3 != null)
            renderer.getRendIn().draw(line3);
        int stringX = (int)(position.getX() + camera.getPosition().getX() - 3) + 50 / 2;
        int stringY = (int)(position.getY() + camera.getPosition().getY() + 2) + 50 / 2;
        switch (type){
            case SELF_UP:
                stringY += 12;
                stringX -= 5;
                break;
            case UP:

                break;
            case SELF_DOWN:
                stringY -= 8;
                stringX -= 5;
                break;
            case DOWN:

                break;
            case SELF_LEFT:

                break;
            case LEFT:

                break;
            case SELF_RIGHT:

                break;
            case RIGHT:

                break;
        }
        renderer.getRendIn().drawString(
                name, stringX, stringY);

    }

    @Override
    public void close() {

    }

    public Vector2f getInitialPos() {
        return initialPos;
    }

    public void setInitialPos(Vector2f initialPos) {
        this.initialPos = initialPos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
