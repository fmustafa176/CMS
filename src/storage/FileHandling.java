package storage;

import java.io.*;
import classes.*;

public class FileHandling {

    public static String loadData(String fileName, Repository<Student> studentRepo, Repository<Teacher> teacherRepo, Repository<AdministrativeStaff> adminRepo, Repository<Course> courseRepo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            while (true) {
                Object obj = ois.readObject();
                if (obj instanceof Student) {
                    studentRepo.add((Student) obj);
                } 
                else if (obj instanceof Teacher) {
                    teacherRepo.add((Teacher) obj);
                } 
                else if (obj instanceof Course) {
                    courseRepo.add((Course) obj);
                }
                else if (obj instanceof AdministrativeStaff){
                    adminRepo.add((AdministrativeStaff) obj);
                }
            }
        } catch (EOFException e) {
            return "Data loaded successfully from " + fileName;
        } catch (IOException | ClassNotFoundException e) {
            return "Error loading data: " + e.getMessage();
        }
    }

    public static String saveData(String fileName, Repository<Student> studentRepo, Repository<Teacher> teacherRepo, Repository<AdministrativeStaff> adminRepo, Repository<Course> courseRepo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            // Save all students
            for (Student student : studentRepo.getAll()) {
                oos.writeObject(student);
            }

            // Save all teachers
            for (Teacher teacher : teacherRepo.getAll()) {
                oos.writeObject(teacher);
            }

            // Save all courses
            for (Course course : courseRepo.getAll()) {
                oos.writeObject(course);
            }

            for (AdministrativeStaff admin : adminRepo.getAll()) {
                oos.writeObject(admin);
            }

            return "Data saved successfully to " + fileName;
        } catch (IOException e) {
            return "Error saving data: " + e.getMessage();
        }
    }
}
