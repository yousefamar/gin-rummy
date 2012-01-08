package gui;
import gui.SpriteMap.SpriteType;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import core.GinRummyGame;

public class MainGUI extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private SpriteMap spriteMap = new SpriteMap();
	private JPanel humanImagePanel, computerImagePanel;
	private JButton playButton, humanPlus, humanMinus, computerPlus, computerMinus;
	private int humanCount=1, computerCount=3;
	
	/**
	 * The class for the main menu GUI.
	 * No double buffering due to few and small elements within.
	 * @param frame
	 */
	public MainGUI(JFrame frame){
		super(new GridBagLayout());
		this.frame = frame;
		
		/* Initialise all GridBagConstraint objects in a single array to save space */
		GridBagConstraints cons[] = new GridBagConstraints[7];
		for (int i=0;i<cons.length;i++)
			cons[i] = new GridBagConstraints();
		
		/* Set GBC values fields */
		cons[0].gridx = 0;
		cons[0].gridy = 0;
		cons[0].insets = new Insets(0,0,80,0);
		this.add(new JLabel(new ImageIcon(getClass().getResource("textures/logo.png"))),cons[0]);
		
		addButtons(cons);
	}

	private void addButtons(GridBagConstraints cons[]) {
		JPanel playerButtonPanel = new JPanel(new GridBagLayout());
        humanMinus = new JButton("-");
        humanMinus.addActionListener(this);
        humanPlus = new JButton("+");
        humanPlus.addActionListener(this);
        computerMinus = new JButton("-");
        computerMinus.addActionListener(this);
        computerPlus = new JButton("+");
        computerPlus.addActionListener(this);
        playButton = new JButton("Play");
        playButton.addActionListener(this);
        
        JPanel playerImagePanel = new JPanel(new GridBagLayout());
        
        humanImagePanel = new JPanel();
		humanImagePanel.add(new JLabel(new ImageIcon(spriteMap.getSprite(SpriteType.PLAYER))));
        cons[1].gridx = 0;
        cons[1].gridy = 0;
        cons[1].anchor = GridBagConstraints.WEST;
        playerImagePanel.add(humanImagePanel, cons[1]);
        
        cons[2].gridx = 1;
        cons[2].gridy = 0;
        playerImagePanel.add(new JPanel(), cons[2]);
        
        computerImagePanel = new JPanel();
        computerImagePanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        computerImagePanel.add(new JLabel(new ImageIcon(spriteMap.getSprite(SpriteType.COMPUTER))));
        computerImagePanel.add(new JLabel(new ImageIcon(spriteMap.getSprite(SpriteType.COMPUTER))));
        computerImagePanel.add(new JLabel(new ImageIcon(spriteMap.getSprite(SpriteType.COMPUTER))));
        cons[3].gridx = 2;
        cons[3].gridy = 0;
        cons[3].anchor = GridBagConstraints.EAST;
        playerImagePanel.add(computerImagePanel, cons[3]);
        
        cons[4].gridx = 0;
        cons[4].gridy = 1;
        cons[4].ipady = 20;
        this.add(playerImagePanel,cons[4]);
        
        /*To make the buttons a reasonable size, put them in other panels that use FlowLayout.*/
        playerButtonPanel.add(new JPanel().add(humanMinus));
        playerButtonPanel.add(new JLabel(" Humans "));
        playerButtonPanel.add(new JPanel().add(humanPlus));
        playerButtonPanel.add(new JPanel());
        playerButtonPanel.add(new JPanel().add(computerMinus));
        playerButtonPanel.add(new JLabel(" Computers "));
        playerButtonPanel.add(new JPanel().add(computerPlus));
        cons[5].gridx = 0;
        cons[5].gridy = 2;
        cons[5].ipady = 20;
        this.add(playerButtonPanel, cons[5]);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(playButton);
		cons[6].gridx = 0;
		cons[6].gridy = 3;
		cons[6].insets = new Insets(20,0,0,0);
        this.add(buttonPanel, cons[6]);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		/* Sets the player number fields and redraws the GUI accordingly.
		 * Also manages the number of humans vs. computer players to avoid
		 * an imbalanced number of either. (e.g. 0 for both or 3 total)*/
		Object source = e.getSource();
		if (source == humanMinus){
			if(humanCount<1)
				return;
			humanCount--;
			humanImagePanel.remove(humanCount);
			if(humanCount+computerCount<2) {
				computerCount++; 
				computerImagePanel.add(new JLabel(new ImageIcon(spriteMap.getSprite(SpriteType.COMPUTER))));
			}
			validate();
		} else if (source == humanPlus){
			if(humanCount>3)
				return;
			humanCount++;
			humanImagePanel.add(new JLabel(new ImageIcon(spriteMap.getSprite(SpriteType.PLAYER))));
			if(humanCount+computerCount>4) {
				computerCount--;
				computerImagePanel.remove(computerCount);
			}
			validate();
		} else if (source == computerMinus){
			if(computerCount<1)
				return;
			computerCount--;
			computerImagePanel.remove(computerCount);
			if(humanCount+computerCount<2) {
				humanCount++;
				humanImagePanel.add(new JLabel(new ImageIcon(spriteMap.getSprite(SpriteType.PLAYER))));
			}
			validate();
		} else if (source == computerPlus){
			if(computerCount>3)
				return;
			computerCount++;
			computerImagePanel.add(new JLabel(new ImageIcon(spriteMap.getSprite(SpriteType.COMPUTER))));
			if(humanCount+computerCount>4) {
				humanCount--;
				humanImagePanel.remove(humanCount);
			}
			validate();
		} else if (source == playButton){
			if(humanCount+computerCount==3)
				JOptionPane.showMessageDialog(frame, "Playing Gin Rummy with 3 people is not yet supported.", "Error", JOptionPane.ERROR_MESSAGE);
			else {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new GameCanvas(frame, spriteMap, new GinRummyGame(humanCount, computerCount)));
				frame.validate();
			}
		}
	}
}
