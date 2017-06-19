package p02_findTheNearestExitFromALabyrinth;

public class Point {
    private int row;

    private int col;

    private String direction;

    private Point prevPoint;

    public Point(int row, int col) {
        this.setRow(row);
        this.setCol(col);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Point getPrevPoint() {
        return prevPoint;
    }

    public void setPrevPoint(Point prevPoint) {
        this.prevPoint = prevPoint;
    }
}
