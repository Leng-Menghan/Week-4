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
        if(admin.adminLogin()) admin.AdminFeatures();
    }
}
