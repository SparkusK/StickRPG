/**
 * @filename Texture.java
 * @author Shane
 * @date 09 Jan 2013
 */

package Engine;

// -----------------------------------------------------------------------------
// Imports & Definition
// -----------------------------------------------------------------------------

import org.newdawn.slick.Image;

public class Texture {



// -----------------------------------------------------------------------------
// Fields
// -----------------------------------------------------------------------------

    private Image image;
    private String imageName;

// -----------------------------------------------------------------------------
// Getters $ Setters
// -----------------------------------------------------------------------------

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }


// -----------------------------------------------------------------------------
// Constructors
// -----------------------------------------------------------------------------

    public Texture(Image image, boolean walkable) {
        this.image = image;
    }

    public Texture(String imageName, boolean walkable) {
        this.imageName = imageName;
    }

    public Texture(Image image, boolean walkable, String imageName) {
        this.image = image;
        this.imageName = imageName;
    }

    public Texture() {}




// -----------------------------------------------------------------------------
// Methods
// -----------------------------------------------------------------------------

    @Override
    public String toString() {
        String file = this.image.getResourceReference();
        int slashPos = file.indexOf('/');
        int dotPos = file.indexOf('.');
        file = file.substring(slashPos+1, dotPos);
        file = file.toLowerCase();
        String output = file;
        return output;
    }
}
