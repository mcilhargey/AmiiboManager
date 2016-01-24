package fr.skyforce77.amiibomanager.swing;

import javax.swing.JMenuBar;

public class MainMenuBar extends JMenuBar {

	private static final long serialVersionUID = -4172583205118976752L;
	
	public MainMenuBar() {
		add(new FileMenu());
	}

}
