package com.mycompany.libraryprogram;

/**
 *
 * @author Student
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelMemberGUI {

    public static void main(String[] args) {

        // Create Frame
        JFrame frame = new JFrame("Hotel Reservation System - Add Member");
        frame.setSize(400, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Labels
        JLabel lblName = new JLabel("Guest Name:");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblPhone = new JLabel("Phone:");

        // Text Fields
        JTextField txtName = new JTextField(20);
        JTextField txtEmail = new JTextField(20);
        JTextField txtPhone = new JTextField(20);

        // Button
        JButton btnAdd = new JButton("Add Member");

        // Text Area to display members
        JTextArea displayArea = new JTextArea(8, 30);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Button Action
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtName.getText();
                String email = txtEmail.getText();
                String phone = txtPhone.getText();

                if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            "Please fill all fields",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    displayArea.append("Guest: " + name +
                            " | Email: " + email +
                            " | Phone: " + phone + "\n");

                    // Clear fields
                    txtName.setText("");
                    txtEmail.setText("");
                    txtPhone.setText("");
                }
            }
        });

        // Add components to frame
        frame.add(lblName);
        frame.add(txtName);
        frame.add(lblEmail);
        frame.add(txtEmail);
        frame.add(lblPhone);
        frame.add(txtPhone);
        frame.add(btnAdd);
        frame.add(scrollPane);

        // Make frame visible
        frame.setVisible(true);
    }
}

