package p08_distanceInLabyrinth;

public class Cell {
    private int row;

    private int col;

    private int value;

    //--------------------------------------------------------
    public Cell(int row, int col, int value) {
        this.setRow(row);
        this.setCol(col);
        this.setValue(value);
    }

    public int getRow() {
        return row;
    }

    private void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    private void setCol(int col) {
        this.col = col;
    }

    public int getValue() {
        return value;
    }

    private void setValue(int value) {
        this.value = value;
    }
    //--------------------------------------------------------


}
