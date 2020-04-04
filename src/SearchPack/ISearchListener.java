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
public interface ISearchListener {

    /**
     *
     * @param type
     * @param searchString
     */
    default void SearchBegin(Search.SearchType type, String searchString, File directory, boolean subfolders){

    };

    /**
     *
     * @param result
     * @param active
     * @return
     */
    default boolean SearchUpdate(File result, boolean active){
        return true;
    };

    /**
     *
     * @param results
     */
    default void SearchEnd(ArrayList<File> results){

    };
}
