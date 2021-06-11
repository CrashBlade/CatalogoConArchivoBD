package co.com.gm.peliculas.datos;

import co.com.gm.peliculas.domain.Pelicula;
import co.com.gm.peliculas.excepciones.AccesoDatosEx;
import co.com.gm.peliculas.excepciones.EscrituraDatosEx;
import co.com.gm.peliculas.excepciones.LecturaDatosEx;

import java.util.List;

public interface IAccesoDatos {

    boolean existe (String nombrePathArchivo) throws AccesoDatosEx;
    List<Pelicula> listar (String nombrePathArchivo) throws LecturaDatosEx;
    void escribir (Pelicula pelicula, String nombrePathArchivo, Boolean anexar) throws EscrituraDatosEx;
    String buscar (String nombreArchivo, String buscar) throws LecturaDatosEx;
    void crear (String nombrePathArchivo) throws AccesoDatosEx;
    void borrar (String nombrePathArchivo) throws AccesoDatosEx;

}
