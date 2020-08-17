package java_swing_erp.ui.component.contant;

import javax.swing.JPanel;

import java_swing_erp.dto.Department;
import java_swing_erp.ui.exception.EmptyTfException;
import java_swing_erp.ui.exception.InValidTfValue;

import java.awt.GridLayout;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class DepartmentPanel extends AbstractItemPanel<Department> {
	private JLabel lblNo;
	private JTextField tfNo;
	private JLabel lblName;
	private JTextField tfName;
	private JLabel lblTel;
	private JTextField tfTel;

	
	public DepartmentPanel() {

		initComponents();
	}
	private void initComponents() {
		setLayout(new GridLayout(0, 2, 20, 10));
		
		lblNo = new JLabel("학과 번호");
		add(lblNo);
		
		tfNo = new JTextField();
		add(tfNo);
		tfNo.setColumns(10);
		
		lblName = new JLabel("학과명");
		add(lblName);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		add(tfName);
		
		lblTel = new JLabel("연락처");
		add(lblTel);
		
		tfTel = new JTextField();
		tfTel.setColumns(10);
		add(tfTel);
	}
	@Override
	public void setItem(Department item) {
		tfNo.setText(String.valueOf(item.getNo()));
		tfName.setText(item.getName());
		tfTel.setText(String.valueOf(item.getTel()));
		
	}
	@Override
	public Department getItem() {
		if(isTfEmpty()) {
			//textfield가 하나라도 비어있으면
			throw new EmptyTfException("공란이 존재");
		}
		if(!isValidTf()) {
			//국어 영어 수학이 100점 초과면 
			throw new InValidTfValue("학과 번호는 숫자만, 학과명은 한글만, 연락처는 000-000(0)-0000만가능");
		}
		int no = Integer.parseInt(tfNo.getText().trim());
		String name = tfName.getText().trim();
		String tel = tfTel.getText().trim();
		return new Department(no,name,tel);
	}
	@Override
	boolean isValidTf() {
		//1. 정규표현식
		//no 숫자만 가능
		//name은 한글만
		//tel 세자리숫자 ~ 세(네)자리 숫자 ~ 네자리숫자
		String no = tfNo.getText().trim();
		String name = tfName.getText().trim();
		String tel = tfTel.getText().trim();
		
		//정규표현식
		boolean noCheck =  Pattern.matches("\\d{1,3}", no);
		boolean nameCheck = Pattern.matches("^[가-힣]+$", name);
		boolean telCheck = Pattern.matches("\\d{3}-\\d{3,4}-\\d{4}", tel);
		System.out.println(noCheck+ "+" +nameCheck+ "+" + telCheck+ "+" + tel);
		return noCheck && nameCheck && telCheck;
	}
	@Override
	public void setEditableNoTf(boolean isEditable) {
		tfNo.setEditable(isEditable);
		
	}

}
