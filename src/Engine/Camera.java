package Engine;

import java.awt.Point;

/**
 * @filename Camera.java
 * @author Shane Kelly
 * @date 09 Jan 2013
 */

public class Camera {
// -----------------------------------------------------------------------------
// Fields
// -----------------------------------------------------------------------------

    private Point topLeft;
    private Level level;
    private Tile[][] view;
    private Lootable[][] lootables;

// -----------------------------------------------------------------------------
// Getters $ Setters
// -----------------------------------------------------------------------------

     public Point getTopLeft() {
        return topLeft;
    }

     public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }

     public Level getMap() {
        return this.level;
     }

// -----------------------------------------------------------------------------
// Constructors
// -----------------------------------------------------------------------------

    public Camera(Point _topLeft, Level _map) {
        this.level = _map;
        this.topLeft = _topLeft;

    }

// -----------------------------------------------------------------------------
// Methods
// -----------------------------------------------------------------------------

    public Tile[][] getView() {
        view = new Tile[22][17];
        Boolean outOfBoundsX;
        Boolean outOfBoundsY;
        int topLeftX;
        int topLeftY;
        int mapSizeX;
        int mapSizeY;

        for (int x = 0; x < 22; x++) {
            for (int y = 0; y < 17; y++) {

                topLeftX = this.getTopLeft().x - 1;
                topLeftY = this.getTopLeft().y - 1;
                mapSizeX = level.getSize().x - 1;
                mapSizeY = level.getSize().y - 1;

                outOfBoundsX = (x + topLeftX < 0) || (x + topLeftX > mapSizeX);
                outOfBoundsY = (y + topLeftY < 0) || (y + topLeftY > mapSizeY);
                
                if( outOfBoundsX || outOfBoundsY ) {
                    view[x][y] = new Tile("black", false);
                }
                else {
                    view[x][y] = level.getLevel()[x + topLeftX - 1][y + topLeftY - 1];
                }
            }
        }

        return view;
    }

    public Lootable[][] getLootables() {
        lootables = new Lootable[22][17];
        Boolean outOfBoundsX;
        Boolean outOfBoundsY;
        int topLeftX;
        int topLeftY;
        int mapSizeX;
        int mapSizeY;

        for (int x = 0; x < 22; x++) {
            for (int y = 0; y < 17; y++) {

                topLeftX = this.getTopLeft().x - 1;
                topLeftY = this.getTopLeft().y - 1;
                mapSizeX = level.getSize().x - 1;
                mapSizeY = level.getSize().y - 1;

                outOfBoundsX = (x + topLeftX < 0) || (x + topLeftX > mapSizeX);
                outOfBoundsY = (y + topLeftY < 0) || (y + topLeftY > mapSizeY);

                if( outOfBoundsX || outOfBoundsY ) {
                    lootables[x][y] = null;
                }
                else {
                    lootables[x][y] = level.getObjects()[x + topLeftX - 1][y + topLeftY - 1];
                }
            }
        }

        return lootables;
    }


}
