package fr.skyforce77.amiibomanager.swing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = -6054639400985508623L;
	
	private AmiiboList list;
	private JPanel content;
	
	public MainFrame() {
		super("Amiibo Manager");
		setSize(600, 500);
		
		setJMenuBar(new MainMenuBar());
		
		content = new JPanel();
		JScrollPane contentPane = new JScrollPane(content);
		
		list = new AmiiboList();
		list.addListSelectionListener(new AmiiboChooseListener(content));
		JScrollPane listPane = new JScrollPane(list);
		
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listPane, contentPane);
		splitPane.setDividerLocation(150);
		add(splitPane);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
