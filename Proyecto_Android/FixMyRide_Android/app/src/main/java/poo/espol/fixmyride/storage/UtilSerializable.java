package poo.espol.fixmyride.storage;
import java.util.ArrayList;
import java.io.*;

public class UtilSerializable {
    // Serealizar las listas
    public static void guardarLista(ArrayList<?> lista, String nombreArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(lista);
            System.out.println("Lista guardada en " + nombreArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // De-Serializar las listas
    public static <T> ArrayList<T> cargarLista(String nombreArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            return (ArrayList<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado, devolviendo null.");
            return null; // O devuelve vacio.
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
