package co.edu.unbosque.model;

import co.edu.unbosque.model.persitence.FileUsers;
import co.edu.unbosque.model.persitence.Usuario;

import java.util.ArrayList;

public class UsuarioDAO {

    private ArrayList<Usuario> usuarios;
    private FileUsers archivoBinario;

    public UsuarioDAO() {
        usuarios = new ArrayList<>();
        archivoBinario = new FileUsers("./Data/usuarios.dat");
    }

    public void cargarUsuarios() {
        if (archivoBinario.leerRegistros() != null) {
            usuarios = archivoBinario.leerRegistros();
        }
    }

    public void activarUsuario(String nombre, String password) {
        for (Usuario user : usuarios) {
            if (nombre.equals(user.getNombre()) && password.equals(user.getContrasenia())) {
             user.setActivo(true);
            }
        }
    }


    public void registroUsuario(Usuario usuario) {
        usuarios.add(usuario);
        archivoBinario.escribirRegistros(usuarios);
    }

    public boolean ingresoUsuario(String nombre, String password) {
        boolean flag = false;
        for (Usuario user : usuarios) {
            if (nombre.equals(user.getNombre()) && password.equals(user.getContrasenia())) {
                flag = true;
            }
        }
        return flag;
    }

    public Usuario buscarUsuario(String nombre, String password) {
        Usuario temp = null;
        for (Usuario usuario : usuarios) {
            if (nombre.equals(usuario.getNombre()) && password.equals(usuario.getContrasenia()) && usuario.getActivo() == true) {
                temp = usuario;
            }
        }
        return temp;
    }

    public Usuario obtenerCliente() {
        Usuario temp = null;
        for (Usuario usuario : usuarios) {
            if (usuario.getTipo().equals("cliente") && usuario.getActivo() == true) {
                temp = usuario;
            }
        }
        return temp;
    }

    public Usuario obtenerAsesor() {
        Usuario temp = null;
        for (Usuario usuario : usuarios) {
            if (usuario.getTipo().equals("asesor") && usuario.getActivo() == true) {
                temp = usuario;
            }
        }
        return temp;
    }

}
