import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class BubbleGrid {
    private int[][] gird;
    private UnionFind disjointSet;
    private int columnSize;
    private int rowSize;

    public BubbleGrid(int[][] grid) {
        this.gird = grid;
        this.columnSize = grid.length;
        this.rowSize = gird[grid.length-1].length;
        this.disjointSet = new UnionFind(rowSize * columnSize - rowSize);
        this.disjointSet = setDisjointSet(this.gird, this.disjointSet);
    }

    private UnionFind setDisjointSet(int[][] grid, UnionFind disjointSet) {
        for(int row = 1; row < grid.length; row++) {
            for(int col = 0; col < grid[0].length; col++) {
                //
            }
        }
        return null;
    }

    private void setLowerRows() {

    }

    public int[][] getGird() {
        return gird;
    }

    public int[] popBubbles(int [][] darts) {
        return null;
    }

    private static void print2DIntArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for(int x = 0; x < array[i].length; x++) {
                System.out.print(array[i][x]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        BubbleGrid bubbleGrid = new
                BubbleGrid(new int[][]{{1, 1, 0}, {1, 0, 0}, {1, 1, 0}, {1, 1, 1}});
        print2DIntArray(bubbleGrid.getGird());

        System.out.println();
        System.out.println("Testing");
        System.out.println(Arrays.toString(bubbleGrid.getGird()[1]));
        System.out.println(bubbleGrid.getGird()[1][2]);
        System.out.println("Row Size " + bubbleGrid.rowSize);
        System.out.println("Column Size " + bubbleGrid.columnSize);
    }
}
