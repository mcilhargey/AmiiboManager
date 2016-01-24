package fr.skyforce77.amiibomanager.swing;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.skyforce77.amiibomanager.data.Amiibo;
import fr.skyforce77.amiibomanager.managers.DataManager;

public class AmiiboChooseListener implements ListSelectionListener{
	
	private JPanel panel;
	
	public AmiiboChooseListener(JPanel panel) {
		this.panel = panel;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		AmiiboList source = (AmiiboList)e.getSource();
		Amiibo amiibo = DataManager.getAmiiboMap().get(source.getSelectedValue());
		panel.removeAll();
		
		panel.add(new JLabel(amiibo.getCharacter().toString()));
		
		panel.revalidate();
	}

}
