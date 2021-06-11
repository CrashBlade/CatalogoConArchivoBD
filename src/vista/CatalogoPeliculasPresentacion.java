package vista;

import co.com.gm.peliculas.negocio.CatalogoPeliculasImp;
import co.com.gm.peliculas.negocio.ICatalogoPeliculas;

import java.util.Scanner;

public class CatalogoPeliculasPresentacion {

    public static void main(String[] args) {

        int opcion = -1;
        Scanner scanner = new Scanner(System.in);
        ICatalogoPeliculas catalogo = new CatalogoPeliculasImp();

        while (opcion!=0)
        {
            System.out.println("Elija una opcion:  \n"
            + "1. Iniciar catalogo de peliculas \n"
            + "2. Agreagar pelicula \n"
            + "3. Listar peliculas \n"
            + "4. Buscar pelicula \n"
            + "0. Salir");

            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion){
                case 1:
                    catalogo.iniciarCatalogoPeliculas();
                    break;
                case 2:
                    System.out.println("Nombre de la pelicula");
                    String nombrePelicula = scanner.nextLine();
                    catalogo.agregarPeliculas(nombrePelicula);
                    break;
                case 3:
                    catalogo.listarPeliculas();
                    break;
                case 4:
                    System.out.println("Nombre de la pelicula");
                    String buscar = scanner.nextLine();
                    catalogo.buscarPeliculas(buscar);
                    break;
                case 0:
                    System.out.println("Bye");
                    break;
                default:
                    System.out.println("Opcion no reconocida");
            }

        }

    }
}
