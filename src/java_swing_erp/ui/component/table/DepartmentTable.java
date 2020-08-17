package java_swing_erp.ui.component.table;

import javax.swing.SwingConstants;

import java_swing_erp.dto.Department;

@SuppressWarnings("serial")
public class DepartmentTable extends AbstractItemTable<Department> {

	@Override
	Object[] getColName() {

		return new String[] { "학과 번호", "학과명", "연락처" };
	}

	@Override
	Object[] toArray(Department item) {

		return new Object[] { item.getNo(), item.getName(), item.getTel() };
	}

	@Override
	void setWidthAndAlign() {
		tableSetWidth(150, 200, 100);
		//column alingnment
		tableCellAlign(SwingConstants.CENTER, 0, 1, 2);

	}

}
