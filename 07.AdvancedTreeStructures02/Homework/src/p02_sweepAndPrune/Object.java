package p02_sweepAndPrune;

public class Object {
    private String name;

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    //----------------------------------------------------------
    public Object(String name, int x1, int y1) {
        this.setName(name);
        this.setX1(x1);
        this.setY1(y1);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return this.getX1() + 10;
    }

    public int getY2() {
        return this.getY1() + 10;
    }
    //-------------------------------------------------------

    @Override
    public String toString() {
        return getName() + " " + getX1() + " " + getY1();
    }
}
