/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FilterPack;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 18074751
 */
public class Filter {
    private final List<File> folder;
    Filter(File _folder){
        assert _folder.isDirectory() : "Filter class requires a directory.";
        this.folder = new ArrayList<>();
        folder.addAll(Arrays.asList(_folder.listFiles()));
    }
    public File[] getArray(){
        return (File[])folder.toArray();
    }
    public enum filterDateType {
        BEFORE,
        EQUAL,
        AFTER
    }
    public void byCreationDate(Date date, filterDateType _enum) throws IOException{
        assert date != null : "Date cannot be null.";
        assert _enum != null : "Enum cannot be null.";
        FileTime compare = FileTime.fromMillis(date.getTime());
        for (File f: folder){
            BasicFileAttributes attr = Files.readAttributes(f.toPath(), BasicFileAttributes.class);
            FileTime creationTime = attr.creationTime();
            switch (_enum){
                case AFTER:
                    if (creationTime.compareTo(compare) < 0){
                        folder.remove(f);
                    } break;
                case BEFORE:
                    if (creationTime.compareTo(compare) > 0){
                        folder.remove(f);
                    } break;
                case EQUAL:
                    if (creationTime.compareTo(compare) != 0){
                        folder.remove(f);
                    } break;
            }
        }
    }
    public void byCreationDate(Date dateA, Date dateB) throws IOException{
        byCreationDate(dateA, filterDateType.AFTER);
        byCreationDate(dateB, filterDateType.BEFORE);
    }
}
