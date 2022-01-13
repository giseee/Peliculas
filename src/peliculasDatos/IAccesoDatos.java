package peliculasDatos;

import peliculas.Pelicula;
import peliculasExcepciones.AccesoDatosEx;
import peliculasExcepciones.EscrituraDatosEx;
import peliculasExcepciones.LecturaDatosEx;

import java.util.List;

public interface IAccesoDatos {
    boolean existe(String nombre) throws AccesoDatosEx;
    List<Pelicula> listar(String nombre) throws LecturaDatosEx;
    void escribir(Pelicula pelicula, String nombre, boolean anexar) throws EscrituraDatosEx;
    String buscar(String nombre, String buscar) throws LecturaDatosEx;
    void crear(String nombre) throws AccesoDatosEx;
    void borrar(String nombre) throws AccesoDatosEx;

}
