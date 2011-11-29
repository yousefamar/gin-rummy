package core;

import gui.MainGUI;

import java.awt.*;
import javax.swing.*;

public class Main {

	public static void main(String args[]) {
		JFrame frame = new JFrame("~ Gin Rummy Card Game ~");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MainGUI gui = new MainGUI(frame);

		frame.setSize(new Dimension(450,400));
		frame.setResizable(false);
		frame.getContentPane().add(gui);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		/*
		
		Console console = System.console();
		int players = 0, humans = -1;
		
		do {
			System.out.println("~ Gin Rummy Card Game ~");
			System.out.printf("Number of players: ");
			try {
				players = Integer.parseInt(console.readLine());
			} catch (Exception e) {
				players = 0;
			}
			if(players<1 || players>7)
				System.out.println("Invalid selection.");
		} while(players<1 || players>7);
		
		do {
			System.out.printf("Of which are human players (0 to watch an all computer game): ");
			try {
				humans = Integer.parseInt(console.readLine());
			} catch (Exception e) {
				humans = -1;
			}
			if(humans<0)
				System.out.println("Invalid selection.");
		} while(humans<1);
		
		new GinRummyGame(players, humans);
		*/
	}
}
