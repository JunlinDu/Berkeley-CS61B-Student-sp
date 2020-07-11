package byog.lab5;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.Random;
/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 80;
    private static final int HEIGHT = 60;
    private static final long SEED = 295394;
    private static final Random RANDOM = new Random(SEED);

    /**
     * draw a single hexagon */
    public void addHexagon(int sideLength, Position p, TETile[][] line, TETile tile) {
        if (sideLength < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }
        addHexVertical(sideLength, p.x, p.y, true, line, tile);
        addHexVertical(sideLength, p.x, p.y + sideLength * 2 - 1, false, line, tile);
    }

    /**
     * adding multiple lines of hexagon vertically */
    private void addHexVertical(int sideLength, int x, int y, boolean topDown, TETile[][] line, TETile tile) {
        int numOfTiles = sideLength;
        int factor = topDown ? -1 : 1;
        for (int i = 0; i < sideLength; i++) {
            addHexInLine(numOfTiles, x, y, line, tile);
            numOfTiles += 2;
            x --;
            y -= factor;
        }
    }

    /**
     * add a single line of hexagon based on given position and length */
    private void addHexInLine (int numOfHexTiles, int x, int y, TETile[][] line, TETile tile) {
        for(int i = 0; i < numOfHexTiles; i++) {
            line[x + i][y] = tile;
        }
    }

    /**
     * draw a line of consecutive hexagons vertically */
    private void drawVertical(int numOfHex, int hexSize, Position p, TETile[][] world) {
        TETile tile;
        for (int i = 0; i < numOfHex; i++) {
            tile = randomTile();
            addHexagon(hexSize, p, world, tile);
            p.y -= 2 * hexSize;
        }
    }

    /**
     * returns the starting position of the next hexagon based on the
     * give position */
    private Position nextVerticalLineStartPosition(Position p, int hexSize, int colNum, int terrainSideLength) {
        int x = p.x + hexSize * 2 - 1;
        int y;
        if (colNum >= terrainSideLength) {
            y = p.y - hexSize;
        } else {
            y = p.y + hexSize;
        }
        return new Position(x, y);
    }

    /**
     * returns the number of hexagons to be drawn based on column number */
    private int numOfHex(int colNum, int terrainSideLength) {
        if(colNum > terrainSideLength * 2 - 1) {
            throw new IllegalArgumentException("Out Of Bound");
        }

        if(colNum > terrainSideLength) {
            return terrainSideLength * 3 -1 - colNum;
        }
        return terrainSideLength + colNum - 1;
    }

    /** picks a random tile */
    private TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.WATER;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.MOUNTAIN;
            case 3: return Tileset.TREE;
            case 4: return Tileset.SAND;
            default: return Tileset.GRASS;
        }
    }

    /** draw the entire terrain made of hexagon */
    public void drawTerrain (Position p, int hexSize, int terrainSideLength, TETile[][] world) {
        int num;
        for(int i = 1; i <= terrainSideLength * 2 -1; i++) {
            Position originalPos = new Position(p.x, p.y);
            num = numOfHex(i, terrainSideLength);
            drawVertical(num, hexSize, p, world);
            p = nextVerticalLineStartPosition(originalPos, hexSize, i, terrainSideLength);
        }
    }

    public static void main(String[] args) {
        TERenderer renderer = new TERenderer();
        renderer.initialize(WIDTH, HEIGHT);
        TETile[][] terrain = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                terrain[x][y] = Tileset.NOTHING;
            }
        }

        Position p = new Position(10, 30);
        HexWorld hex = new HexWorld();
        hex.drawTerrain(p, 4, 3, terrain);
        System.out.println(TETile.toString(terrain));
        renderer.renderFrame(terrain);
    }
}
