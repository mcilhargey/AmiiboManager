package fr.skyforce77.amiibomanager.swing;

import javax.swing.JList;

public class AmiiboList extends JList<String> {

	private static final long serialVersionUID = 4649210348130854610L;
	
	public AmiiboList() {
		setModel(new AmiiboListModel());
	}

}
