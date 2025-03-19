package Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        Admin admin = new Admin();
        Librarian librarian = new Librarian();
        Student student = new Student();
        //admin.manageAllUser();
        //librarian.manageStudent();

        //librarian.manageBook();
        //librarian.manageBorrow();

        if(student.userLogin("S")) student.StudentFeatures();
    }
}
