/**
 * @filename Level.java
 * @author Shane
 * @date 09 Jan 2013
 */

package Engine;

// -----------------------------------------------------------------------------
// Imports & Definition
// -----------------------------------------------------------------------------

import java.awt.Point;
import java.util.ArrayList;



public class Level {

// -----------------------------------------------------------------------------
// Fields
// -----------------------------------------------------------------------------

    private Tile[][] level;
    private Lootable[][] objects;
    private ArrayList<PortalSquare> portalSquares;
    private Point size;
    private Point startPosition;




// -----------------------------------------------------------------------------
// Getters $ Setters
// -----------------------------------------------------------------------------
    public void setStartPosition(Point startPosition) {
        this.startPosition = startPosition;
    }

    public Point getStartPosition() {
        return startPosition;
    }

    public Tile[][] getLevel() {
        return this.level;
    }

    public Lootable[][] getObjects() {
        return this.objects;
    }

    public ArrayList<PortalSquare> getPortalSquares() {
        return portalSquares;
    }

    public Point getSize() {
        return this.size;
    }




// -----------------------------------------------------------------------------
// Constructors
// -----------------------------------------------------------------------------

    public Level() {
        this.portalSquares = new ArrayList<PortalSquare>();
    }

// -----------------------------------------------------------------------------
// Methods
// -----------------------------------------------------------------------------
    public boolean isWalkable(int x, int y) {
        boolean isTileWalkable;
        boolean isObjectWalkable;

        isTileWalkable = (level[x][y].isWalkable());
        isObjectWalkable = ( (objects[x][y] != null) && (objects[x][y].isWalkable()) );

        if (isObjectWalkable && isTileWalkable) {
            return true;
        }

        return false;
    }

    public void setSize(Point _size) {
        this.size = _size;
        this.level = new Tile[_size.x][_size.y];
        this.objects = new Lootable[_size.x][_size.y];
    }

    public void addPortalSquare(PortalSquare portalSquare) {
        this.portalSquares.add(portalSquare);
    }



// ---- INCOMPLE IMPLEMENTATION ----
    public void interact(Point target) {
        if (objects[target.x][target.y] == null) return;
        else {
            String message = objects[target.x][target.y].getMessage();
            String name = objects[target.x][target.y].getName();
            System.out.println(name + " says: ");
            System.out.println(message);
        }
    }

    public CombatCharacter getEnemy() {
        return null;
        // Supposed to retrieve an enemy from the level.
    }
// ----------------------------------

// ---- DEBUG -----------------------
    public void printTiles(){
        for (int x = 0; x < this.size.x; x++) {
            for (int y = 0; y < this.size.y; y++) {
                System.out.print(this.level[y][x].toString() + " ");
            }
            System.out.println();
        }
    }

    public void printObjects() {
        for (int x = 0; x < this.size.x; x++) {
            for (int y = 0; y < this.size.y; y++) {
                try {
                System.out.println(this.objects[x][y].toString());
                } catch (NullPointerException e) {}
            }
        }
    }
// ----------------------------------

//    public void createLevel() throws SlickException {
//
//        this.initImages();
//
//
//        for (int x = 0; x < this.size.x; x++) {
//            for (int y = 0; y < this.size.y; y++) {
//                // read the file for each square.
//                // this.level[x][y] = levelFile.read();
//            }
//        }
//
//        for (int x = 0; x < this.size.x; x++) {
//            for (int y = 0; y < this.size.y; y++) {
//                // read the file for each object.
//                // this.objects[x][y] = levelFile.read();
//            }
//        }
//
//        // -- PORTAL SQUARES --
//
//        PortalSquare red = new PortalSquare(new Point(16, 28), new Point(41, 38));
//        PortalSquare blue = new PortalSquare(new Point(18, 18), new Point(36, 43));
//        PortalSquare yellow = new PortalSquare(new Point(18, 36), new Point(36, 22));
//
//        portalSquares = new ArrayList<PortalSquare>();
//        portalSquares.add(red);
//        portalSquares.add(blue);
//        portalSquares.add(yellow);
//
//        // --- END OF PORTAL SQUARES
//
//
//        // --- OUTLINES ---
//
//        for (int x = 0; x <= 49; x++) level[x][0] = new Texture(VOID, false);
//        for (int x = 0; x <= 49; x++) level[x][49] = new Texture(VOID, false);
//        for (int y = 0; y <= 49; y++) level[0][y] = new Texture(VOID, false);
//        for (int y = 0; y <= 49; y++) level[49][y] = new Texture(VOID, false);
//
//        // --- END OF OUTLINES ---
//
//        // -- GRASS --
//
//        // y = 1; x = 1 - 18
//        for (int x = 1; x <= 18; x++) {
//            if (x % 2 == 0) {
//                level[x][1] = new Texture(GRASS, true);
//            } else {
//                level[x][1] = new Texture(GRASS, true);
//            }
//        }
//
//
//
//        // y = 2; x = 1 - 18
//        for (int x = 1; x <= 18; x++) {
//            if (x % 2 == 0) {
//                level[x][2] = new Texture(GRASS, true);
//            } else {
//                level[x][2] = new Texture(GRASS, true);
//            }
//        }
//
//        // y = 3; x = 1 - 20
//        for (int x = 1; x <= 20; x++) {
//            if (x % 2 == 0) {
//                level[x][3] = new Texture(GRASS, true);
//            } else {
//                level[x][3] = new Texture(GRASS, true);
//            }
//        }
//
//        // y = 4; x = 1 - 20
//        for (int x = 1; x <= 20; x++) {
//            if (x % 2 == 0) {
//                level[x][4] = new Texture(GRASS, true);
//            } else {
//                level[x][4] = new Texture(GRASS, true);
//            }
//        }
//
//        // y = 5; x = 1 - 21
//        for (int x = 1; x <= 21; x++) {
//            if (x % 2 == 0) {
//                level[x][5] = new Texture(GRASS, true);
//            } else {
//                level[x][5] = new Texture(GRASS, true);
//            }
//        }
//
//        // y = 6; x = 1 - 23
//        for (int x = 1; x <= 23; x++) {
//            if (x % 2 == 0) {
//                level[x][6] = new Texture(GRASS, true);
//            } else {
//                level[x][6] = new Texture(GRASS, true);
//            }
//        }
//
//        // y = 7; x = 1 - 26
//        for (int x = 1; x <= 26; x++) {
//            if (x % 2 == 0) {
//                level[x][7] = new Texture(GRASS, true);
//            } else {
//                level[x][7] = new Texture(GRASS, true);
//            }
//        }
//
//        // y = 8; x = 1 - 26
//        for (int x = 1; x <= 26; x++) {
//            if (x % 2 == 0) {
//                level[x][8] = new Texture(GRASS, true);
//            } else {
//                level[x][8] = new Texture(GRASS, true);
//            }
//        }
//
//        // y = 9; x = 1 - 29
//        for (int x = 1; x <= 29; x++) {
//            if (x % 2 == 0) {
//                level[x][9] = new Texture(GRASS, true);
//            } else {
//                level[x][9] = new Texture(GRASS, true);
//            }
//        }
//
//        // y = 10; x = 1 - 33
//        for (int x = 1; x <= 33; x++) {
//            if (x % 2 == 0) {
//                level[x][10] = new Texture(GRASS, true);
//            } else {
//                level[x][10] = new Texture(GRASS, true);
//            }
//        }
//
//        // y = 11; x = 1 - 38
//        for (int x = 1; x <= 38; x++) {
//            if (x % 2 == 0) {
//                level[x][11] = new Texture(GRASS, true);
//            } else {
//                level[x][11] = new Texture(GRASS, true);
//            }
//        }
//
//        //y = 12 - 48; x = 1 - 48
//        for (int y = 12; y <= 48; y++) {
//            for (int x = 1; x <= 48; x++) {
//                if ((x % 2 == 0 && y % 2 == 0) || (x % 2 != 0 && y % 2 != 0)) {
//                    level[x][y] = new Texture(GRASS, true);
//                } else {
//                    level[x][y] = new Texture(GRASS, true);
//                }
//            }
//        }
//
//        // --- END OF GRASS --- //
//
//        // --- WATER --- //
//
//        // y = 18; x = 23 - 25
//        for (int x = 23; x <= 25; x++) {
//            level[x][18] = new Texture(WATER, false);
//        }
//        // y = 19; x = 21 - 32
//        for (int x = 21; x <= 32; x++) {
//            level[x][19] = new Texture(WATER, false);
//        }
//        // y = 20; x = 21 - 32
//        for (int x = 21; x <= 32; x++) {
//            level[x][20] = new Texture(WATER, false);
//        }
//        // y = 21; x = 20 - 32
//        for (int x = 20; x <= 32; x++) {
//            level[x][21] = new Texture(WATER, false);
//        }
//        // y = 22; x = 20 - 33
//        for (int x = 20; x <= 33; x++) {
//            level[x][22] = new Texture(WATER, false);
//        }
//        // y = 23; x = 20 - 33
//        for (int x = 20; x <= 33; x++) {
//            level[x][23] = new Texture(WATER, false);
//        }
//        // y = 24; x = 19 - 33
//        for (int x = 19; x <= 33; x++) {
//            level[x][24] = new Texture(WATER, false);
//        }
//        // y = 25; x = 19 - 33
//        for (int x = 19; x <= 33; x++) {
//            level[x][25] = new Texture(WATER, false);
//        }
//        // y = 26; x = 19 - 34
//        for (int x = 19; x <= 34; x++) {
//            level[x][26] = new Texture(WATER, false);
//        }
//        // y = 27; x = 19 - 35
//        for (int x = 19; x <= 35; x++) {
//            level[x][27] = new Texture(WATER, false);
//        }
//        // y = 28; x = 20 - 37
//        for (int x = 20; x <= 37; x++) {
//            level[x][28] = new Texture(WATER, false);
//        }
//        // y = 29; x = 23 - 39
//        for (int x = 23; x <= 39; x++) {
//            level[x][29] = new Texture(WATER, false);
//        }
//        //y = 30 - 35; x = 24 - 40
//        for (int y = 30; y <= 35; y++) {
//            for (int x = 24; x <= 40; x++) {
//                level[x][y] = new Texture(WATER, false);
//            }
//        }
//        //y = 36; x = 24 - 39
//        for (int x = 24; x <= 39; x++) {
//            level[x][36] = new Texture(WATER, false);
//        }
//        //y = 37; x = 24 - 39
//        for (int x = 24; x <= 39; x++) {
//            level[x][37] = new Texture(WATER, false);
//        }
//        //y = 38; x = 24 - 38
//        for (int x = 24; x <= 38; x++) {
//            level[x][38] = new Texture(WATER, false);
//        }
//        //y = 39; x = 25 - 37
//        for (int x = 25; x <= 37; x++) {
//            level[x][39] = new Texture(WATER, false);
//        }
//        //y = 40; x = 27 - 35
//        for (int x = 27; x <= 35; x++) {
//            level[x][40] = new Texture(WATER, false);
//        }
//        //y = 41; x = 29 - 34
//        for (int x = 29; x <= 34; x++) {
//            level[x][41] = new Texture(WATER, false);
//        }
//        //y = 42; x = 30 - 34
//        for (int x = 30; x <= 34; x++) {
//            level[x][42] = new Texture(WATER, false);
//        }
//        // y = 43; x = 32
//        level[32][43] = new Texture(WATER, false);
//        // --- END OF WATER --- //
//
//        // --- STONE --- //
//
//        for (int y = 1; y <= 11; y++) {
//            for (int x = 17; x <= 48; x++) {
//                if (level[x][y] == null) {
//                    level[x][y] = new Texture(STONE, true);
//                }
//            }
//        }
//
//        Lootable barrelObject = new Lootable("barrel", "This barrel contains nothing.", true, BARREL, false, new Point(10,14));
//        objects[barrelObject.getPosition().x][barrelObject.getPosition().y] = barrelObject;
//    }

//    public boolean isWalkable(int x, int y) {
//        boolean walkable = false;
//
//        // if there is no object
//        if (objects[x][y] == null) {
//            // and you can walk on the tile
//            if (level[x][y].isWalkable()) {
//                // then it's a legal move
//                walkable = true;
//            }
//        }
//        // else if there is an object and you can walk there
//        else if (objects[x][y].isWalkable()) {
//            // and you can walk on the tile under it
//            if (level[x][y].isWalkable()) {
//                // then it's a legal move
//                walkable = true;
//            }
//        }
//
//        // else the default value of false is returned because it's illegal.
//        return walkable;
//    }

//    public void initImages() throws SlickException {
//        BLACK = new Image("res/Black.png");
//        GRASS = new Image("res/Grass.png");
//        GRASS2 = new Image("res/Grass2.png");
//        STONE = new Image("res/Stone.png");
//        VOID = new Image("res/Void.png");
//        WATER = new Image("res/Water.png");
//        BARREL = new Image("res/Barrel.png");
//    }


}
