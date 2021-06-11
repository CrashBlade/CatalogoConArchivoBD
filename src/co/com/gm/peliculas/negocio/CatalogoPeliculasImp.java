package co.com.gm.peliculas.negocio;

import co.com.gm.peliculas.datos.*;
import co.com.gm.peliculas.domain.Pelicula;
import co.com.gm.peliculas.excepciones.*;


import java.util.List;

public class CatalogoPeliculasImp implements ICatalogoPeliculas{

    private final IAccesoDatos datos;

    public CatalogoPeliculasImp() {
        datos = new AccesoDatosImp();
    }


    @Override
    public void agregarPeliculas(String nombrePelicula) {
        Pelicula pelicula = new Pelicula(nombrePelicula);
        boolean anexar = false;
        try {
            anexar = datos.existe(NOMBRE_PATH_PELICULA);
            datos.escribir(pelicula,NOMBRE_PATH_PELICULA, anexar);
        } catch (AccesoDatosEx accesoDatosEx) {
            accesoDatosEx.printStackTrace(System.out);
        }
    }

    @Override
    public void listarPeliculas() {
        try {
            List<Pelicula> peliculas = datos.listar(NOMBRE_PATH_PELICULA);
            for (Pelicula listaPeliculas: peliculas
                 ) {
                System.out.println("listaPeliculas = " + listaPeliculas);
            }
        } catch (LecturaDatosEx lecturaDatosEx) {
            lecturaDatosEx.printStackTrace(System.out);
        }
    }

    @Override
    public void buscarPeliculas(String buscar) {
        String resultadoBusqueda =null;
        try {
            resultadoBusqueda = datos.buscar(NOMBRE_PATH_PELICULA,buscar);
            System.out.println("resultadoBusqueda = " + resultadoBusqueda);
        } catch (LecturaDatosEx lecturaDatosEx) {
            lecturaDatosEx.printStackTrace(System.out);
        }
    }

    @Override
    public void iniciarCatalogoPeliculas() {
        try {
            if (this.datos.existe(NOMBRE_PATH_PELICULA)){
                datos.borrar(NOMBRE_PATH_PELICULA);
                datos.crear(NOMBRE_PATH_PELICULA);
            }
            else {
                datos.crear(NOMBRE_PATH_PELICULA);
            }
        } catch (AccesoDatosEx accesoDatosEx) {
            System.out.println("Error al iniciar catalogo de peliculas");
            accesoDatosEx.printStackTrace(System.out);
        }
    }
}
