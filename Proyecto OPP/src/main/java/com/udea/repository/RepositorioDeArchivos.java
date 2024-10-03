package com.udea.repository;

import com.udea.modelos.Propiedad;
import com.udea.exceptions.PrecioFueraDeRangoException;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class RepositorioDeArchivos implements PropiedadRepositorio {

    private final String filePath;
    private List<Propiedad> propiedades;
    private static final int PRECIO_MINIMO = 90000000; // Precio mínimo permitido
    private static final int PRECIO_MAXIMO = 1000000000; // Precio máximo permitido
    protected static final Logger logger = LogManager.getLogger();


    public RepositorioDeArchivos(String filePath) {
        this.propiedades = new ArrayList<>();
        cargarPropiedades("propiedades.txt");
        this.filePath = filePath;
        logger.info("Mensaje de prueba");
        logger.error("Mensaje de error");

    }
    @Override
    public List<Propiedad> listadoPorTipoPropiedades(String tipoPropiedad) {
        List<Propiedad> resultado = new ArrayList<>();

        // Si el tipoPropiedad es nulo o vacío, devolver todas las propiedades
        if (tipoPropiedad == null || tipoPropiedad.trim().isEmpty()) {
            return new ArrayList<>(propiedades); // Retornamos todas las propiedades
        }

        // Si se especifica un tipo de propiedad, filtramos
        for (Propiedad propiedad : propiedades) {
            if (propiedad.getTipoPropiedad().equalsIgnoreCase(tipoPropiedad)) {
                resultado.add(propiedad);
            }
        }
        return resultado;
    }

    @Override
    public List<Propiedad> listarTodasLasPropiedades() {
        return propiedades; // Devuelve todas las propiedades
    }

    @Override
    public int numeroPropiedadesPorEstrato(int estrato) {
        int count = 0;
        for (Propiedad propiedad : propiedades) {
            if (propiedad.getEstrato() == estrato) {
                count++;
            }
        }
        return count;
    }

    @Override
    public List<Propiedad> busquedaPropiedadPorMatricula(int numeroDeMatricula) {
        List<Propiedad> resultado = new ArrayList<>();
        for (Propiedad propiedad : propiedades) {
            if (propiedad.getNumeroDeMatricula() == numeroDeMatricula) {
                resultado.add(propiedad);
            }
        }
        return resultado;
    }

    @Override
    public void ingresarNuevaPropiedad(int matricula, int estrato, String tipoPropiedad, String barrio, int valor) throws PrecioFueraDeRangoException{
        if (valor < PRECIO_MINIMO || valor > PRECIO_MAXIMO) {
            throw new PrecioFueraDeRangoException(valor);
        }
        Propiedad nuevaPropiedad = new Propiedad(matricula, estrato, barrio,valor, tipoPropiedad, LocalDate.now());
        propiedades.add(nuevaPropiedad);
        escribirPropiedades();
    }

    private void cargarPropiedades(String File) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("propiedades.txt");

        if (inputStream == null) {
            throw new IllegalArgumentException("Archivo no encontrado en el classpath: propiedades.txt");
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                Propiedad propiedad = new Propiedad(
                        Integer.parseInt(datos[0]), // Matricula
                        Integer.parseInt(datos[1]), // Estrato
                        datos[2],                   // Barrio
                        Integer.parseInt(datos[3]), // Valor
                        datos[4],                   // Tipo
                        LocalDate.parse(datos[5])   // Fecha
                );
                propiedades.add(propiedad);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void escribirPropiedades() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Propiedad propiedad : propiedades) {
                bw.write(String.format("%d,%d,%s,%d,%s,%s\n",
                        propiedad.getNumeroDeMatricula(),
                        propiedad.getEstrato(),
                        propiedad.getBarrio(),
                        propiedad.getValorDePropiedad(),
                        propiedad.getTipoPropiedad(),
                        propiedad.getFechaRegistroPropiedad().toString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
