package gui;

import gui.SpriteMap.SpriteType;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import core.*;

public class GameCanvas extends Canvas implements MouseListener{
	private static final long serialVersionUID = 1L;

	private int playerTurn=0;
	private SpriteMap spriteMap;
	private GinRummyGame theGame;
	private int clickedCard=-1, clickedStack=-1;
	private Font playerFont = new Font("Book Antiqua", Font.BOLD, 18);
	private Color cardShader = new Color(255,238,0,127);
	
	public GameCanvas(SpriteMap spriteMap, GinRummyGame theGame) {
		this.spriteMap = spriteMap;
		this.theGame = theGame;
		setBackground(new Color(0, 130, 59));
		this.addMouseListener(this);
	}
	
	@Override
	public void paint(Graphics gfx) {
		Graphics2D gfx2D = (Graphics2D) gfx;
		drawCards(gfx2D);
//		for (Entity entity:world.entityList.getEntityList())
//			entity.draw(this, g2D);
	 }

	private void drawCards(Graphics2D gfx2D) {
		for (int i=0;i<7;i++) {
			gfx2D.setColor(i==clickedCard?Color.YELLOW:Color.WHITE);
			gfx2D.fillRect((i*50)+50, 290, 40, 60);
			gfx2D.setColor(Color.DARK_GRAY);
			gfx2D.drawRect((i*50)+51, 291, 37, 57);
			gfx2D.setColor((theGame.currentPlayer.hand.get(i).isRed()&&theGame.currentPlayer instanceof HumanPlayer)?Color.RED:Color.BLACK);
			gfx2D.drawString (theGame.currentPlayer.hand.get(i).toString(), (i*50)+59, 316);
			gfx2D.drawImage(spriteMap.getSprite(SpriteType.CARDBACK), null, (i*30)+122, 20);
			gfx2D.drawImage(spriteMap.getSprite(SpriteType.CARDBACKROT), null, 20-20, (i*30)+60);
			gfx2D.drawImage(spriteMap.getSprite(SpriteType.CARDBACKROT), null, 392-20, (i*30)+60);
		}
		gfx2D.setColor(clickedStack==0?Color.YELLOW:Color.WHITE);
		gfx2D.fillRect(150, 130, 50, 75);
		gfx2D.setColor(Color.DARK_GRAY);
		gfx2D.drawRect(151, 131, 47, 72);
		gfx2D.setColor(theGame.discardPile.peek().isRed()?Color.RED:Color.BLACK);
		gfx2D.drawString (theGame.discardPile.peek().toString(), 165, 161);
		
		gfx2D.drawImage(spriteMap.getSprite(SpriteType.CARDBACKLARGE), null, 240, 130);
		if(clickedStack==1) {
			gfx2D.setColor(cardShader);
			gfx2D.fillRect(240, 130, 50, 75);
		}
		
		gfx2D.setColor(Color.BLUE);
		gfx2D.setFont(playerFont);
		gfx2D.drawString ("Player "+(theGame.currentPlayer.playerID+1), 190, 280);
	}

	@Override
	public void mouseClicked(MouseEvent parammouseEvent) {}

	//I use mousePressed instead of mouseClicked because mouseClicked ignores accidental drags.
	@Override
	public void mousePressed(MouseEvent mouseEvent) {
		int posX=mouseEvent.getX(), posY=mouseEvent.getY();
		if(posX>150&&posX<200&&posY>130&&posY<205){
			clickedStack=0;
			repaint();
		}else if(posX>240&&posX<260&&posY>130&&posY<205) {
			clickedStack=1;
			repaint();
		} else
			for (int i=0;i<7;i++) {
				if(posX>(i*50)+50&&posX<(i*50)+90&&posY>290&&posY<350) {
					clickedCard=i;
					repaint();
					break;
				}
			}
		if (clickedCard>=0&&clickedStack>=0) {
			
		}
	}

	@Override
	public void mouseReleased(MouseEvent paramMouseEvent) {}

	@Override
	public void mouseEntered(MouseEvent paramMouseEvent) {}

	@Override
	public void mouseExited(MouseEvent paramMouseEvent) {}
}