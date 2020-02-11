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
    default public void SearchBegin(Search.SearchType type, String searchString){};

    /**
     *
     * @param results
     * @param active
     * @return
     */
    default public boolean SearchUpdate(File result, boolean active){return active;};

    /**
     *
     * @param results
     */
    default public void SearchEnd(ArrayList<File> results){};
}
