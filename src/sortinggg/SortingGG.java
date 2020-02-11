

package sortinggg;

import java.io.IOException;
import java.io.File;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author 17007976
 */
public class SortingGG {

    /**
     * @param args the command line arguments
     */  
 
    
    //public static void main(String[] args) throws IOException {
        /*
    Path file = Paths.get(URI.create("file:///H:/Quality/tester.docx"));
    BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
    FileTime Sam =  attr.creationTime();
    Date newDate = new Date( Sam.toMillis() );
    String pattern = "dd-MM-yyyy HH:mm:ss";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    System.out.println(simpleDateFormat.format(newDate));
    */
 /*
      final File folder = new File("/home/you/Desktop");
    BasicFileAttributes attr = Files.readAttributes(File, BasicFileAttributes.class);
    FileTime Sam =  attr.creationTime();
    Date newDate = new Date( Sam.toMillis() );
    String pattern = "dd-MM-yyyy HH:mm:ss";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    System.out.println(simpleDateFormat.format(newDate));
*/
 
 


    public static void main(String[] args) throws IOException
    {
        File folder = new File("/H:/");
        File[] files = folder.listFiles();
        String types = null;
        int total = 0;
        Map<String, Long> map = new HashMap<String, Long>();
        for (File file : files)
        {
            String[] tokens = file.getName().split("\\.(?=[^\\.]+$)"); // 0: Filename, 1: File extension
            String extension = tokens.length == 1 ? "unknown" : tokens[1];

            System.out.printf("%s %dkb \n",extension, file.length()/1024);
            System.out.println(extension);
            if (file.isFile() && map.containsKey(extension)){
                map.replace(extension, (map.get(extension) + file.length())); // Get
            } else {
                map.put(extension, file.length());
            }
        }
        System.out.println("test map");
        System.out.println(map);
        System.out.println("Print map");
        map.entrySet().forEach(entry->{
            System.out.println(entry.getKey() + " " + entry.getValue());
        });
        FillArc.main(map);
    
    }
    
}



   

  