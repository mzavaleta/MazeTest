package com.games.maps.maze;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Maze {
    public Cell[][] createGrid(int size) {
        Cell[][] grid = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Cell(CellType.WALL, i, j);
            }
        }
        return grid;
    }

    public String printMaze(Cell[][] mazeGrid) {
        StringJoiner joiner = new StringJoiner("");
        for (Cell[] row : mazeGrid) {
            for (Cell cell : row) {
                joiner.add(toMazeChar(cell));
                // joiner.add(cell.toString());
            }
            joiner.add("\n");
        }
        return joiner.toString();
    }

    public String toMazeChar(Cell cell) {
        String ch = "";
        switch (cell.getCellType()) {
            case WALL:
                ch = "X";
                break;
            case PATH:
                ch = " ";
                break;
            case START:
                ch = "S";
                break;
            case FINISH:
                ch = "F";
                break;
            case UP:
                ch = "^";
                break;
            case DOWN:
                ch = "v";
                break;
            case RIGHT:
                ch = ">";
                break;
            case LEFT:
                ch = "<";
                break;
            case OUTBOUND:
                break;

        }
        return ch;
    }

    public Cell[][] createRandomMaze(Cell[][] grid) {
        Random r = new Random();
        Cell cell = getStartCell(grid);

        boolean shouldContinue = true;
        while (shouldContinue) {
            String printMaze = printMaze(grid);
            System.out.println(printMaze);

            Cell rightCell = cell.getRightCell(grid);
            Cell leftCell = cell.getLeftCell(grid);
            Cell topCell = cell.getTopCell(grid);
            Cell downCell = cell.getDownCell(grid);
            List<Cell> neighborCells = Arrays.asList(rightCell, leftCell, topCell, downCell);

            List<Cell> eligibleNextCell = neighborCells.stream().filter(c -> {
                CellType cellType = c.getCellType();
                return cellType.equals(CellType.WALL)
                        && !c.isEdge(grid);
            }).collect(Collectors.toList());

            Cell nextCell;
            if (eligibleNextCell.isEmpty()) {
                nextCell = neighborCells.stream().filter(c -> c.isEdge(grid)).findFirst().orElse(cell);
            } else {
                nextCell = eligibleNextCell.get(r.nextInt(eligibleNextCell.size()));
            }

            if (nextCell.isEdge(grid) || nextCell.equals(cell)) {
                nextCell.setCellType(CellType.FINISH);
                shouldContinue = false;
            } else {
                nextCell.setCellType(CellType.PATH);
            }

            cell = nextCell;
        }


        return grid;
    }

    private Cell getStartCell(Cell[][] grid, long seed) {
        Random r = new Random(seed);
        return getStartCell(grid, r);
    }

    private Cell getStartCell(Cell[][] grid) {
        Random r = new Random();
        return getStartCell(grid, r);
    }

    private Cell getStartCell(Cell[][] grid, Random r) {
        int size = grid.length;
        boolean nextBoolean = r.nextBoolean();
        int nextStartInt = r.nextInt(size);
        int x = nextBoolean ? 0 : nextStartInt;
        int y = nextBoolean ? nextStartInt : 0;
        Cell cell = grid[x][y];
        cell.setCellType(CellType.START);
        return cell;
    }
}
