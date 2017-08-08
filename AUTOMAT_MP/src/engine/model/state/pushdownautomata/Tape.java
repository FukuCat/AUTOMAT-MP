package engine.model.state.pushdownautomata;

import engine.drawable.StateObject;
import engine.drawable.TapeObject;

public class Tape {
    private StringBuilder sb;
    private int index;
    private int offset;

    private TapeObject tapeObject;

    public Tape(){
        sb = new StringBuilder();
        setIndex(0);
        setOffset(0);
    }

    public void clear(){ sb.delete(0, sb.length()); }

    public void initialize(String tape){
        if(sb.length() > 0){
        if(tape != null) {
            if(tape.length() > 0) {
                sb.replace(0, sb.length() - 1, tape);
                if (sb.toString().charAt(0) != '#')
                    sb.insert(0, '#');
                if (sb.toString().charAt(sb.length() - 1) != '#')
                    sb.append('#');
            } else
                sb.append("##");
        } else
            sb.replace(0,sb.length() - 1, "##");
        } else{
            if(tape.length() > 0){
                sb.append(tape);
                if (sb.toString().charAt(0) != '#')
                    sb.insert(0, '#');
                if (sb.toString().charAt(sb.length() - 1) != '#')
                    sb.append('#');
            } else
                sb.append("##");
        }
    }

    public void moveRight(){
        if(getOffset() + getIndex() + 1 >= sb.length())
            sb.append('#');
        setIndex(getIndex() + 1);
    }

    public void moveLeft(){
        if(getOffset() + getIndex() - 1 < 0){
            setIndex(getIndex() - 1);
            setOffset(getOffset() + 1);
            sb.insert(getOffset() + getIndex(),'#');
        } else
            setIndex(getIndex() - 1);

    }

    public char read(){
        return sb.charAt(getOffset() + getIndex());
    }

    public void write(String value){
        sb.replace(getOffset() + getIndex(), getOffset() + getIndex() + 1, value);
    }

    public String getString(){
        return sb.toString();

    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public TapeObject getTapeObject() {
        if (tapeObject == null) {
            tapeObject = new TapeObject(this);
        }
        return tapeObject;
    }
}
