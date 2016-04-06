import javax.swing.table.AbstractTableModel;

class MyTableModel extends AbstractTableModel {
	private String[] columnNames = { "Port #", "MAC" };
	private Object[][] data = new Object[24][];

	public MyTableModel() {
		for (int i = 0; i < 24; i++) {
			data[i] = new Object[] { i + 1, "   -E M P T Y-   " };
		}
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	public void setValueAt(Object value, int row, int col) {
		data[row][col] = value;
		fireTableCellUpdated(row, col);
	}
}