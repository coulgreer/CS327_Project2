import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class EthernetSwitch {

	public static void main(String[] args) {

		JFrame frame = new JFrame("Ethernet Switch");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTable table = new JTable(new MyTableModel());

		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout());
		frame.add(tablePanel, BorderLayout.CENTER);

		JScrollPane pane = new JScrollPane(table);
		tablePanel.add(pane, BorderLayout.CENTER);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		
		JPanel buttonPanel = new JPanel();
		frame.add(buttonPanel, BorderLayout.SOUTH);

		JButton updateButton = new JButton("Update");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String portString;
				int incomingPort = 0;
				do {
					portString = JOptionPane.showInputDialog(frame, "Where is the frame going?");
					try {
						incomingPort = Integer.parseInt(portString);
						if (0 < incomingPort && incomingPort <= 24) {
							break;
						} else {
							JOptionPane.showMessageDialog(frame, "Enter an integer between 1 and 24");
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(frame, "You must enter an integer.");
					}
				} while (portString != null);

				String senderAddress;
				do {
					senderAddress = JOptionPane.showInputDialog(frame, "What is the source MAC address.");
					if (senderAddress.length() == 6) {
						break;
					} else {
						JOptionPane.showMessageDialog(frame, "The MAC address should be exactly 6 characters long.");
					}
				} while (senderAddress != null);

				String destinationAddress;
				do {
					destinationAddress = JOptionPane.showInputDialog(frame, "What is the destination MAC address.");
					if (destinationAddress.length() == 6) {
						break;
					} else {
						JOptionPane.showMessageDialog(frame, "The MAC address should be exactly 6 characters long.");
					}
				} while (destinationAddress != null);
				if (incomingPort > 0 && senderAddress.length() == 6 && destinationAddress.length() == 6) {
					table.getModel().setValueAt(senderAddress + "" + destinationAddress, incomingPort - 1, 1);
				}
			}
		});
		buttonPanel.add(updateButton);

		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				for (int i = 0; i < table.getRowCount(); i++) {
					table.getModel().setValueAt("   -E M P T Y-   ", i, 1);
				}
			}
		});
		buttonPanel.add(clearButton);

		frame.setVisible(true);
		frame.pack();

	}

}
