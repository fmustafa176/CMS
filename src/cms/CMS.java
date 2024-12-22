package cms;

import gui.*;
import storage.University;

/**
 *
 * @author Mustafa
 */
public class CMS {
    public static void main(String[] args) {
        University.loadData();
        HomePage menu = new HomePage();
        menu.setVisible(true);
    }
    
}
