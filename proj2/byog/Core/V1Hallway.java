package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.lab5.Position;

public class V1Hallway {
    private V1Square hallway;
    private boolean isVertical;

    public V1Hallway(boolean isVertical, int length, Position p) {
        if (isVertical) {
            hallway = new V1Square(length, 3, p);
        } else {
            hallway = new V1Square(3, length, p);
        }
    }

    public void addTo(TETile[][] world) {
        hallway.addTo(world);
    }

}
