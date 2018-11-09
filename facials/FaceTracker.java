package facials;
// FaceTracker.java
// Andrew Davison, July 2013, ad@fivedots.psu.ac.th

/* Show a sequence of images snapped from a webcam in a picture panel (FacePanel). 
   A face is highlighted with a yellow rectangle, which is updated as the face
   moves. The highlighted part of the image can be saved by the user pressing
   the "Save Face" button.

   Usage:
      > java FaceTracker
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.googlecode.javacv.cpp.*;
import com.googlecode.javacpp.Loader;


public class FaceTracker extends JFrame 
{
  // GUI components
  private FacePanel facePanel;
  int coun = 0;
  JLabel left;
  JButton canc;


  public FaceTracker()
  {
    super("Save Employee Face");

    Container c = getContentPane();
    c.setLayout( new BorderLayout() );   

    // Preload the opencv_objdetect module to work around a known bug.
    Loader.load(opencv_objdetect.class);

    facePanel = new FacePanel(); // the sequence of pictures appear here
    c.add( facePanel, BorderLayout.CENTER);

    // button for saving a highlighted face
    JButton saveBut = new JButton("Save Face");
    saveBut.addActionListener( new ActionListener() {
       public void actionPerformed(ActionEvent e)
       { 
           facePanel.saveFace();
           coun++;
           if(coun == 1 || coun == 2 || coun == 3){
            left.setText(Integer.toString(4-coun)+" Remaining");
           }
           if(coun == 4){
             left.setText("Successfully Saved");
             canc.setVisible(true);
             saveBut.setVisible(false);
           }
       }
    });
    
    // button for closing frame
    canc = new JButton("Close");
    canc.setVisible(false);
    canc.addActionListener( new ActionListener() {
       public void actionPerformed(ActionEvent e)
       { facePanel.closeDown();
         dispose();  }
    });
    
    //Label to tell pictures left
        left = new JLabel("Save 4 Pictures ");

    JPanel p = new JPanel();
    p.add(left);
    p.add(saveBut);
    p.add(canc);
    c.add(p, BorderLayout.SOUTH);
    

    addWindowListener( new WindowAdapter() {
      public void windowClosing(WindowEvent e)
      { facePanel.closeDown();    // stop snapping pics
        //System.exit(0);
      }
    });

    setResizable(false);
    pack();  
    setLocationRelativeTo(null);
    setVisible(true);
  } // end of FaceTracker()


  // -------------------------------------------------------

  public static void main( String args[] )
  {  new FaceTracker();  }

} // end of FaceTracker class