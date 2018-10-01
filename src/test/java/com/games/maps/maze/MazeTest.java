package com.games.maps.maze;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MazeTest {

    @Test
    public void createGridTest() {
        Maze maze = new Maze();
        Cell[][] mazeGrid = maze.createGrid(10);

        assertEquals(10, mazeGrid.length);

        for (Cell[] cellRows : mazeGrid) {
            assertEquals(10, cellRows.length);
            for (Cell cell : cellRows) {
                assertTrue(cell.getCellType().equals(CellType.WALL));
            }
        }
    }

    @Test
    public void printMazeTest() {
        Maze maze = new Maze();
        Cell[][] mazeGrid = maze.createGrid(10);
        String printMaze = maze.printMaze(mazeGrid);

        assertEquals(MazeUtils.getFileStringFromResources("maze_init_expected"), printMaze);
    }

    @Test
    public void toMazeCharTet() {
        Maze maze = new Maze();
        String wall = maze.toMazeChar(new Cell(CellType.WALL, 0, 0));
        String path = maze.toMazeChar(new Cell(CellType.PATH, 0, 0));
        String start = maze.toMazeChar(new Cell(CellType.START, 0, 0));
        String finish = maze.toMazeChar(new Cell(CellType.FINISH, 0, 0));
        String up = maze.toMazeChar(new Cell(CellType.UP, 0, 0));
        String down = maze.toMazeChar(new Cell(CellType.DOWN, 0, 0));
        String right = maze.toMazeChar(new Cell(CellType.RIGHT, 0, 0));
        String left = maze.toMazeChar(new Cell(CellType.LEFT, 0, 0));

        assertEquals("X", wall);
        assertEquals(" ", path);
        assertEquals("S", start);
        assertEquals("F", finish);
        assertEquals("^", up);
        assertEquals("v", down);
        assertEquals(">", right);
        assertEquals("<", left);
    }

    @Test
    public void createRandomMazeTest() {
        Maze maze = new Maze();
        Cell[][] grid = maze.createGrid(8);

        Cell[][] mazeGrid = maze.createRandomMaze(grid);

        System.out.println(maze.printMaze(mazeGrid));

    }
}