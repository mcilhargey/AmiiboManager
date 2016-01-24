package fr.skyforce77.amiibomanager.swing;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import fr.skyforce77.amiibomanager.managers.DataManager;

public class AmiiboListModel implements ListModel<String>{

	@Override
	public void addListDataListener(ListDataListener l) {}

	@Override
	public String getElementAt(int index) {
		return (String)DataManager.getAmiiboMap().keySet().toArray()[index];
	}

	@Override
	public int getSize() {
		return DataManager.getAmiiboMap().keySet().size();
	}

	@Override
	public void removeListDataListener(ListDataListener l) {}

}
