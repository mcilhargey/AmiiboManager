package fr.skyforce77.amiibomanager.swing;

import javax.swing.JMenu;

public class FileMenu extends JMenu{

	private static final long serialVersionUID = -2704709962235253382L;
	
	public FileMenu() {
		super("File");
		add(new LoadMenuItem());
	}

}
