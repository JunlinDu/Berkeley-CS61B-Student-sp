import java.util.Random;

public class BubbleGrid {
    private int[][] gird;
    private UnionFind disjointSet;
    private int columnSize;
    private static final long SEED = 2873163;
    private static final Random RANDOM = new Random(SEED);
    /**
     * [] [] [] [] [] []
     * -- -- -- -- -- --
     * -- -- -- -- -- --
     * -- -- -- -- -- --
     * -- -- -- -- -- -- */
    public BubbleGrid(int rowSize, int columnSize) {
        gird = new int[rowSize][columnSize];
        for (int i = 0; i < gird.length; i++) {
            for (int x = 0; x < gird[i].length; x++) {
                gird[x][i] = RANDOM.nextInt(2);
            }
        }
        this.columnSize = columnSize;

    }

    public int[] popBubbles(int [][] darts) {
        return null;
    }

    public static void main(String[] args) {
        //
    }
}
