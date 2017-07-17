package org.wikipathways.cytoscapeapp.impl;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.cytoscape.application.swing.search.AbstractNetworkSearchTaskFactory;
import org.cytoscape.work.TaskIterator;
import org.wikipathways.cytoscapeapp.WPClient;
import org.wikipathways.cytoscapeapp.internal.cmd.WPSearchCmdTaskFactory;

public class WPNetworkSearchTaskFactory extends AbstractNetworkSearchTaskFactory {

	private final WPClient client;
//	private ImageIcon ICON;
	private final ImageIcon ICON = new ImageIcon(getClass().getClassLoader().getResource("logo_150.png"));
	@Override
	public Icon getIcon() 		{ return ICON; }

	public WPNetworkSearchTaskFactory(WPClient clnt, ImageIcon icon) {
		super(
				"wikipathways-netsearchtest.test-b",		// id
				"Wikipathways",								// name
				"A user-curated pathway collection", 		// description
				null										// icon
		);
		
		client = clnt;
	//	ICON = icon;
	}
	
	@Override
	public TaskIterator createTaskIterator() {
		WPSearchCmdTaskFactory factory = new WPSearchCmdTaskFactory(client);
		return factory.createTaskIterator();
	}
	
//	@Override
//	public JComponent getOptionsComponent() {
//		JCheckBox cb1 = new JCheckBox("DWIM", true);
//		JCheckBox cb2 = new JCheckBox("Custom");
//		cb1.setForeground(Color.WHITE);
//		cb2.setForeground(Color.WHITE);
//		
//		JPanel p = new JPanel(new BorderLayout());
//		p.setBackground(Color.DARK_GRAY);
//		
//		p.add(cb1, BorderLayout.NORTH);
//		p.add(cb2, BorderLayout.SOUTH);
//		
//		return p;
//	}

}