package Engine;

import java.util.ArrayList;
import java.util.Locale;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * @filename TextureManager.java
 * @author Shane Kelly
 * @date 03 Feb 2013
 */

public class TextureManager {

// -----------------------------------------------------------------------------
// Fields
// -----------------------------------------------------------------------------

    private ArrayList<Texture> textures;

// -----------------------------------------------------------------------------
// Getters $ Setters
// -----------------------------------------------------------------------------

    public ArrayList<Texture> getImages() {
        return textures;
    }

    public void setImages(ArrayList<Texture> textures) {
        this.textures = textures;
    }


// -----------------------------------------------------------------------------
// Constructors
// -----------------------------------------------------------------------------

    public TextureManager() throws SlickException {

        this.textures = new ArrayList<Texture>();
        this.textures.add(new Texture(new Image("res/tileImages/Black.png"), false, "black"));
    }


    public TextureManager(ArrayList<Texture> images) {
        this.textures = images;

    }

// -----------------------------------------------------------------------------
// Methods
// -----------------------------------------------------------------------------

    public Image getImageByName(String _name) {
        for (int idx = 0; idx < textures.size(); idx++) {
            if (textures.get(idx).getImageName().equals(_name)) {
                return textures.get(idx).getImage();
            }
        }
        return null;
    }

    public void addTextureByTile(Tile tile) throws SlickException {
        String imageStr = "res/tileImages/" + tile.getImageName().substring(0, 1).toUpperCase(Locale.ENGLISH) + tile.getImageName().substring(1)  + ".png";
        Image image = new Image(imageStr);
        Texture textureToAdd = new Texture(image, tile.isWalkable(), tile.getImageName());
        this.textures.add(textureToAdd);
    }

    // Testing & Debugging purposes
    private void dumpTextureNames() {
        System.out.print("The amount of textures is: ");
        System.out.println(textures.size());
        System.out.print("Here are the texture names:");
        for (int i = 0; i < textures.size(); i++) {
            System.out.println(textures.get(i).getImage());
        }
    }

}
