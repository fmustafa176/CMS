package cms;

import classes.*;
import repositories.FileHandling;

/**
 *
 * @author Mustafa
 */
public class CMS {
    public static void main(String[] args) {
        Student s1 = new Student("null", "null", "null", "null", "null");
        String output = FileHandling.write(s1);
        System.out.println(output);
        output = FileHandling.read("Student.ser");
        System.out.println(output);
    }
    
}
