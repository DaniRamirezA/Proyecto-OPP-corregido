package com.udea.repository;

import com.udea.modelos.Propiedad;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class PropiedadRepositorioTest {

    // Se carga el repositorio de archivos con el archivo de prueba "propiedades.txt"
    PropiedadRepositorio repo = new RepositorioDeArchivos("propiedades.txt");

    @org.junit.jupiter.api.Test
    void listadoPorTipoPropiedades() {
        // CASO: Listado por tipo de Propiedades
        repo.ingresarNuevaPropiedad(1001, 3, "Apartamento", "Barrio Norte", 150000000);
        repo.ingresarNuevaPropiedad(1002, 7, "Casa", "Barrio Sur", 90000000);
        repo.ingresarNuevaPropiedad(1003, 5, "Apartamento", "Barrio Centro", 120000000);
        List<Propiedad> propiedadesApartamentos = repo.listadoPorTipoPropiedades("Apartamento");

        // Confirmamos que la lista no esta vacia
        assertNotNull(propiedadesApartamentos, "La lista de apartamentos no debe ser null.");
        assertFalse(propiedadesApartamentos.isEmpty(), "La lista de apartamentos no debe estar vacía.");

        // Verificamos que todas las propiedades en la lista sean del tipo "Apartamento"
        for (Propiedad propiedad : propiedadesApartamentos) {
            assertEquals("Apartamento", propiedad.getTipoPropiedad(), "La propiedad debe ser del tipo 'Apartamento'.");
        }

        // Verificamos que el número de apartamentos devueltos sea el correcto (2 agregados y dos del archivo plano)
        assertEquals(4, propiedadesApartamentos.size(), "Debería haber 4 apartamentos en la lista.");

    }

    @org.junit.jupiter.api.Test
    void listarTodasLasPropiedades() {
        // CASO: Listar todas las propiedades
        List<Propiedad> propiedades = repo.listarTodasLasPropiedades();
        assertNotNull(propiedades, "La lista de propiedades no debe ser null.");
        assertTrue(propiedades.size() > 0, "La lista de propiedades debería contener elementos.");
    }

    @org.junit.jupiter.api.Test
    void numeroPropiedadesPorEstrato() {
        // CASO: Contar propiedades por estrato (ejemplo: estrato 3)
        int propiedadesEstrato3 = repo.numeroPropiedadesPorEstrato(3);
        assertTrue(propiedadesEstrato3 > 0, "Debería haber al menos una propiedad en estrato 3.");

        // CASO: Contar propiedades por estrato inexistente (ejemplo: estrato 10)
        int propiedadesEstrato10 = repo.numeroPropiedadesPorEstrato(10);
        assertEquals(0, propiedadesEstrato10, "No debería haber propiedades en estrato 10.");
    }

    @org.junit.jupiter.api.Test
    void busquedaPropiedadPorMatricula() {

        // CASO: Buscar propiedad por matrícula existente
        List<Propiedad> propiedades = repo.busquedaPropiedadPorMatricula(1234); // Matricula como int
        assertNotNull(propiedades, "La lista de propiedades no debe ser null.");
        assertFalse(propiedades.isEmpty(), "La lista de propiedades no debe estar vacía.");

        // Verificamos que al menos una propiedad coincide con la matrícula
        for (Propiedad propiedad : propiedades) {
            assertEquals(1234, propiedad.getNumeroDeMatricula(), "La matrícula debe coincidir con la buscada.");
        }

        // CASO: Buscar propiedad por matrícula inexistente
        List<Propiedad> propiedadesInexistentes = repo.busquedaPropiedadPorMatricula(0000); // Matricula inexistente
        assertNotNull(propiedadesInexistentes, "La lista de propiedades no debe ser null.");
        assertTrue(propiedadesInexistentes.isEmpty(), "No debería encontrarse ninguna propiedad con matrícula inexistente.");
    }

    @org.junit.jupiter.api.Test
    void ingresarNuevaPropiedad() {

        // CASO: La nueva propiedad ingresad existe y sus atributos coinciden
        // Agregar una nueva propiedades
        System.out.println("== Agregando nuevas propiedades ==");
        repo.ingresarNuevaPropiedad(1001, 3, "Apartamento", "Barrio Norte", 150000000);
        List<Propiedad> propiedadesEncontradas = repo.busquedaPropiedadPorMatricula(1001);

        // Confirmamos que la lista no esté vacía
        assertNotNull(propiedadesEncontradas, "La lista de propiedades no debe ser null.");
        assertFalse(propiedadesEncontradas.isEmpty(), "La lista de propiedades no debe estar vacía.");

        // Verificamos que la propiedad que se agregó es la correcta
        Propiedad propiedadAgregada = propiedadesEncontradas.get(0);

        // Comprobamos que los valores de la propiedad agregada coincidan con los que ingresamos
        assertEquals(1001, propiedadAgregada.getNumeroDeMatricula(), "La matrícula debe coincidir con la propiedad agregada.");
        assertEquals(3, propiedadAgregada.getEstrato(), "El estrato debe coincidir.");
        assertEquals("Apartamento", propiedadAgregada.getTipoPropiedad(), "El tipo de propiedad debe coincidir.");
        assertEquals("Barrio Norte", propiedadAgregada.getBarrio(), "El barrio debe coincidir.");
        assertEquals(150000000, propiedadAgregada.getValorDePropiedad(), "El valor de la propiedad debe coincidir.");
    }
}