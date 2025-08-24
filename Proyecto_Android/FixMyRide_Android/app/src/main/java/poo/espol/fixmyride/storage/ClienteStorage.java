package poo.espol.fixmyride.storage;
import java.util.ArrayList;
import java.io.*;
import poo.espol.fixmyride.model.Cliente;

public class ClienteStorage {
    
    //Nombre del archivo
    private static final String FILE_NAME = "clientes.ser";
    
    //Serializar la lista de clientes
    public static void guardarClientes(ArrayList<Cliente> clientes){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))){
            oos.writeObject(clientes);
            System.out.println("Clientes guradados en: "+ FILE_NAME);
        
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    //De-Serializar la lista de clientes
    public static ArrayList<Cliente> cargarClientes() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>(); // si no existe, devolvemos lista vacía
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<Cliente>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

