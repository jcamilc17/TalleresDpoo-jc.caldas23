package uniandes.dpoo.estructuras.logica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Esta clase tiene un conjunto de métodos para practicar operaciones sobre listas de enteros y de cadenas.
 *
 * Todos los métodos deben operar sobre los atributos listaEnteros y listaCadenas.
 * 
 * No pueden agregarse nuevos atributos.
 * 
 * Implemente los métodos usando operaciones sobre listas (ie., no haga cosas como construir arreglos para evitar la manipulación de listas).
 * 
 * Intente usar varias formas de recorrer las listas (while, for, for each, iteradores ... )
 */
public class SandboxListas
{
    /**
     * Una lista de enteros para realizar varias de las siguientes operaciones.
     */
    private List<Integer> listaEnteros;

    /**
     * Una lista de cadenas para realizar varias de las siguientes operaciones
     */
    private List<String> listaCadenas;

    /**
     * Crea una nueva instancia de la clase con las dos listas inicializadas pero vacías
     */
    public SandboxListas( )
    {
        listaEnteros = new ArrayList<Integer>( );
        listaCadenas = new LinkedList<String>( );
    }

    /**
     * Retorna una copia de la lista de enteros, es decir una nueva lista del mismo tamaño que contiene copias de los valores de la lista original
     * @return Una copia de la lista de enteros
     */
    public List<Integer> getCopiaEnteros( )
    {
        return new ArrayList <>(listaEnteros);
    }

    /**
     * Retorna una copia de la lista de cadenas, es decir una nueva lista del mismo tamaño que contiene copias de los valores de la lista original
     * @return Una copia de la lista de cadenas
     */
    public List<String> getCopiaCadenas( )
    {
        return new ArrayList <>(listaCadenas);
    }

    /**
     * Retorna un arreglo con los valores de la lista de enteros, es decir un arreglo del mismo tamaño que contiene copias de los valores de la lista
     * @return Una arreglo de enteros
     */
    public int[] getEnterosComoArreglo( )
    {
    	int[] arreglo = new int[listaEnteros.size()];
    	for (int i = 0; i < listaEnteros.size(); i++) {
    		arreglo[i] = listaEnteros.get(i);
    	}
        return arreglo;
    }

    /**
     * Retorna la cantidad de valores en la lista de enteros
     * @return
     */
    public int getCantidadEnteros( )
    {
        return listaEnteros.size();
    }

    /**
     * Retorna la cantidad de valores en la lista de cadenas
     * @return
     */
    public int getCantidadCadenas( )
    {
        return listaCadenas.size();
    }

    /**
     * Agrega un nuevo valor al final de la lista de enteros. Es decir que este método siempre debería aumentar en 1 el tamaño de la lista.
     * 
     * @param entero El valor que se va a agregar.
     */
    public void agregarEntero( int entero )
    {
    	listaEnteros.add(entero);

    }

    /**
     * Agrega un nuevo valor al final de la lista de cadenas. Es decir que este método siempre debería aumentar en 1 la capacidad de la lista.
     * 
     * @param cadena La cadena que se va a agregar.
     */
    public void agregarCadena( String cadena )
    {
    	listaCadenas.add(cadena);

    }

    /**
     * Elimina todas las apariciones de un determinado valor dentro de la lista de enteros
     * @param valor El valor que se va eliminar
     */
    public void eliminarEntero( int valor )
    {
    	for (int i = listaEnteros.size() - 1;i >= 0; i--) {
    		if (listaEnteros.get(i) == valor) {
    			listaEnteros.remove(i);
    		}
    	}
    }

    /**
     * Elimina todas las apariciones de un determinado valor dentro de la lista de cadenas
     * @param cadena La cadena que se va eliminar
     */
    public void eliminarCadena( String cadena )
    {
    	for (int i = listaCadenas.size() - 1;i >= 0; i--) {
    		if (listaCadenas.get(i) == cadena) {
    			listaCadenas.remove(i);
    		}
    	}

    }

    /**
     * Inserta un nuevo entero en la lista de enteros
     * 
     * @param entero El nuevo valor que debe agregarse
     * @param posicion La posición donde debe quedar el nuevo valor en la lista aumentada. Si la posición es menor a 0, se inserta el valor en la primera posición. Si la
     *        posición es mayor que el tamaño de la lista, se inserta el valor en la última posición.
     */
    public void insertarEntero( int entero, int posicion )
    {
    	if (posicion < 0) {
    		listaEnteros.add(0, entero);
    	}
    	else if (posicion >= listaEnteros.size()) {
    		listaEnteros.add(entero);
    	}
    	else {
    		listaEnteros.add(posicion,entero);
    	} 

    }

    /**
     * Elimina un valor de la lista de enteros dada su posición.
     * @param posicion La posición donde está el elemento que debe ser eliminado. Si el parámetro posicion no corresponde a ninguna posición de la lista de enteros, el método
     *        no debe hacer nada.
     */
    public void eliminarEnteroPorPosicion( int posicion )
    {
    	if (posicion >= 0 && posicion < listaEnteros.size()) {
    		listaEnteros.remove(posicion);
    	}
    	

    }

    /**
     * Reinicia la lista de enteros con los valores contenidos en el parámetro 'valores', pero truncados.
     * 
     * Es decir que si el valor fuera 3.67, en la nueva lista debería quedar el entero 3.
     * @param valores Un arreglo de valores decimales.
     */
    public void reiniciarArregloEnteros( double[] valores )
    {
    	listaEnteros.clear();
    	for (double valor : valores) {
    		listaEnteros.add((int) valor);
    	}
    }

    /**
     * Reinicia la lista de cadenas con las representaciones como Strings de los objetos contenidos en la lista del parámetro 'objetos'.
     * 
     * Use el método toString para convertir los objetos a cadenas.
     * @param valores Una lista de objetos
     */
    public void reiniciarArregloCadenas( List<Object> objetos )
    {
    	listaCadenas.clear();
    	for (Object objeto : objetos) {
    		listaCadenas.add(objeto.toString());
    	}
    		
    }

    /**
     * Modifica la lista de enteros para que todos los valores sean positivos.
     * 
     * Es decir que si en una posición había un valor negativo, después de ejecutar el método debe quedar el mismo valor muliplicado por -1.
     */
    public void volverPositivos( )
    {
    	for (int i = 0; i < listaEnteros.size(); i++) {
    		int valor = listaEnteros.get(i);
    		if (valor < 0) {
    			listaEnteros.set(i, -valor);
    		}
    	}
    }

    /**
     * Modifica la lista de enteros para que todos los valores queden organizados de MAYOR a MENOR.
     */
    public void organizarEnteros( )
    {
    	for (int i = 0; i < listaEnteros.size() - 1; i++) {
    		for (int j = 0; j < listaEnteros.size() -1; j++) {
    			if (listaEnteros.get(j) < listaEnteros.get(j + 1)) {
    				int temporal = listaEnteros.get(j);
    				listaEnteros.set(j, listaEnteros.get(j + 1));
    				listaEnteros.set(j + 1, temporal);
    			}
    		}
    	}

    }

    /**
     * Modifica la lista de cadenas para que todos los valores queden organizados lexicográficamente.
     */
    public void organizarCadenas( )
    {
    	for (int i = 0; i < listaCadenas.size() - 1; i++) {
    		for (int j = 0; j < listaCadenas.size() -1; j++) {
    			if (listaCadenas.get(j).compareTo(listaCadenas.get(j + 1)) > 0) {
    				String temporal = listaCadenas.get(j);
    				listaCadenas.set(j, listaCadenas.get(j + 1));
    				listaCadenas.set(j + 1, temporal);
    			}
    		}
    	}

    }

    /**
     * Cuenta cuántas veces aparece el valor recibido por parámetro en la lista de enteros
     * @param valor El valor buscado
     * @return La cantidad de veces que aparece el valor
     */
    public int contarApariciones( int valor )
    {
    	int contador = 0;
    	for (int num : listaEnteros) {
    		if (num == valor) {
    			contador++;
    		}
    	}
        return contador;
    }

    /**
     * Cuenta cuántas veces aparece la cadena recibida por parámetro en la lista de cadenas.
     * 
     * La búsqueda no debe diferenciar entre mayúsculas y minúsculas.
     * @param cadena La cadena buscada
     * @return La cantidad de veces que aparece la cadena
     */
    public int contarApariciones( String cadena )
    {
    	int contador = 0;
    	for (String item : listaCadenas) {
    		if (item.equalsIgnoreCase(cadena)) {
    			contador++;
    		}
    	}
        return contador;
        
    }

    /**
     * Cuenta cuántos valores dentro de la lista de enteros están repetidos.
     * @return La cantidad de enteos diferentes que aparecen más de una vez
     */
    public int contarEnterosRepetidos( )
    {
    	boolean[] yaContado = new boolean[listaEnteros.size()];
    	int contadorRepetidos = 0;
    	
    	for (int i = 0; i <listaEnteros.size(); i++) {
    		if (yaContado[i]) continue;
    		
    		int frecuencia = 0;
    		for (int j = 0; j < listaEnteros.size(); j++) {
    			if (listaEnteros.get(i).equals(listaEnteros.get(j))) {
    				frecuencia++;
    				if (i != j) yaContado[j] = true;
    			}
    		}
    		if (frecuencia > 1) {
    			contadorRepetidos++;
    		}
    	}
        return contadorRepetidos;
    }

    /**
     * Compara la lista de enteros con un arreglo de enteros y verifica si contienen los mismos elementos exactamente en el mismo orden.
     * @param otroArreglo El arreglo de enteros con el que se debe comparar
     * @return True si los elementos son los mismos y en el mismo orden y false de lo contrario
     */
    public boolean compararArregloEnteros( int[] otroArreglo )
    {
    	if (listaEnteros.size() != otroArreglo.length) {
    		return false;
    	}
    	for (int i = 0; i <listaEnteros.size(); i++) {
    		if (!listaEnteros.get(i).equals(otroArreglo[i])) {
    			return false;
    		}
    	}
        return true;
    }

    /**
     * Cambia los elementos de la lista de enteros por una nueva serie de valores generada de forma aleatoria.
     * 
     * Para generar los valores se debe partir de una distribución uniforme usando Math.random().
     * 
     * Los números en la lista deben quedar entre el valor mínimo y el máximo.
     * @param cantidad La cantidad de elementos que debe haber en la lista
     * @param minimo El valor mínimo para los números generados
     * @param maximo El valor máximo para los números generados
     */
    public void generarEnteros( int cantidad, int minimo, int maximo )
    {
    	listaEnteros.clear();
    	
    	for (int i = 0; i < cantidad; i++) {
    		int valor = (int) (Math.random() * (maximo - minimo + 1)) + minimo;
    		listaEnteros.add(valor);
    	}

    }

}
