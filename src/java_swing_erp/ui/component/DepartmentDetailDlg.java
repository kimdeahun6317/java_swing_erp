package java_swing_erp.ui.component;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import java_swing_erp.dto.Department;
import java_swing_erp.ui.component.contant.StudentPanel;
import java_swing_erp.ui.component.contant.DepartmentPanel;

@SuppressWarnings("serial")
public class DepartmentDetailDlg extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private DepartmentPanel pstudent;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			StudentDetailDlg dialog = new StudentDetailDlg();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	
	
	public DepartmentDetailDlg() {
		initComponents();
	}
	
	public void setDepartment(Department department) {
		pstudent.setItem(department);
//		
//		JLabel lblTotal = new JLabel("총점");
//		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
//		pstudent.add(lblTotal);
//		
//		JLabel lblValue = new JLabel(String.valueOf(department.getTotal()));
//		lblValue.setHorizontalAlignment(SwingConstants.LEFT);
//		pstudent.add(lblValue);
//		
//		JLabel lblAvg = new JLabel("평균");
//		lblAvg.setHorizontalAlignment(SwingConstants.RIGHT);
//		pstudent.add(lblAvg);
//		
//		JLabel lblValue1 = new JLabel(String.valueOf(department.getAverage()));
//		lblValue1.setHorizontalAlignment(SwingConstants.LEFT);
//		pstudent.add(lblValue1);
		
	}
	
	public void setTfNotEditable() {
		pstudent.setTfNotEditable(false);
	}
	
	private void initComponents() {
		setTitle("학과 세부 정보");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "\uD559\uC0DD\uC815\uBCF4", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			pstudent = new DepartmentPanel();
			contentPanel.add(pstudent, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("확인");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
