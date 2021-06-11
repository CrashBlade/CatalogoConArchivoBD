package co.com.gm.peliculas.datos;

import co.com.gm.peliculas.domain.Pelicula;
import co.com.gm.peliculas.excepciones.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AccesoDatosImp implements IAccesoDatos{

    @Override
    public boolean existe(String nombrePathArchivo) {
        File archivo = new File(nombrePathArchivo);
        return archivo.exists();
    }


    @Override
    public List<Pelicula> listar(String nombrePathArchivo) throws LecturaDatosEx {
        File archivo = new File(nombrePathArchivo);
        List<Pelicula> peliculas = new ArrayList<>();
        try {
            /*  La clase ---FileReader--- permite tener acceso al archivo en modo lectura.
                Para crear objetos FileReader podemos utilizar los constructores:
                    **FileReader(String ruta)
                    **FileReader(File objetoFile);

                La clase ---BufferReader--- permite leer caracteres del archivo
                Un objeto BufferReader se crea a partir de un objeto FileReader.
                    **BufferReader leector = new BufferReader (File objetoFile)
                    */

            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            String linea = null;
            linea = entrada.readLine();
            while (linea!=null){
                Pelicula pelicula = new Pelicula(linea);
                peliculas.add(pelicula);
                linea = entrada.readLine();
            }

            entrada.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new LecturaDatosEx("Excepcion al listar peliculas (Metodo Listar)" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new LecturaDatosEx("Excepcion al listar peliculas (Metodo Listar)" + e.getMessage());
        }
        return peliculas;
    }

    @Override
    public void escribir(Pelicula pelicula, String nombrePathArchivo, Boolean anexar) throws EscrituraDatosEx {
        File archivo = new File(nombrePathArchivo);
        try {
            /*  La clase ---PrintWriter--- permite escribir caracteres en el archivo de la misma forma que la pantalla
                Un objeto PrintWriter se crea a partir de un objeto FileWriter.
                    **PrintWriter escribir = new PrintWriter(FileWriter(String path));
                    **PrintWriter escribir = new PrintWriter(FileWriter(File objetoFile));

              Una vez creado el objeto podemos utilizar print(), println() y printf() para escribir en el archivo.

              La clase ---FileWriter--- permite tener acceso al archivo en modo escritura.
                Para crear objetos FileWriter podemos utilizar los constructores:
                    **FileWriter(String path)      -> El archivo se crea y si ya existe su contenido se pierde.
                    **FileWriter(File objetoFile); -> El archivo se crea y si ya existe su contenido se pierde.

                    **FileWriter(String path, boolean append)     -> Abre el archivo adiciona el contenido sin perder info
                    **FileWriter(File objetoFile, boolean append) -> Abre el archivo adiciona el contenido sin perder info
             */
            PrintWriter varEscritura = new PrintWriter(new FileWriter(archivo, anexar));
            varEscritura.println(pelicula.toString());
            varEscritura.close();
            System.out.println("Se ha escrito la pelicula " + pelicula.toString() + "en el archivo.");
        } catch (IOException e) {
            e.printStackTrace();
            throw new EscrituraDatosEx("Error al escribir sobre el archivo" + e.getMessage());
        }
    }

    @Override
    public String buscar(String nombreArchivo, String buscar) throws LecturaDatosEx {
        File archivo = new File(nombreArchivo);
        String resultado = null;

        try {

            BufferedReader lector = new BufferedReader(new FileReader(archivo));
            String lectura = null;
            lectura = lector.readLine();
            int indice =1;

            while (lectura!= null) {
                if (buscar != null && buscar.equalsIgnoreCase(lectura)){
                    resultado = "Pelicula: " + lectura + " encontrada en el indice: "+ indice;
                    break;
                }
                lectura = lector.readLine();
                indice++;
            }
            lector.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new LecturaDatosEx("Excepcion al buscar peliculas (Metodo Buscar)" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new LecturaDatosEx("Excepcion al buscar peliculas (Metodo Buscar)" + e.getMessage());
        }

        return resultado;
    }

    @Override
    public void crear(String nombrePathArchivo) throws AccesoDatosEx {
        File archivo = new File(nombrePathArchivo);
        try {
            PrintWriter varEscritura = new PrintWriter(new FileWriter(archivo));
            varEscritura.close();
            System.out.println("Se ha creado el archivo con exito");
        } catch (IOException e) {
            e.printStackTrace();
            throw new AccesoDatosEx("Excepcion al crear archivo peliculas (Metodo Crear)" + e.getMessage());
        }

    }

    @Override
    public void borrar(String nombrePathArchivo) {
        File archivo = new File(nombrePathArchivo);
        if (archivo.exists()) {
            archivo.delete();
            System.out.println("El archivo: "+ nombrePathArchivo +" ha sido eliminado");
        }else
            System.out.println("El archivo no fue encontrado");

    }
}
