package p02_quadTree;

public class Rectangle {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int height;
    private int width;

    public Rectangle(int x1, int y1, int x2, int y2) {
        this.setX1(x1);
        this.setY1(y1);
        this.setX2(x2);
        this.setY2(y2);

        this.setWidth(x2 - x1);
        this.setHeight(y2 - y1);
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
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isInside(Rectangle other) {
        return this.getX1() < other.getX1() &&
                this.getY1() < other.getY1() &&
                this.getX2() > other.getX2() &&
                this.getY2() > other.getY2();
    }
}