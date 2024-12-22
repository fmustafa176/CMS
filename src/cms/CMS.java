package cms;

import classes.*;
import repositories.FileHandling;

/**
 *
 * @author Mustafa
 */
public class CMS {
    public static void main(String[] args) {
        Teacher s1 = new Teacher("null", "null", "null", "null", 28, "null");
        String output = FileHandling.write(s1);
        System.out.println(output);
        output = FileHandling.read("Student.ser");
        System.out.println(output);
    }
    
}
