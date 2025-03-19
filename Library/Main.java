package Library;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        Admin admin = new Admin();
        Librarian librarian = new Librarian();
        Student student = new Student();
        JFrame frame = GUI.createFrame("Library", 500, 300);
        GUI.createTitle(frame, 0, 10, 500, "Welcome to Library Systems");
        JPanel panelButton = GUI.createInputPanel(frame, 0, 60, 500, 650);
        JButton button1 = GUI.createButton("Admin", 150, 0, 200, 40, panelButton);
        JButton button2 = GUI.createButton("Librarian", 150, 50, 200, 40, panelButton);
        JButton button3 = GUI.createButton("Student", 150, 100, 200, 40, panelButton);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(admin.adminLogin()){
                    admin.AdminFeatures();
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(librarian.userLogin("L")){

                    librarian.LibrarianFeatures();
                }
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(student.userLogin("S")){

                    student.StudentFeatures();
                }
            }
        });

    }
}
