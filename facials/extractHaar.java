/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facials;

import java.io.*;
import java.util.Hashtable;

public class extractHaar {

    // Stores paths to files with the global jarFilePath as the key
    private static Hashtable<String, String> fileCache = new Hashtable<String, String>();

    /**
     * Extract the specified resource from inside the jar to the local file system.
     * @param jarFilePath absolute path to the resource
     * @return full file system path if file successfully extracted, else null on error
     */
    public static String extract(String jarFilePath){

        if(jarFilePath == null)
        { System.out.println("error in filepath..1");
            }

        // See if we already have the file
        if(fileCache.contains(jarFilePath)){
            System.out.println("File already available");
            return fileCache.get(jarFilePath);}

        // Alright, we don't have the file, let's extract it
        try {
            // Read the file we're looking for
            InputStream fileStream = extractHaar.class.getResourceAsStream(jarFilePath);

            // Was the resource found?
            if(fileStream == null)
                System.out.println("Resource not found");

            // Grab the file name
            String[] chopped = jarFilePath.split("\\/");
            String fileName = chopped[chopped.length-1];

            // Create our temp file (first param is just random bits)
            File tempFile = File.createTempFile("asdf", fileName);

            // Set this file to be deleted on VM exit
            tempFile.deleteOnExit();

            // Create an output stream to barf to the temp file
            OutputStream out = new FileOutputStream(tempFile);

            // Write the file to the temp file
            byte[] buffer = new byte[1024];
            int len = fileStream.read(buffer);
            while (len != -1) {
                out.write(buffer, 0, len);
                len = fileStream.read(buffer);
            }

            // Store this file in the cache list
            fileCache.put(jarFilePath, tempFile.getAbsolutePath());

            // Close the streams
            fileStream.close();
            out.close();

            // Return the path of this sweet new file
            return tempFile.getAbsolutePath();

        } catch (IOException e) {
            System.out.println("Error in copying file");
            return null;
        }
    }
}
