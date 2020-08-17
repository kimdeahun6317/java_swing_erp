package java_swing_erp.ui.component.contant;

import javax.swing.JPanel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java_swing_erp.dto.Student;
import java_swing_erp.ui.exception.EmptyTfException;
import java_swing_erp.ui.exception.InValidTfValue;

@SuppressWarnings("serial")
public abstract class AbstractItemPanel<T> extends JPanel {
	
	
	public AbstractItemPanel() {
		
	}

	public abstract void setItem(T item); 
	
	public abstract T getItem();
	
	abstract boolean isValidTf();
	
	boolean isTfEmpty() {
		for(Component c : getComponents()) {
			if(c instanceof JTextField) {
				if(((JTextField) c).getText().isEmpty()){
					return true;
				}
			}
		}
		return false;
	}
	
	public void setTfNotEditable(boolean isEditable) {
		for(Component c : getComponents()) {
			if(c instanceof JTextField) {
				((JTextField) c).setEditable(isEditable);
			}
		}
	}
	
	public void clearTf() {
		for(Component c : getComponents()) {
			if(c instanceof JTextField) {
				((JTextField) c).setText("");
			}
		}
		
	}
	
	public abstract void setEditableNoTf(boolean isEditable);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
