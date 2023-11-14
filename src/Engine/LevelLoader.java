package Engine;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;
import org.newdawn.slick.SlickException;

/**
 * @filename LevelLoader.java
 * @author Shane Kelly
 * @date 14 Jan 2013
 */

public class LevelLoader {

// -----------------------------------------------------------------------------
// Constructors
// -----------------------------------------------------------------------------

    public LevelLoader() {}

// -----------------------------------------------------------------------------
// Methods
// -----------------------------------------------------------------------------


    // Example input: "level001.sticklvl"
    // What I need: "D:\\Game dev\\slick games\\Slickout\\StickRPG\\src\\res\\levels\\level001.sticklvl"
    // This function assumes that all levels are in: D:\\Game dev\\slick games\\Slickout\\StickRPG\\src\\res\\levels\\
    // map = LevelLoader.readLevel(new FileInputStream(new File("D:\\Game dev\\slick games\\Slickout\\StickRPG\\src\\res\\level001.sticklvl")));
    public static Level readLevel(String levelFile) throws SlickException, FileNotFoundException {

        Level level = new Level();
        String pathToLevel = res.levels.path.class.getResource(levelFile).getPath();
        pathToLevel = pathToLevel.replaceAll("%20", " ");
        pathToLevel = pathToLevel.substring(1);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(pathToLevel))));
        level = parseLevel(reader);
        return level;
    }

    public static Level readLevel(InputStream input) throws SlickException {
        Level level = new Level();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        level = parseLevel(reader);
        return level;
    }

    public static Level parseLevel(BufferedReader reader) {
        Level level = new Level();
        String line;
        String[] args;

        try {
            // --- size ---
            line = reader.readLine();
            if (line.contains("size")) {}
            line = reader.readLine();
            args = line.split(",");
            level.setSize(new Point(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
            line = reader.readLine();
            if (line.contains("size")) {}
            // --- size finished ---
            // --- portal squares ---
            line = reader.readLine();
            if (line.contains("portalsquares")) {}
            int count = 0;
            while (!((line = reader.readLine()).contains("portalsquares"))) {
                args = line.split(" ");
                String[] fromPoint = args[0].split(",");
                String[] targetPoint = args[2].split(",");
                String levelStr = args[1];
                Point from = new Point(Integer.parseInt(fromPoint[0]), Integer.parseInt(fromPoint[1]));
                Point target = new Point(Integer.parseInt(targetPoint[0]), Integer.parseInt(targetPoint[1]));
                count++;
                level.addPortalSquare(new PortalSquare(from, levelStr, target));
            }
            // --- portal squares finished ---
            // --- tiles ---
            line = reader.readLine();
            if (line.contains("tiles")) {}
            int y = 0;
            while (!((line = reader.readLine()).contains("tiles"))) {
                String[] levelLine = line.split(" ");
                for (int x = 0; x < level.getSize().x; x++) {
                    String[] tileArgs = levelLine[x].split(",");
                    boolean walkable = Boolean.parseBoolean(tileArgs[1]);
                    level.getLevel()[x][y] = new Tile(tileArgs[0], walkable);

                }
                y++;
            }
            // --- tiles finished ---
            // --- objects ---
            line = reader.readLine();
            if (line.contains("objects")) {
            }
            while (!((line = reader.readLine()).contains("objects"))) {
                // sample input: barrel,false,barrel,This barrel contains nothing.,10,14,true
                Lootable object = new Lootable();
                args = line.split(",");
                // retrieve object variables
                String image = args[0];
                boolean walkable = Boolean.parseBoolean(args[1]);
                String name = args[2];
                name = name.substring(0, 1).toUpperCase(Locale.ENGLISH) + name.substring(1);
                String message = args[3];
                Point pos = new Point(Integer.parseInt(args[4]), Integer.parseInt(args[5]));
                boolean interactable = Boolean.parseBoolean(args[6]);
                // set object values
                object.setImageName(image);
                object.setWalkable(walkable);
                object.setMessage(message);
                object.setName(name);
                object.setPosition(pos);
                object.setInteractable(interactable);
                // insert object into level
                level.getObjects()[pos.x][pos.y] = object;
            }
            // --- objects finished ---
            // --- starting position ---
            line = reader.readLine();
            if (line.contains("startingposition")) {}
            line = reader.readLine();
            args = line.split(",");
            Point startPos = new Point(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            level.setStartPosition(startPos);
            line = reader.readLine();
            if (line.contains("startingposition")) {}
            // --- starting position finished ---
        } catch (IOException ex) {
            System.out.println("something went wrong.");
        }

        return level;
    }

    public static void addTextures(String levelFile, TextureManager textures) throws SlickException, FileNotFoundException {

        ArrayList<Tile> tiles = new ArrayList<Tile>();
        String pathToLevel = res.levels.path.class.getResource(levelFile).getPath().replaceAll("%20", " ");
        pathToLevel = pathToLevel.substring(1);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(pathToLevel))));
        String line;
        String[] elements;
        Tile tile;

        try {
            // skip to tiles
            while (!((line = reader.readLine()).contains("tiles"))){ continue;     }
            // obtain all tiles from level tiles
            while (!((line = reader.readLine()).contains("tiles"))) {
                elements = line.split(" ");
                for(String elem: elements) {
                    String[] args = elem.split(",");
                    tile = new Tile(args[0], Boolean.parseBoolean(args[1]));
                    if (!tilesContainsTile(tile, tiles)) {
                        tiles.add(tile);
                    }
                }
            }
            // skip to objects
            while (!((line = reader.readLine()).contains("objects"))){ continue;    }
            // obtain all tiles from level objects
            while (!((line = reader.readLine()).contains("objects"))) {
                System.out.println(line);
                elements = line.split(",");
                tile = new Tile(elements[0], Boolean.parseBoolean(elements[1]));
                if (!tiles.contains(tile)) {
                    tiles.add(tile);
                }
            }
        } catch (IOException ex) {
            System.out.println("something went wrong.");

        }
        for (Tile e_tile : tiles) {
            textures.addTextureByTile(e_tile);
        }
    }

    public static void addTextures(InputStream input, TextureManager textures) throws SlickException {
        // First, receive all distinct tiles from the level. Place it in tiles.
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line;
        String[] elements;
        try {
            // skip to tiles
            while (!((line = reader.readLine()).contains("tiles"))) {
                continue;
            }
            // obtain all tiles from level tiles
            while (!((line = reader.readLine()).contains("tiles"))) {
                System.out.println(line);
                elements = line.split(" ");
                for(String elem: elements) {
                    String[] args = elem.split(",");
                    Tile tile = new Tile(args[0], Boolean.parseBoolean(args[1]));
                    if (!tiles.contains(tile)) {
                        tiles.add(tile);
                    }
                }
            }
            // skip to objects
            while (!((line = reader.readLine()).contains("objects"))) {
                continue;
            }
            // obtain all tiles from level objects
            while (!((line = reader.readLine()).contains("objects"))) {
                System.out.println(line);
                elements = line.split(",");

                Tile tile = new Tile(elements[0], Boolean.parseBoolean(elements[1]));
                if (!tiles.contains(tile)) {
                    tiles.add(tile);
                }
            }
        } catch (IOException ex) {
            System.out.println("something went wrong.");

        }
        for (Tile tile : tiles) {
            textures.addTextureByTile(tile);
        }
    }

    private static boolean tilesContainsTile(Tile tile, ArrayList<Tile> tiles) {

        boolean alsoWalkable;
        boolean sameImage;

        for (int idx = 0; idx < tiles.size(); idx++) {
            alsoWalkable = (tile.isWalkable() == tiles.get(idx).isWalkable());
            sameImage = (tile.getImageName().equals(tiles.get(idx).getImageName()));
            if (alsoWalkable && sameImage) {
                return true;
            }
        }

        return false;
    }
}

