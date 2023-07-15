package gr.aueb.cf.schoolapp.viewcontroller;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.IStudentDAO;
import gr.aueb.cf.schoolapp.dao.StudentDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp.dto.StudentInsertDTO;
import gr.aueb.cf.schoolapp.model.Student;
import gr.aueb.cf.schoolapp.service.IStudentService;
import gr.aueb.cf.schoolapp.service.StudentServiceImpl;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StudentsInsertForm extends JFrame {
    private static final long serialVersionUID = 123456;
    private JPanel contentPane;
    private JTextField firstnameTxt;
    private JTextField lastnameTxt;

    IStudentDAO dao = new StudentDAOImpl();
    IStudentService studentService = new StudentServiceImpl(dao);

    public StudentsInsertForm() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                firstnameTxt.setText("");
                lastnameTxt.setText("");
            }
        });
        setTitle("Εισαγωγή Εκπαιδευόμενου");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 405, 280);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel firstnameLbl = new JLabel("Όνομα");
        firstnameLbl.setForeground(new Color(128, 0, 0));
        firstnameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
        firstnameLbl.setBounds(57, 57, 69, 25);
        contentPane.add(firstnameLbl);

        firstnameTxt = new JTextField();
        firstnameTxt.setBounds(131, 61, 143, 20);
        contentPane.add(firstnameTxt);
        firstnameTxt.setColumns(10);

        JLabel lastnameLbl = new JLabel("Επώνυμο");
        lastnameLbl.setForeground(new Color(128, 0, 0));
        lastnameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
        lastnameLbl.setBounds(57, 90, 69, 25);
        contentPane.add(lastnameLbl);

        lastnameTxt = new JTextField();
        lastnameTxt.setColumns(10);
        lastnameTxt.setBounds(131, 94, 143, 20);
        contentPane.add(lastnameTxt);

        JPanel panel = new JPanel();
        panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel.setBounds(24, 11, 325, 156);
        contentPane.add(panel);

        JButton insertBtn = new JButton("Εισαγωγή");
        insertBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstname = firstnameTxt.getText().trim();
                String lastname = lastnameTxt.getText().trim();

                if (firstname.equals("") || lastname.equals("")) {
                    JOptionPane.showMessageDialog(null, "Not valid input", "INSERT ERROR", JOptionPane.ERROR_MESSAGE);
                }

                try {
                    StudentInsertDTO dto = new StudentInsertDTO();
                    dto.setFirstname(firstname);
                    dto.setLastname(lastname);

                    Student student = studentService.insertStudent(dto);


                    JOptionPane.showMessageDialog(null, "Student " + student.getLastname()
                            + " was inserted", "INSERT", JOptionPane.PLAIN_MESSAGE);
                } catch (StudentDAOException e1) {
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        insertBtn.setForeground(new Color(0, 0, 255));
        insertBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        insertBtn.setBounds(99, 191, 129, 41);
        contentPane.add(insertBtn);

        JButton closeBtn = new JButton("Κλείσιμο");
        closeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getStudentsSearchForm().setEnabled(true);
                Main.getStudentsInsertForm().setVisible(false);
            }
        });
        closeBtn.setForeground(Color.BLUE);
        closeBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        closeBtn.setBounds(226, 191, 129, 41);
        contentPane.add(closeBtn);
    }

}
