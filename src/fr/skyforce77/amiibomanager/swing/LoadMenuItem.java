package fr.skyforce77.amiibomanager.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import fr.skyforce77.amiibomanager.AmiiboManager;
import fr.skyforce77.amiibomanager.data.Amiibo;
import fr.skyforce77.amiibomanager.managers.DataManager;

public class LoadMenuItem extends JMenuItem implements ActionListener {

	private static final long serialVersionUID = 2489683982756058446L;
	
	public LoadMenuItem() {
		super("Load");
		setMnemonic('L');
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Load amiibo encrypted data");
		fc.setApproveButtonText("Load");
		fc.setMultiSelectionEnabled(true);
		
		JFileChooser ofc = new JFileChooser();
		ofc.setApproveButtonText("Load");
		ofc.setMultiSelectionEnabled(false);
		
		int returnVal = fc.showOpenDialog(AmiiboManager.getMainFrame());
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	for(File f : fc.getSelectedFiles()) {
	    		ofc.setDialogTitle("Load amiibo decrypted data for: "+f.getName());
	    		int oReturnVal = ofc.showOpenDialog(AmiiboManager.getMainFrame());
	    		if(oReturnVal == JFileChooser.APPROVE_OPTION) {
					Amiibo amiibo = Amiibo.fromDumps(f, ofc.getSelectedFile());
					String ret = JOptionPane.showInputDialog(AmiiboManager.getMainFrame(), "Choose name for new backup: "+f.getName());
					if(ret != "") {
						DataManager.getAmiiboMap().put(ret, amiibo);
					}
	    		}
	    	}
	    	DataManager.save();
	    }
	}

}
