/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package innovativedesign;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
/**
 *
 * @author 18074751
 */
public class Search {
    private final File root;
    public Search(File file){
        this.root = file;
    }
    
    private ArrayList<ISearchListener> listeners = new ArrayList();
    public void addListener(ISearchListener listener){
        listeners.add(listener);
    }
    public enum SearchType {
        byFile,
        byDirect
    }
    private void fireSearchBegin(Search.SearchType type, String searchString){
        for (ISearchListener e : listeners)
            e.SearchBegin(type, searchString);
    }
    private boolean fireSearchUpdate(File results){
        for (ISearchListener e : listeners)
            if (!e.SearchUpdate(results, true))
                return true;
        return false;
    }
    private void fireSearchEnd(ArrayList<File> results){
        for (ISearchListener e : listeners)
            e.SearchEnd(results);
    }
    
    private class FileIterator implements Iterator {
        boolean inDirectories;
        Queue<File> directories = new LinkedList();
        Queue<File> subjects = new LinkedList();
        
        public FileIterator(boolean inDirectories){
            assert root.isDirectory();
            directories.add(root);
            this.inDirectories = inDirectories;
        }

        @Override
        public boolean hasNext() {
            return !(directories.isEmpty() && subjects.isEmpty());
        }

        @Override
        public File next() {
            if (subjects.isEmpty()){
                for (File file : directories.poll().listFiles()){
                    if (file.isDirectory() && this.inDirectories){
                        directories.add(file);
                    }
                    subjects.add(file);
                }
            }
            return subjects.poll();
        }
        
    }
    
    public ArrayList<File> byFile(String name, boolean inDirectories){
        FileIterator files = new FileIterator(inDirectories);
        ArrayList<File> results = new ArrayList();
        this.fireSearchBegin(SearchType.byFile, name); // Event start
        while (files.hasNext()){
            File file = files.next();
            if (file.isFile() && file.getName().contains(name)){
                results.add(file);
                if (this.fireSearchUpdate(file)){ // Event update
                    this.fireSearchEnd(results); // Search will stop if event is returned to hault the search
                    return results.isEmpty() ? null : results;
                }
            }
        }
        this.fireSearchEnd(results); // Event end
        return results.isEmpty() ? null : results;
    }
    
    public ArrayList<File> byDirectory(String name, boolean inDirectories){
        FileIterator files = new FileIterator(inDirectories);
        ArrayList<File> results = new ArrayList();
        this.fireSearchBegin(SearchType.byDirect, name); // Event start
        while (files.hasNext()){
            File file = files.next();
            if (file.isDirectory() && file.getName().contains(name)){
                results.add(file);
                if (this.fireSearchUpdate(file)){ // Event update
                    this.fireSearchEnd(results); // Search will stop if event is returned to hault the search
                    return results.isEmpty() ? null : results;
                }
            }
        }
        this.fireSearchEnd(results); // Event end
        return results.isEmpty() ? null : results;
    }
}

