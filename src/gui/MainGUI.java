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
	private int humanNum=1, computerNum=3;
	
	public MainGUI(JFrame frame){
		super(new GridBagLayout());
		this.frame = frame;
		GridBagConstraints cons[] = new GridBagConstraints[7];
		for (int i=0;i<cons.length;i++)
			cons[i] = new GridBagConstraints();
		
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
        
        //To make the buttons a reasonable size, I put them in other panels that use FlowLayout.
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
	public void repaint() {
		super.repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == humanMinus){
			if(humanNum<1)
				return;
			humanNum--;
			humanImagePanel.remove(humanNum);
			if(humanNum+computerNum<2) {
				computerNum++; 
				computerImagePanel.add(new JLabel(new ImageIcon(spriteMap.getSprite(SpriteType.COMPUTER))));
			}
			validate();
		} else if (source == humanPlus){
			if(humanNum>3)
				return;
			humanNum++;
			humanImagePanel.add(new JLabel(new ImageIcon(spriteMap.getSprite(SpriteType.PLAYER))));
			if(humanNum+computerNum>4) {
				computerNum--;
				computerImagePanel.remove(computerNum);
			}
			validate();
		} else if (source == computerMinus){
			if(computerNum<1)
				return;
			computerNum--;
			computerImagePanel.remove(computerNum);
			if(humanNum+computerNum<2) {
				humanNum++;
				humanImagePanel.add(new JLabel(new ImageIcon(spriteMap.getSprite(SpriteType.PLAYER))));
			}
			validate();
		} else if (source == computerPlus){
			if(computerNum>3)
				return;
			computerNum++;
			computerImagePanel.add(new JLabel(new ImageIcon(spriteMap.getSprite(SpriteType.COMPUTER))));
			if(humanNum+computerNum>4) {
				humanNum--;
				humanImagePanel.remove(humanNum);
			}
			validate();
		} else if (source == playButton){
			if(humanNum+computerNum==3)
				JOptionPane.showMessageDialog(frame, "Playing Gin Rummy with 3 people is not yet supported.", "Error", JOptionPane.ERROR_MESSAGE);
			else {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new GameCanvas(frame, spriteMap, new GinRummyGame(humanNum, computerNum)));
				frame.validate();
			}
		}
	}
}
