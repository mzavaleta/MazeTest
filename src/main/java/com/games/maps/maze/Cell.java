package com.games.maps.maze;

public class Cell {
    private CellType cellType;
    private final int x;
    private final int y;

    public Cell(CellType cellType, int x, int y) {
        this.cellType = cellType;
        this.x = x;
        this.y = y;
    }

    public CellType getCellType() {
        return cellType;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    Cell getDownCell(Cell[][] grid) {
        if (y == 0) {
            return new Cell(CellType.OUTBOUND, x, -1);
        }
        return grid[x][y - 1];
    }

    Cell getTopCell(Cell[][] grid) {
        if (y == grid.length - 1) {
            return new Cell(CellType.OUTBOUND, x, +1);
        }
        return grid[x][y + 1];
    }

    Cell getLeftCell(Cell[][] grid) {
        if (x == 0) {
            return new Cell(CellType.OUTBOUND, x - 1, y);
        }
        return grid[x - 1][y];
    }

    Cell getRightCell(Cell[][] grid) {
        if (x == grid.length - 1) {
            return new Cell(CellType.OUTBOUND, x + 1, y);
        }
        return grid[x + 1][y];
    }

    public boolean isEdge(Cell[][] grid) {
        int size = grid.length - 1;
        return x == 0 || y == 0 || x == size || y == size;
    }

    public static CellType getDirection(Cell c0, Cell c1) {
        if (c0.getX() == c1.getX() && c0.getY() == c1.getY()) {
            return c0.getCellType();
        } else if (c0.getX() == c1.getX()) {
            return c0.getY() - c1.getY() > 0 ? CellType.LEFT : CellType.RIGHT;
        } else if (c0.getY() == c1.getY()) {
            return c0.getX() - c1.getX() > 0 ? CellType.UP : CellType.DOWN;
        }

        return c0.getCellType();
    }

    @Override
    public String toString() {
        return String.format("[%s][%s,%s]", cellType, x, y);
    }

}
