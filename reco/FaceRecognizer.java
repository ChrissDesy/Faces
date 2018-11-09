package reco;
// FaceRecognizer.java
// Andrew Davison, April 2011, ad@fivedots.psu.ac.th

/* Show a sequence of images snapped from a webcam in a picture panel (FaceRecogPanel). 
   A face is highlighted with a yellow rectangle, which is updated as the face
   moves. The highlighted part of the image can be recognized by the user pressing
   the "Recognize Face" button.

   Usage:
      > java FaceRecognizer
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

import com.googlecode.javacv.*;
import com.googlecode.javacv.cpp.*;
import com.googlecode.javacpp.Loader;
import src_code.controller;
import src_code.employee;
import src_code.main;

public class FaceRecognizer extends JFrame {

    // GUI components
    private FaceRecogPanel facePanel;
    private JLabel labl;
    private JButton recogBut, canc, logi;
    private JTextField nameField, namField;   // where the name (and distance info) appears

    public FaceRecognizer() {
        super("Employee Face Recognition");

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        // Preload the opencv_objdetect module to work around a known bug.
        Loader.load(opencv_objdetect.class);

        facePanel = new FaceRecogPanel(this); // the sequence of pictures appear here
        c.add(facePanel, BorderLayout.CENTER);

        //Label
        labl = new JLabel("Enter Employee ID to verify..");
        labl.setVisible(false);

        // button for recognizing a highlighted face
        recogBut = new JButton("Recognize Face");
        recogBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //nameField.setText("");
                //recogBut.setEnabled(false);
                facePanel.setRecog();
            }
        });

        //verify button..
        logi = new JButton("Verify");
        logi.setVisible(false);
        logi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.setemid(namField.getText());
                if (nameField.getText().length() != 4) {
                    JOptionPane.showMessageDialog(null, "CHECK YOUR EMPLOYEE ID","ERROR",0);
                    

                } else if (namField.getText().equals(nameField.getText())) {
                    new employee().show(true);
                    facePanel.closeDown();    // stop snapping pics
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "YOU ARE NOT WHO YOU SAY YOU ARE","ERROR",0);
                }
            }
        });

        //cancel button
        canc = new JButton("Cancel");
        canc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                facePanel.closeDown();    // stop snapping pics
                dispose();
                try{new main().show(true);}
                catch(Exception ex){
                    
                }
            }
        });

        nameField = new JTextField(20);   // for verifying id
        nameField.setVisible(false);
        namField = new JTextField(20);   // for the name of the recognized face
        namField.setVisible(false);

        JPanel p = new JPanel();
        p.add(labl);
        p.add(recogBut);
        p.add(namField);
        p.add(nameField);
        p.add(logi);
        p.add(canc);
        c.add(p, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                facePanel.closeDown();    // stop snapping pics
                dispose();
            }
        });

        pack();
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
    } // end of FaceRecognizer()

    public void setRecogName(final String faceName, final String dist) // update face name and its distance in the nameField; called from panel
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                namField.setText(faceName);
                recogBut.setVisible(false);
                labl.setVisible(true);
                //namField.setVisible(true);
                nameField.setVisible(true);
                logi.setVisible(true);

            }
        });
    }  // end of setRecogName()
    
    public void vhara(){
        facePanel.closeDown();
        dispose();
    }

    // -------------------------------------------------------
    public static void main(String args[]) {
        new FaceRecognizer();
    }

} // end of FaceRecognizer class
