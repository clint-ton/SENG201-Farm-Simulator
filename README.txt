-----Farm Simulator-----

Farm Simulator is a farm management game where you can buy and manage crops and animals, over a limited time frame.


-----Installation-----

To run the game using the JAR file,

1. Ensure Java 13 is installed on the machine
2. Open the zip folder in a terminal window
3. Execute the command "java -jar usercode1_usercode2_SpaceExplorer.jar"


To compile the game from source code, 

1. Open a terminal in the game directory
2. Execute the command javac src/main/*.java
3. Change into the /src directory
4. Execute java main.Game to launch


To import the source code into Eclipse,

1. Create a new project in Eclipse
2. Right click on the "src" folder for the new project and click "Import"
3. Select "File System" under "General" 
4. Browse for the game directory in the downloaded zip archive and select the "src" folder to import
5. Check the box next to the folder and click "Finish"
6. Running the "Game" class in Eclipse will launch the game
7. If errors are displayed in the "test" package, you need to add JUnit5 to the build path


To add JUnit to the buildpath,

1. Right-click the on the project folder and click "Properties"
2. Go to "Java Build Path" and then "Libraries"
3. Select "Classpath" and then "Add Library.."
4. Select JUnit and then JUnit5 in the dropdown
5. Click "Finish"
