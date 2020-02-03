package sortinggg;

import javax.swing.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class NPieChart { // Copied and altered from SortingGG.java

    private static Map<String, NPieChartObj> RootToMap(File root){
        assert root.isDirectory();

        File[] files = root.listFiles();
        Map<String, NPieChartObj> map = new HashMap<String, NPieChartObj>(); // Dictionary of extensions and total space taken by that type in root

        for (File file : files)
        {
            String[] tokens = file.getName().split("\\.(?=[^\\.]+$)"); // 0: Filename, 1: File extension
            String extension = tokens.length == 1 ? "unknown" : tokens[1];

            NPieChartObj currentObj = map.get(extension);

            if (file.isFile() && map.containsKey(extension)){
                map.replace(extension, currentObj.setExtentionTotalLength(currentObj.getExtentionTotalLength() + file.length())); // Get
            } else {

                map.put(extension, new NPieChartObj(extension, file.length()));
            }
        }

        return map;
    }

    public static NPieChartC createPieChartJComponent(File root){
        return new NPieChartC(RootToMap(root));
    }

}
