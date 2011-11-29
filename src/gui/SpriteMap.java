package gui;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class SpriteMap {

	private BufferedImage spriteSheet;
	private ArrayList<BufferedImage> sprites = new ArrayList<BufferedImage>();
	
	public SpriteMap() {
		try{spriteSheet = ImageIO.read(getClass().getResource("textures/spritemap.png"));}
		catch(IOException e){e.printStackTrace();}

		addSprite(SpriteType.PLAYER);
		addSprite(SpriteType.COMPUTER);
		addSprite(SpriteType.CARDBACK);
		addSprite(SpriteType.CARDBACKROT);
		addSprite(SpriteType.CARDBACKLARGE);
	}
	
	private void addSprite(SpriteType sprite) {
		if(sprite==SpriteType.CARDBACKROT){
			BufferedImage rotatedImage = spriteSheet.getSubimage(sprite.texX, sprite.texY, sprite.sizeX, sprite.sizeY);
			AffineTransform tx = new AffineTransform();
			tx.rotate(Math.toRadians(90), 20, 30);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
			rotatedImage = op.filter(rotatedImage, null);
			sprites.add(rotatedImage);
		}else if(sprite==SpriteType.CARDBACKLARGE){
			BufferedImage scaledImage = spriteSheet.getSubimage(sprite.texX, sprite.texY, sprite.sizeX, sprite.sizeY);
			AffineTransform tx = new AffineTransform();
			tx.scale(2.5, 2.5);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BICUBIC);
			scaledImage = op.filter(scaledImage, null);
			sprites.add(scaledImage);
		}else
			sprites.add(spriteSheet.getSubimage(sprite.texX, sprite.texY, sprite.sizeX, sprite.sizeY));
	}

	public BufferedImage getSprite(SpriteType sprite) {
		return sprites.get(sprite.id);
	}
	
	public enum SpriteType {
		PLAYER(0,0,0,9,18),
		COMPUTER(1,9,0,11,15),
		CARDBACK(2,0,18,20,30),
		CARDBACKROT(3,0,18,20,30),
		CARDBACKLARGE(4,0,18,20,30);

		public int id, texX, texY, sizeX, sizeY;
		
		private SpriteType(int id, int texX, int texY, int sizeX, int sizeY){
			this.id=id;
			this.texX=texX;
			this.texY=texY;
			this.sizeX=sizeX;
			this.sizeY=sizeY;
		}
	}
}
