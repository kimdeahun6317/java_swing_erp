package java_swing_erp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java_swing_erp.ui.component.Departmentmanagement2;
import java_swing_erp.ui.component.StudentManagement;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class erp extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnStudent;
	private JButton btnDepartment;
	private StudentManagement stdFrame;
	private Departmentmanagement2 deptFarme;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					erp frame = new erp();
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
	public erp() {
		initComponents();
	}
	private void initComponents() {
		setTitle("학사 관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 361, 105);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		btnStudent = new JButton("학생 관리");
		btnStudent.addActionListener(this);
		contentPane.add(btnStudent);
		
		btnDepartment = new JButton("학과 관리");
		btnDepartment.addActionListener(this);
		contentPane.add(btnDepartment);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnDepartment) {
			actionPerformedBtnDepartment(e);
		}
		if (e.getSource() == btnStudent) {
			actionPerformedBtnStudent(e);
		}
	}
	protected void actionPerformedBtnStudent(ActionEvent e) {
		if(stdFrame == null) {
		stdFrame = new StudentManagement();
		}
		stdFrame.setVisible(true);
	}
	protected void actionPerformedBtnDepartment(ActionEvent e) {
		if(deptFarme == null) {
		deptFarme = new Departmentmanagement2();
		}
		deptFarme.setVisible(true);
	}
}
