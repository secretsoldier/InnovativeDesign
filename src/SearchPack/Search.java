/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SearchPack;
import java.io.File;
import java.util.ArrayList;
/**
 *
 * @author 18074751
 */
public class Search {
    private final File root;
    
    public Search(File file){
        this.root = file;
    }
    
    private final ArrayList<ISearchListener> listeners = new ArrayList();
    public void addListener(ISearchListener listener){
        listeners.add(listener);
    }
    public enum SearchType {
        both,
        byFile,
        byDirect
    }
    private void fireSearchBegin(Search.SearchType type, String searchString){
        listeners.forEach((e) -> e.SearchBegin(type, searchString));
    }
    private boolean fireSearchUpdate(File results){
        for (ISearchListener e : listeners)
            if (!e.SearchUpdate(results, true))
                return true;
        return false;
    }
    private void fireSearchEnd(ArrayList<File> results){
        listeners.forEach((e) -> e.SearchEnd(results));
    }
    
    public ArrayList<File> byFile(String name, boolean inDirectories){
        FileIterator files = new FileIterator(root, inDirectories);
        ArrayList<File> results = new ArrayList<>();
        this.fireSearchBegin(SearchType.byFile, name); // Event start

        while (files.hasNext()){
            File file = files.next();
            if (file.isFile() && file.getName().contains(name)){
                results.add(file);
                if (this.fireSearchUpdate(file)){ // Event update
                    this.fireSearchEnd(results); // Search will stop if event is returned to halt the search
                    return results.isEmpty() ? null : results;
                }
            }
        }

        this.fireSearchEnd(results); // Event end
        return results.isEmpty() ? null : results;
    }
    
    public ArrayList<File> byDirectory(String name, boolean inDirectories){
        FileIterator files = new FileIterator(root, inDirectories);
        ArrayList<File> results = new ArrayList<>();
        this.fireSearchBegin(SearchType.byDirect, name); // Event start

        while (files.hasNext()){
            File file = files.next();
            if (file.isDirectory() && file.getName().contains(name)){
                results.add(file);
                if (this.fireSearchUpdate(file)){ // Event update
                    this.fireSearchEnd(results); // Search will stop if event is returned to halt the search
                    return results.isEmpty() ? null : results;
                }
            }
        }

        this.fireSearchEnd(results); // Event end
        return results.isEmpty() ? null : results;
    }

    public ArrayList<File> byBoth(String name, boolean inDirectories){
        FileIterator files = new FileIterator(root, inDirectories);
        ArrayList<File> results = new ArrayList<>();
        this.fireSearchBegin(SearchType.both, name); // Event start

        while (files.hasNext()){
            File file = files.next();
            if (file.getName().contains(name)){
                results.add(file);
                if (this.fireSearchUpdate(file)){
                    this.fireSearchEnd(results);
                    return results.isEmpty() ? null : results;
                }
            }
        }

        this.fireSearchEnd(results); // Event end
        return results.isEmpty() ? null : results;
    }
}

