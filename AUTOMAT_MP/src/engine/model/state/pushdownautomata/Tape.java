package engine.model.state.pushdownautomata;

public class Tape {
    private StringBuilder sb;
    private int index;
    private int offset;

    public Tape(){
        sb = new StringBuilder();
        sb.append('#');
        setIndex(0);
        setOffset(0);
    }

    public void initialize(String tape){
        sb.replace(0,sb.length() - 1, tape);
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
        if(sb.toString().charAt(0) != '#'){
            setOffset(getOffset() + 1);
            sb.insert(0,'#');
        }

        if(sb.toString().charAt(sb.length() - 1) != '#')
            sb.append('#');

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
}
