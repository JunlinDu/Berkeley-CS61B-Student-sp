package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.lab5.Position;

public class V1Room {
    private V1Square room;

    public V1Room (int height, int width, Position p) {
        if(height < 3 || width < 3) {
            throw new Error("Room height/width must be greater than or equal to 3");
        }
        room = new V1Square(height, width, p);
    }

    public Position getP() {
        return room.getP();
    }

    public int getHEIGHT() {
        return room.getHEIGHT();
    }

    public int getWIDTH() {
        return room.getWIDTH();
    }

    public void addTo(TETile[][] world) {
        room.addTo(world);
    }

    public void openHallwayPort(TETile[][] world, Position p) {
        room.openOnSide(world, p);
    }

}
