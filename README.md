# Faces
Algorithm for face detection and recognition(implementing Eigen Faces) using OpenCV and JavaCV.

# Setting Up Face Detection and Capturing

download zip or clone this repository from github.

### lets create the project

##### Part 1.

-> Lets use netbeans so open netbeans.

-> Create a new project and give it a name. Uncheck the option to create a main class, as we        won't be needing it.

-> Create a new package and name it myPackage.

-> Create a new jFrame form inside the myPackage and call it Navigator.

-> Design it the way you like. But make sure to include two buttons:
    - one for invoking face registration and the other for invoking face recognition, so create    two butoons and name them register and recognise.

-> Build and run your project and set the Navigater form as the main form in popup dialog that      comes up before compilation.

##### Part 2.

-> From the downloaded zip file you cloned or downloaded, copy the facials folder and the media     folder and paste them in your project src folder.
    - It will be next to the myPackage folder you created earlier.

-> You will notice some errors. To resolve them you will need to add some libraries from the        library folder.
    - In netbeans under your project, right click on libraries and add JAR/folder, and navigate    to the library folder and choose the library called: javacv.jar and javacpp.jar.

-> Go to the Navigator form and go to source. Add this import line:
    import facials.Facetracker;

-> Go to design on that same form and double click on register button to write the following        code:
        new FaceTracker().show(true);

-> Save all. Build and run the project.

#### lets save your name.

##### Part 3.

-> On the Navigator form on top of the register button, add a textfield and name it txtName.

-> Go to source and add the import line:
    import facials.FacePanel;

-> Add a line of code on the register button so that it looks like the following:
    FacePanel.FACE_FNM = txtName.getText();
    new FaceTracker().show(true);

-> Run the project. Type your name and save your pictures.

## Lets recognise the faces.