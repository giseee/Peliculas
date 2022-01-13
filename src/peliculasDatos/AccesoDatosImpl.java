package peliculasDatos;

import peliculas.Pelicula;
import peliculasExcepciones.AccesoDatosEx;
import peliculasExcepciones.EscrituraDatosEx;
import peliculasExcepciones.LecturaDatosEx;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AccesoDatosImpl implements IAccesoDatos {
    @Override
    public boolean existe(String nombre)  {
       var archivo = new File(nombre);
        return archivo.exists();
    }

    @Override
    public List<Pelicula> listar(String nombre) throws LecturaDatosEx {
        var archivo = new File(nombre);
        List<Pelicula> peliculas = new ArrayList<>();
        try {
            var entrada = new BufferedReader(new FileReader(archivo));
            String linea = null;
            linea = entrada.readLine();
            while(linea != null){
               var pelicula = new Pelicula(linea);
               peliculas.add(pelicula);
               linea = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            throw new LecturaDatosEx("Excepcion al listar peliculas: " + ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new LecturaDatosEx("Excepcion al listar peliculas: " + ex.getMessage());
        }
        return peliculas;
    }

    @Override
    public void escribir (Pelicula pelicula, String nombre, boolean anexar) throws EscrituraDatosEx {
        var archivo =new File(nombre);
        try {
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            salida.println(pelicula.toString());
            salida.close();
            System.out.println("se ha escrito informacion al archivo" + pelicula);
        } catch(IOException ex) {
          ex.printStackTrace();
          throw new EscrituraDatosEx("Excepcion al escribir las peliculas: "+ ex.getMessage());
        }

    }
    public String buscar(String nombre, String buscar) throws LecturaDatosEx{
        var archivo = new File(nombre);
        String resultado = null;
        try {
            var entrada = new BufferedReader(new FileReader(archivo));
            String linea = null;
            linea = entrada.readLine();
            var indice = 1;
            while(linea != null){
                if(buscar != null && buscar.equals(linea)) {
                    resultado = "Pelicula " + linea + " encontrada en el indice " + indice;
                    break; }
                linea = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            throw new LecturaDatosEx("Excepcion al buscar pelicula: " + ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new LecturaDatosEx("Excepcion al buscar pelicula: " + ex.getMessage());
        }
        return resultado;
    }

    @Override
    public void crear(String nombre) throws AccesoDatosEx {
        var archivo= new File(nombre);
        try {
            var salida = new PrintWriter(new FileWriter(archivo));
            salida.close();
            System.out.println("se ha creado el archivo");
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new AccesoDatosEx("Excepcion al crear archivo: " + ex.getMessage());
        }

    }

    @Override
    public void borrar(String nombre){
        var archivo = new File(nombre);
        if (archivo.exists()){
            archivo.delete();
            System.out.println("Se ha eliminado el archivo");
        }

    }
}
