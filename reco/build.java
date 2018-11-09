/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reco;

import javax.swing.JOptionPane;

/**
 *
 * @author Chriss
 */
public class build {
    public static void main(String args[]){
        buildfacesnow();
    }

    public static void buildfacesnow() {
    
    int numEFs = 0;
    
    long startTime = System.currentTimeMillis();
    BuildEigenFaces.build(numEFs);
    JOptionPane.showMessageDialog(null, "Face Re-structuring Done!..");
        
    System.out.println("Total time taken: " + 
                       (System.currentTimeMillis() - startTime) + " ms");
    }
}
