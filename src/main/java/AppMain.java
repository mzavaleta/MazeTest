import com.games.maps.maze.Cell;
import com.games.maps.maze.Maze;

/**
 * Main Class
 */
public class AppMain {

    public static void main(String[] args) {
        System.out.println("Hello Maze");

        Maze maze = new Maze();
        Cell[][] grid = maze.createGrid(16);

        Cell[][] mazeGrid = maze.createRandomMaze(grid);

        System.out.println(maze.printMaze(mazeGrid));
    }
}
