package co.edu.unbosque.model.persitence;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileUsers {
    private String ruta;

    public FileUsers(String ruta) {
        this.ruta = ruta;
    }

    public void escribirRegistros(ArrayList<Usuario> reg) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ruta));
            out.writeObject(reg);
            out.close();
            System.out.println("dato ingresado en la base de datos.");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error de IO");
        }

    }

    public ArrayList<Usuario> leerRegistros() {
        ObjectInputStream in;
        ArrayList<Usuario> reg = new ArrayList<Usuario>();
        try {
            in = new ObjectInputStream(new FileInputStream(ruta));
            reg = (ArrayList<Usuario>) in.readObject();
            in.close();

        } catch (Exception e) {
            System.out.println("No hay datos por leer ");
        }
        return reg;
    }
}



