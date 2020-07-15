import java.util.Random;

public class BubbleGrid {
    private int[][] gird;
    private UnionFind disjointSet;
    private int columnSize;
    /**
     * [] [] [] [] [] []
     * -- -- -- -- -- --
     * -- -- -- -- -- --
     * -- -- -- -- -- --
     * -- -- -- -- -- -- */
    public BubbleGrid() {
        gird = new int[][]
                {{1, 1, 1, 1}, {1, 0, 1, 1}, {0, 0, 0, 1}};
        this.columnSize = gird.length;
    }

    public int[][] getGird() {
        return gird;
    }

    public int[] popBubbles(int [][] darts) {
        return null;
    }

    private static void print2DIntArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int x = 0; x < array[x].length; x++) {
                System.out.print(array[x][i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        BubbleGrid bubbleGrid = new BubbleGrid();
        print2DIntArray(bubbleGrid.getGird());
    }
}
