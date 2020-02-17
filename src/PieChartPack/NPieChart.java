<<<<<<< HEAD:src/sortinggg/NPieChart.java
package sortinggg;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NPieChart { // Copied and altered from SortingGG.java

    private static Map<String, NPieChartObj> RootToMap(File root){
        assert root.isDirectory();

        File[] files = root.listFiles();
        Map<String, NPieChartObj> map = new HashMap<>(); // Dictionary of extensions and total space taken by that type in root
        ArrayList<Color> Colours = new ArrayList<Color>();
        Colours.addAll(setCol(files.length));
        int count = 0;
        for (File file : files)
        {
            String[] tokens = file.getName().split("\\.(?=[^\\.]+$)"); // 0: Filename, 1: File extension
            String extension = tokens.length == 1 ? "File" : tokens[1];
            NPieChartObj currentObj = map.get(extension);

            if (file.length() > 0) {
                if (file.isFile() && map.containsKey(extension)) {
                    currentObj.extensionTotalLength = currentObj.extensionTotalLength + file.length();
                    map.replace(extension, currentObj); // Get
                } else {
                    map.put(extension, new NPieChartObj(extension, file.length(), Colours.get(count)));
                }
            }
            count++;
        }

        return map;
    }
  static ArrayList<Color> setCol(int size){
        ArrayList<Color> List = new ArrayList<Color>();
        Color customColor = new Color(21,105,245);
        List.add(customColor);
        customColor = new Color(230,230,30);
        List.add(customColor);
        customColor = new Color(2,161,71);
        List.add(customColor);
        customColor = new Color(151, 51, 151);
        List.add(customColor);
        customColor = new Color(174,163,115);
        List.add(customColor);
        customColor = new Color(166,65,65);
        List.add(customColor);
        customColor = new Color(255,150,30);
        List.add(customColor);
        customColor = new Color(0,64,78);
        List.add(customColor);
        customColor = new Color(74,0,178);
        List.add(customColor);
        for (int i = 0; i < size - 10 ; i++){
            List.add(new Color((int) (Math.random() * ((255 - 0) + 1)) + 0, (int) (Math.random() * ((255 - 0) + 1)) + 0, (int) (Math.random() * ((255 - 0) + 1)) + 0));}
        return List;
    }
  
    public static NPieChartC createPieChartJComponent(File root){
        return new NPieChartC(RootToMap(root));
    }

}
=======
package PieChartPack;

import java.awt.Color;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class NPieChart { // Copied and altered from SortingGG.java

    private static Map<String, NPieChartObj> RootToMap(File root){
        assert root.isDirectory();

        File[] files = root.listFiles();
        Map<String, NPieChartObj> map = new HashMap<>(); // Dictionary of extensions and total space taken by that type in root

        for (File file : files)
        {
            String[] tokens = file.getName().split("\\.(?=[^\\.]+$)"); // 0: Filename, 1: File extension
            String extension = tokens.length == 1 ? "File" : tokens[1];

            NPieChartObj currentObj = map.get(extension);

            if (file.length() > 0) {
                if (file.isFile() && map.containsKey(extension)) {
                    currentObj.extensionTotalLength = currentObj.extensionTotalLength + file.length();
                    map.replace(extension, currentObj); // Get
                } else {
                    map.put(extension, new NPieChartObj(extension, file.length(), new Color((int)(Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256))));
                }
            }
        }

        return map;
    }

    public static NPieChartC createPieChartJComponent(File root){
        return new NPieChartC(RootToMap(root));
    }

}
>>>>>>> master:src/PieChartPack/NPieChart.java
