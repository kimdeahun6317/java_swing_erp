package java_swing_erp.ui.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import java_swing_erp.ui.component.contant.AbstractItemPanel;
import java_swing_erp.ui.component.table.AbstractItemTable;

@SuppressWarnings("serial")
public abstract class AbstractManagement<T> extends JFrame implements ActionListener {

	AbstractItemPanel<T> pStudent;

	JButton btnAdd;
	JButton btnCancel;

	AbstractItemTable<T> table;
	ArrayList<T> list = new ArrayList<T>();

	
	public void actionPerformed(ActionEvent e) {
		//System.out.println(e);

		if (e.getSource() instanceof JButton) {
			if (e.getSource() == btnCancel) {
				actionPerformedBtnCancel(e);
			}
			if (e.getSource() == btnAdd) {
				if (e.getActionCommand().equals("추가")) {
					actionPerformedBtnAdd(e);
				} else {
					actionPerformedBtnUpdate();
				}

			}
		}

		if (e.getSource() instanceof JMenuItem) {
			if (e.getActionCommand().equals("수정")) {
				actionPerformedMenuUpdate();
			}
			if (e.getActionCommand().equals("삭제")) {
				actionPerformedMenuDelete();
			}
			if (e.getActionCommand().equals("세부 정보")) {
				actionPerformedMenuDetail();
			}
		}
	}

	private void actionPerformedBtnUpdate() {
		//1.pstudentPanel에서 수정된 학생정보를 가져옴
		//2. stdlist에서 학생정보 수정
		//3. Studenttable에서 학생정보 수정
		//4.clearTf()
		//5.StudentPanel setEditableTf(true);
		//6.btnAdd 텍스트를 추가로 변경
		//7.message()
		T updateStd = pStudent.getItem();
		int idx = list.indexOf(updateStd);
		list.set(idx, updateStd);
		table.updateRow(idx, updateStd);
		pStudent.clearTf();
		pStudent.setEditableNoTf(true);
		btnAdd.setText("추가");

	}

	private void actionPerformedMenuUpdate() {
		System.out.println("수정");
		int selIdx = table.getSelectedRow();
		if (selIdx == -1) {
			JOptionPane.showMessageDialog(null, "해당 항목을 선택하세요");
			return;
		}
		T selStd = list.get(selIdx);
		pStudent.setItem(selStd);
		//1.버튼의 텍스트를 수정으로 변경
		//2. pstudent 학버 변경 불가
		btnAdd.setText("수정");
		pStudent.setEditableNoTf(false);
	}

	private void actionPerformedMenuDelete() {
		//		System.out.println("삭제");
		int selIdx = table.getSelectedRow();
		if (selIdx == -1) {
			JOptionPane.showMessageDialog(null, "해당 항목을 선택하세요");
			return;
		}

		T deletedstd = list.remove(selIdx);
		table.removeRow(selIdx);

	}

	private void actionPerformedMenuDetail() {
		System.out.println("세부정보");
		int selIdx = table.getSelectedRow();
		if (selIdx == -1) {
			JOptionPane.showMessageDialog(null, "해당 항목을 선택하세요");
			return;
		}
		T detailStd = list.get(selIdx);
		System.out.println(detailStd);
	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		//1.StudentPanel에서 getStudent()
		//2.studenttable getRow()
		//3.pstudentPanel cleartf()
		//4.Message()
		T newstd = pStudent.getItem();
		//System.out.println(newstd);

		table.addRow(newstd);

		pStudent.clearTf();

		list.add(newstd);

	}

	protected void actionPerformedBtnCancel(ActionEvent e) {
		pStudent.clearTf();
		if (btnAdd.getText().equals("수정")) {
			btnAdd.setText("추가");
			pStudent.setEditableNoTf(true);
			table.clearSelection();
		}
	}

	class CustomPopupMenu extends JPopupMenu {
		public CustomPopupMenu(ActionListener listener) {
			JMenuItem updateMeun = new JMenuItem("수정");
			updateMeun.addActionListener(listener);
			add(updateMeun);
			JMenuItem deleteMenu = new JMenuItem("삭제");
			deleteMenu.addActionListener(listener);
			add(deleteMenu);
			JMenuItem detailMenu = new JMenuItem("세부 정보");
			detailMenu.addActionListener(listener);
			add(detailMenu);
		}

	}
}
