package co.edu.unbosque.model.persitence;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;

public class Usuario implements Serializable {

    private String nombre, tipo, contrasenia;
    private Boolean activo;
    private Usuario emparejado;
    
    public Usuario(String nombre, String tipo, String contrasenia, Boolean activo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.contrasenia = contrasenia;
        this.activo = activo;
        this.emparejado = null;
    }


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Usuario getEmparejado() {
		return emparejado;
	}

	public void setEmparejado(Usuario emparejado) {
		this.emparejado = emparejado;
	}

	@Override
	public String toString() {
		return "Usuario{" +
				"nombre='" + nombre + '\'' +
				", tipo='" + tipo + '\'' +
				", contrasenia='" + contrasenia + '\'' +
				", activo=" + activo +
				", emparejado=" + emparejado +
				'}';
	}
}

