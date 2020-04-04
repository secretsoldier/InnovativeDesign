/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SearchPack;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author 18074751
 */
public class FileIterator implements Iterator { // TODO Redesign class to straighten out errors and incorporate some way of measuring progress
        private final boolean inDirectories;
        final private Queue<File> directories = new LinkedList(), subjects = new LinkedList();
        
        public FileIterator(File root, boolean inDirectories){
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
                    if (file.isDirectory() && this.inDirectories)
                        directories.add(file);
                    subjects.add(file);
                }
            }
            return subjects.peek() == null ? next() : subjects.poll();
        }
        
    }
