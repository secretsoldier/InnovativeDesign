package PieChartPack;

import java.awt.Color;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class PieChart { // Copied and altered from SortingGG.java

    private static Map<String, PieChartObj> RootToMap(File root){ // Converts the directory into data that the chart can process
        assert root.isDirectory(); // Makes sure that the file given is a directory

        File[] files = root.listFiles(); // Lists all the files in "root" into the array "files"
        Map<String, PieChartObj> map = new HashMap<>(); // Dictionary of extensions and total space taken by that type in "root"

        for (File file : files) // Loop through the list of files in the directory "root"
        {
            String[] tokens = file.getName().split("\\.(?=[^\\.]+$)"); // 0: Filename, 1: File extension
            String extension = tokens.length == 1 ? "File" : tokens[1]; // Gets file extension from the name, if it doesn't have an extension then it will have the extension "File".
            // "extension" will act as the key for "map"

            PieChartObj currentObj = map.get(extension); // Gets the extension object data from the extension given

            if (file.length() > 0) { // If file size is larger than 0
                if (file.isFile() && map.containsKey(extension)) { // If file is a file and is present in the extension map
                    currentObj.extensionTotalLength = currentObj.extensionTotalLength + file.length(); // Adds the size of the file onto the sum of the file extension
                    map.replace(extension, currentObj); // Updates the extension data in the map
                } else {
                    PieChartObj newExtensionData = new PieChartObj(extension, file.length(), new Color((int)(Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256)));
                    map.put(extension, newExtensionData);
                }
            }
        }

        return map; // returns the map of the extension data in "root"
    }

    public static PieChartC createPieChartJComponent(File root){
        return new PieChartC(RootToMap(root)); // Returns the Pie Chart Component from a File parameter
    }

} // 18074751
