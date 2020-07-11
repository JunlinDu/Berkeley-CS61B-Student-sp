package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.lab5.Position;

import java.util.HashMap;

public class V1Square {
    private final int HEIGHT;
    private final int WIDTH;
    private Position p;

    public V1Square (int height, int width, Position p) {
        if((height < 3 && width < 2) || (height < 2 && width < 3)) {
            throw new Error("Square height/width exceed lower bound");
        }
        HEIGHT = height;
        WIDTH = width;
        this.p = p;
    }

    public Position getP() {
        return p;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    /**
     * Draws one entire Square */
    public void addTo(TETile[][] world) {
        for (int i = 0; i < HEIGHT; i++) {
            horizontalAddition(world, p.x, p.y - i, (i == 0 || i == HEIGHT - 1));
        }
    }

    /**
     * Draws one row of the Square. */
    private void horizontalAddition(TETile[][] world, int x, int y, boolean isSide) {
        for(int i = 0; i < WIDTH; i++) {
            if(!isSide && i == 0 || !isSide && i == WIDTH - 1 || isSide) {
                world[x + i][y] = Tileset.WALL;
            } else {
                world[x + i][y] = Tileset.GRASS;
            }
        }
    }

    public void openOnSide(TETile[][] world, Position pos) {
        if(((pos.y == this.p.y || pos.y == this.p.y + HEIGHT - 1) &&
                (pos.x > this.p.x && pos.x < this.p.x + WIDTH - 1)) ||
                ((pos.x == this.p.x || pos.x == this.p.x + WIDTH - 1) &&
                        (pos.y < this.p.y && pos.y > this.p.y - HEIGHT + 1))) {
            world[pos.x][pos.y] = Tileset.GRASS;
        } else {
            throw new Error("Square Must Be Opened On Sides (Except For Corners)");
        }
    }
}