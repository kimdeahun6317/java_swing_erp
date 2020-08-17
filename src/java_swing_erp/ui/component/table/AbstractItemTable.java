package java_swing_erp.ui.component.table;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java_swing_erp.dto.Student;

@SuppressWarnings("serial")
public abstract class AbstractItemTable<T> extends JTable {
	private CustomModel model;
	

	public AbstractItemTable() {
		initComponents();
	}
	
	private void initComponents() {
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	private void loadData(ArrayList<T> itemlist) {
		model = new CustomModel(getRows(itemlist),getColName());
		setModel(model);
	}
	
	abstract Object[] getColName();
	
	private Object[][] getRows(ArrayList<T> itemlist) {
		Object[][] rows = new Object[itemlist.size()][];
		for(int i =0; i<rows.length; i++) {
			rows[i] =  toArray(itemlist.get(i));
		}
		return rows;
	}
	
	abstract Object[] toArray(T item);

	public void setItems(ArrayList<T> itemlist) {
		loadData(itemlist);
		
		setWidthAndAlign();
		
	}

	/**
	 * //column width
		tableSetWidth(150,200,100,100,100,100,100);<br>
		//column alingnment<br>
		tableCellAlign(SwingConstants.CENTER, 0,1);<br>
		tableCellAlign(SwingConstants.RIGHT, 2,3,4,5,6);<br>
	 * 
	 */
	abstract void setWidthAndAlign();
	
	void tableCellAlign(int align, int...idx) {
		DefaultTableCellRenderer dtcr= new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(align);
		
		TableColumnModel tcm = getColumnModel();
		for(int i=0; i<idx.length; i++) {
			tcm.getColumn(idx[i]).setCellRenderer(dtcr);
		}
	}
	void tableSetWidth(int...width) {
		TableColumnModel tcm = getColumnModel();
		for(int i=0; i<width.length; i++) {
			tcm.getColumn(i).setPreferredWidth(width[i]);
		}
	}
	
	
	public void addRow(T item) {
		model.addRow(toArray(item));
	}
	
	public void removeRow(int idx) {
		model.removeRow(idx);
		
	}

	public void updateRow(int idx, T updateitem) {
		model.removeRow(idx);
		model.insertRow(idx, toArray(updateitem));
		
	}


	private class CustomModel extends DefaultTableModel{

		public CustomModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
			// TODO Auto-generated constructor stub
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false; //각셀수정 불가능
		}
		
		
	}

	
	
}	
