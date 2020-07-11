package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.lab5.Position;

import java.util.Random;

public class WorldGeneratorV1 {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 40;
    private static final long SEED = 2873163;
    private static final Random RANDOM = new Random(SEED);


    public static void main(String[] args) {
        TERenderer renderer = new TERenderer();
        renderer.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        Position p = new Position(10, 20);
        V1Room room = new V1Room(4, 20, p);
        room.addTo(world);
        room.openHallwayPort(world, new Position(10, 17));
        V1Hallway hallway = new V1Hallway(false, 5, new Position(40, 10));
        hallway.addTo(world);
        System.out.println(TETile.toString(world));
        renderer.renderFrame(world);
    }
}
