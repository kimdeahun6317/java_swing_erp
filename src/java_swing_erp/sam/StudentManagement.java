package java_swing_erp.ui.component;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java_swing_erp.dto.Student;
import java_swing_erp.io.JsonReadThread;
import java_swing_erp.io.JsonWriteThread;
import java_swing_erp.ui.component.content.StudentPanel;
import java_swing_erp.ui.component.table.StudentTable;

@SuppressWarnings("serial")
public class StudentManagement extends JFrame implements ActionListener{

    private JPanel contentPane;
    private StudentPanel pStudent;
    private JPanel pBtns;
    private JPanel pTable;
    private JButton btnAdd;
    private JButton btnCancel;
    private JScrollPane scrollPane;
    private StudentTable table;
    private ArrayList<Student> stdList = new ArrayList<Student>();
    private JMenuBar menuBar;
    private JMenu mnFile;
    private JMenuItem mntmSave;
    private JMenuItem mntmLoad;
    private JMenuItem mntmClose;
    private JSeparator separator;

    private String dataFile;
    
    private JFileChooser chooser = new JFileChooser();
    
    private JsonWriteThread writeTh = new JsonWriteThread();
    private JsonReadThread readTh = new JsonReadThread();

    public StudentManagement() {
        /*        stdList.add(new Student(1, "김대훈", 90, 80, 71));
        stdList.add(new Student(2, "이현석", 91, 81, 100));
        stdList.add(new Student(3, "윤원주", 92, 82, 90));
        stdList.add(new Student(4, "배성덕", 93, 83, 60));*/
        
        initComponents();
        
        Student std = new Student(1, "김대훈", 90, 70, 60);
        pStudent.setItem(std);
        
        
        
        table.setItems(stdList);
    }

    private void initComponents() {
        setTitle("학생 관리");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 600);
        
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        mnFile = new JMenu("파일");
        menuBar.add(mnFile);
        
        mntmLoad = new JMenuItem("불러오기");
        mntmLoad.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                String curdir = System.getProperty("user.dir");
                chooser.setCurrentDirectory(new File(curdir));
                
                FileNameExtensionFilter filter = new FileNameExtensionFilter("백업 파일", "data");
                chooser.setFileFilter(filter);
                
                int res = chooser.showOpenDialog(null);
                if (res == JFileChooser.APPROVE_OPTION) {
                    dataFile = chooser.getSelectedFile().getPath();
                    
                    readTh.setFileName(dataFile);
                    readTh.start();
                    try {
                        readTh.join();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    stdList = (ArrayList<Student>) readTh.getList();
                    table.setItems(stdList);
                }
            }
        });
        mnFile.add(mntmLoad);
        
        mntmSave = new JMenuItem("저장하기");
        mntmSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String curdir = System.getProperty("user.dir");
                chooser.setCurrentDirectory(new File(curdir));
                
                int res = chooser.showSaveDialog(null);
                if (res == JFileChooser.APPROVE_OPTION) {
                    dataFile = chooser.getSelectedFile().getPath();
                    
                    writeTh.setFilePath(dataFile);
                    writeTh.setList(stdList);
                    writeTh.start();
                    try {
                        writeTh.join();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "저장 완료");
                }
            }
        });
        mnFile.add(mntmSave);
        
        separator = new JSeparator();
        mnFile.add(separator);
        
        mntmClose = new JMenuItem("닫기");
        mntmClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int res = JOptionPane.showConfirmDialog(null, "종료하시겠습니까?", "닫기", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.WARNING_MESSAGE);
                if (res == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });
 
        mnFile.add(mntmClose);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        
        pStudent = new StudentPanel();
        contentPane.add(pStudent);
        
        pBtns = new JPanel();
        contentPane.add(pBtns);
        
        btnAdd = new JButton("추가");
        btnAdd.addActionListener(this);
        pBtns.add(btnAdd);
        
        btnCancel = new JButton("취소");
        btnCancel.addActionListener(this);
        pBtns.add(btnCancel);
        
        pTable = new JPanel();
        contentPane.add(pTable);
        pTable.setLayout(new BorderLayout(0, 0));
        
        scrollPane = new JScrollPane();
        pTable.add(scrollPane, BorderLayout.CENTER);
        
        table = new StudentTable();
        //popupmenu 장착
        
        CustomPopupMenu popMenu = new CustomPopupMenu(this);
        table.setComponentPopupMenu(popMenu);
        scrollPane.setViewportView(table);
    }

    public void actionPerformed(ActionEvent e) {
//        System.out.println(e);
        if (e.getSource() instanceof JButton) {
            if (e.getSource() == btnCancel) {
                actionPerformedBtnCancel(e);
            }
            if (e.getSource() == btnAdd) {
                if (e.getActionCommand().equals("추가")) {
                    actionPerformedBtnAdd(e);
                }else {
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
        //1. StudentPanel에서 수정된 학생정보를 가져옴
        //2. stdList에서 학생정보 수정
        //3. studentTable에서 학생정보 수정
        //4. clearTf()
        //5. studentPanel setEditableTf(true)
        //6. btnAdd 텍스를 추가로 변경
        //7. message()
        Student updatedStd = pStudent.getItem();
        int idx = stdList.indexOf(updatedStd);
        stdList.set(idx, updatedStd);
        table.updateRow(idx, updatedStd);
        pStudent.clearTf();
        pStudent.setEditableNoTf(true);
        btnAdd.setText("추가");
        JOptionPane.showMessageDialog(null, String.format("%s(%d) 수정 완료!!", updatedStd.getName(), updatedStd.getNo()));
    }

    private void actionPerformedMenuUpdate() {
        System.out.println("수정~~~");
        int selIdx = table.getSelectedRow();
        if (selIdx == -1) {
            JOptionPane.showMessageDialog(null, "해당 항목을 선택하세요.");
            return;
        }
        Student selStd = stdList.get(selIdx);
        pStudent.setItem(selStd);
        //1. 버튼의 텍스를 수정으로 변경
        //2. pStudent 학번은 변경 불가능하게..
        btnAdd.setText("수정");
        pStudent.setEditableNoTf(false);
    }

    private void actionPerformedMenuDelete() {
//        System.out.println("삭제~~~");  
        int selIdx = table.getSelectedRow();
        if (selIdx == -1) {
            JOptionPane.showMessageDialog(null, "해당 항목을 선택하세요.");
            return;
        }
        Student deletedStd = stdList.remove(selIdx);
        table.removeRow(selIdx);
        JOptionPane.showMessageDialog(null, String.format("%s(%d) 삭제 완료!!", deletedStd.getName(), deletedStd.getNo()));
    }

    private void actionPerformedMenuDetail() {
        System.out.println("세부 정보~~~");    
        int selIdx = table.getSelectedRow();
        if (selIdx == -1) {
            JOptionPane.showMessageDialog(null, "해당 항목을 선택하세요.");
            return;
        }
        Student detailStd = stdList.get(selIdx);
        System.out.println(detailStd);
        StudentDetailDlg dlg = new StudentDetailDlg();
        dlg.setStudent(detailStd);
        dlg.setTfNotEditable();
        dlg.setVisible(true);
    }

    protected void actionPerformedBtnAdd(ActionEvent e) {
        //1. StudentPanel에서 getStudent()
        //2. StudentTable addRow();
        //3. StudentPanel clearTf();
        //4. Message();
        Student newStd = pStudent.getItem();
//        System.out.println(newStd);
        table.addRow(newStd);
        pStudent.clearTf();
        JOptionPane.showMessageDialog(null, String.format("%s(%d) 추가 완료!!", newStd.getName(), newStd.getNo()));
        stdList.add(newStd);
    }
    
    protected void actionPerformedBtnCancel(ActionEvent e) {
        pStudent.clearTf();
        if (btnAdd.getText().equals("수정")) {
            btnAdd.setText("추가");
            pStudent.setEditableNoTf(true);
            table.clearSelection();
        }
    }
}





