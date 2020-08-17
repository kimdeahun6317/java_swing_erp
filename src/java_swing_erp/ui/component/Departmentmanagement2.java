package java_swing_erp.ui.component;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.temporal.JulianFields;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import java_swing_erp.dto.Department;
import java_swing_erp.ui.component.contant.DepartmentPanel;
import java_swing_erp.ui.component.table.DepartmentTable;

@SuppressWarnings("serial")
public class Departmentmanagement2 extends JFrame implements ActionListener{

	private JPanel contentPane;
	private DepartmentPanel pdepartment;
	private JPanel pbtn;
	private JPanel ptable;
	private JButton btnAdd;
	private JButton btnCencel;
	private ArrayList<Department> delist = new ArrayList<Department>();
	private JScrollPane scrollPane;
	private DepartmentTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Departmentmanagement2 frame = new Departmentmanagement2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Departmentmanagement2() {
		
		
		initComponents();
		
		
		Department std = new Department(1, "김대훈", "053-1111-111");
		pdepartment.setItem(std);

		delist.add(new Department(1, "김대훈", "053-2222-2222"));
		delist.add(new Department(2, "이현석", "053-3333-3333"));
		
		table.setItems(delist);
		
	}
	void initComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 499, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		pdepartment = new DepartmentPanel();
		contentPane.add(pdepartment);
		
		pbtn = new JPanel();
		contentPane.add(pbtn);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pbtn.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pbtn.add(btnAdd);
		
		btnCencel = new JButton("취소");
		btnCencel.addActionListener(this);
		pbtn.add(btnCencel);
		
		ptable = new JPanel();
		contentPane.add(ptable);
		ptable.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		ptable.add(scrollPane, BorderLayout.CENTER);
		
		table = new DepartmentTable();
		
		CustomPopupMenu pupMenu = new CustomPopupMenu(this);
		table.setComponentPopupMenu(pupMenu);
		
		scrollPane.setViewportView(table);
		
	}
	
	

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCencel) {
			actionPerformedBtnCencel(e);
		}
		if (e.getSource() == btnAdd) {
			if(e.getActionCommand().equals("추가")) {
			actionPerformedBtnAdd(e);
			}else {
				actionPerformedBtnUpdate();
			}
		}
		
		if(e.getSource() instanceof JMenuItem) {
			if(e.getActionCommand().equals("수정")) {
				System.out.println("수정");
				actionPerformedMenuUpdate();
			}
			if(e.getActionCommand().equals("삭제")) {
				System.out.println("삭제");
				actionPerformedMenuDelete();
			}
			if(e.getActionCommand().equals("세부 정보")) {
				System.out.println("세부 정보");
				actionPerformedMenuDetail();
			}
		}
		
	}
	
	
	
	private void actionPerformedBtnUpdate() {
		Department updatede = pdepartment.getItem();
		System.out.println(updatede);
		int idx = delist.indexOf(updatede);
		System.out.println(idx);
		delist.set(idx, updatede);
		table.updateRow(idx, updatede);
		pdepartment.clearTf();
		pdepartment.setEditableNoTf(true);
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, String.format("%s(%d) 수정완료", updatede.getName(), updatede.getNo()));
	}

	private void actionPerformedMenuDetail() {
		System.out.println("세부 정보");
		int selIdx = table.getSelectedRow();
		if(selIdx == -1) {
			JOptionPane.showMessageDialog(null,"해당 항목을 선택하세요" );
			return;
		}
		Department detailde = delist.get(selIdx);
		System.out.println(detailde);
		DepartmentDetailDlg dlg = new DepartmentDetailDlg();
		dlg.setDepartment(detailde);
		dlg.setTfNotEditable();
		dlg.setVisible(true);
		
	}

	private void actionPerformedMenuDelete() {
		System.out.println("삭제");
		int selIdx = table.getSelectedRow();
		if(selIdx ==- 1) {
			JOptionPane.showMessageDialog(null, "해당항목을 선택하세여");
			return;
		}
		Department deletede = delist.remove(selIdx);
		table.removeRow(selIdx);
		JOptionPane.showMessageDialog(null, String.format("%s(%d) 삭제완료", deletede.getName(), deletede.getNo()));
		
	}

	private void actionPerformedMenuUpdate() {
		System.out.println("수정");
		int selIdx = table.getSelectedRow();
		if(selIdx ==- 1) {
			JOptionPane.showMessageDialog(null, "해당항목을 선택하세여");
			return;
		}
		
		Department selde = delist.get(selIdx);
		pdepartment.setItem(selde);
		btnAdd.setText("수정");
		pdepartment.setEditableNoTf(false);
				
	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		Department newde = pdepartment.getItem();
		System.out.println(newde);
		table.addRow(newde);
		pdepartment.clearTf();
		JOptionPane.showMessageDialog(null,String.format("%s(%d) 추가완료", newde.getName(), newde.getNo()));
		delist.add(newde);
	}
	
	
	protected void actionPerformedBtnCencel(ActionEvent e) {
		pdepartment.clearTf();
		
	}
	
	class CustomPopupMenu extends JPopupMenu{

		public CustomPopupMenu(ActionListener e) {
			JMenuItem updateMenu = new JMenuItem("수정");
			updateMenu.addActionListener(e);
			add(updateMenu);
			
			JMenuItem deleteMenu = new JMenuItem("삭제");
			deleteMenu.addActionListener(e);
			add(deleteMenu);			
			
			JMenuItem detailMenu = new JMenuItem("세부 정보");
			detailMenu.addActionListener(e);
			add(detailMenu);
		}

		
		
	}
}
