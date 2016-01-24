package fr.skyforce77.amiibomanager;

import javax.swing.UIManager;

import fr.skyforce77.amiibomanager.managers.DataManager;
import fr.skyforce77.amiibomanager.swing.MainFrame;

public class AmiiboManager {
	
	private static MainFrame mainFrame;
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		
		DataManager.load();
		mainFrame = new MainFrame();
	}
	
	public static MainFrame getMainFrame() {
		return mainFrame;
	}
	
}
