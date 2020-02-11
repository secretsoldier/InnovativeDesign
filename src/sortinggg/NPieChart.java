package sortinggg;

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
                    currentObj.extentionTotalLength = currentObj.extentionTotalLength + file.length();
                    map.replace(extension, currentObj); // Get
                } else {
                    map.put(extension, new NPieChartObj(extension, file.length()));
                }
            }
        }

        return map;
    }

    public static NPieChartC createPieChartJComponent(File root){
        return new NPieChartC(RootToMap(root));
    }

}
