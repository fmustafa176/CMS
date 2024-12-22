package repositories;

import java.io.*;

public class FileHandling {
    public static <T> String write(T obj) {
        String fileName = obj.getClass().getSimpleName() + ".ser";
        try {
            ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(fileName));
            objOut.writeObject(obj);
            objOut.close();
            return "Record Added";
        }
        catch (IOException e) {
            return "Error";
        }
    }

    public static String read(String fileName) {
        StringBuilder records = new StringBuilder();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            while (true) {
                Object obj = ois.readObject();
                records.append(obj.toString()).append("\n\n");
            }
        } catch (EOFException e) {
            
        } catch (IOException | ClassNotFoundException e) {
            return "Error reading file: " + e.getMessage();
        }
        return records.length() > 0 ? records.toString() : "No records found.";
    }

    
}
