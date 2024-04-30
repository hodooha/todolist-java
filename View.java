import model.Task;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.BorderLayout.*;

public class View {
    private JFrame f;
    private JPanel contentP;
    private JPanel taskP = new JPanel();
    private JButton addBtn;
    private JButton completeBtn;
    private JButton deleteBtn;
    private JTextField addField;
    private JLabel taskField;
    private Controller con = new Controller();
    private Font font = new Font("굴림", Font.BOLD, 30);

    public View() {
        f = new JFrame();
        f.setSize(700, 800);
        f.getContentPane().setBackground(Color.gray);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("TodoList");
        initHeadPanel();
        initContentPanel();
        f.setVisible(true);
        addField.setFocusable(true);
    }

    private void initHeadPanel() { // 상단 task 추가 영역
        JPanel headP = new JPanel();
        headP.setLayout(new FlowLayout());
        headP.setBackground(Color.yellow);

        JLabel addLabel = new JLabel("할일: ");
        addLabel.setFont(font);

        addField = new JTextField(15);
        addField.setFont(font);

        addBtn = new JButton("추가");
        addBtn.setBackground(Color.green);
        addBtn.setFont(font);
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String content = addField.getText();
                if (content.length() != 0) { // 내용이 없으면 추가하지 않음
                    if (con.findTask(content) == null) { // 기존 taskList에 중복이 있는지 확인
                        Task task = con.addTask(content); // 중복 없으면 DB에 task 추가 후 Task 타입으로 리턴받음.
                        initTaskPanel(task); // 추가한 Task 타입의 task 전달
                    } else {
                        JOptionPane.showMessageDialog(f, "이미 존재합니다.");
                    }
                    addField.setText(""); // 추가 후 input창 초기화
                }
            }
        });
        headP.add(addLabel);
        headP.add(addField);
        headP.add(addBtn);
        f.add(headP, PAGE_START);
    }

    private void initContentPanel() {
        contentP = new JPanel();
        contentP.setBackground(Color.pink);
        f.add(contentP);
    }

    private void initTaskPanel(Task task) {
        JPanel flow = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5)); // 개별 task 영역
        flow.setBackground(Color.white);
        taskP.setLayout(new GridLayout(0, 1)); // 복수의 tasks 영역
        taskP.setBackground(Color.white);
        taskField = new JLabel();
        taskField.setText(task.getContent()); // 매개변수 task 내용 조회하여 보여주기
        taskField.setFont(font);
        completeBtn = new JButton("완료");
        completeBtn.setBackground(Color.CYAN);
        completeBtn.setFont(font);
        completeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                con.taskCheck(task); // DB에서 task의 완료여부 변경
                if (task.getIsComplete()) {
                    flow.setBackground(Color.gray); // task 완료되었으면 회색
                    f.repaint();
                    f.revalidate();
                } else {
                    flow.setBackground(Color.white); // task 완료 안되었으면 기본색(흰색)
                    f.repaint();
                    f.revalidate();
                }
            }
        });
        deleteBtn = new JButton("삭제");
        deleteBtn.setBackground(Color.red);
        deleteBtn.setFont(font);
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                con.deleteTask(task); // DB에서 task 삭제
                taskP.remove(flow);
                f.repaint();
                f.revalidate();
            }
        });
        flow.add(taskField);
        flow.add(completeBtn);
        flow.add(deleteBtn);
        taskP.add(flow);
        contentP.add(taskP);
        f.repaint();
        f.revalidate();
    }
}



