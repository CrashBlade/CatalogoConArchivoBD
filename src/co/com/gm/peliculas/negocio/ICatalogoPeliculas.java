package co.com.gm.peliculas.negocio;

public interface ICatalogoPeliculas {

    String NOMBRE_PATH_PELICULA = "peliculas.txt";

    void agregarPeliculas(String nombrePelicula);
    void listarPeliculas();
    void buscarPeliculas(String buscar);
    void iniciarCatalogoPeliculas();

}
