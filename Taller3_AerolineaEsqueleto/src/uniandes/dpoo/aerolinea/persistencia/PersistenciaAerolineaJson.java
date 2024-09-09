package uniandes.dpoo.aerolinea.persistencia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Avion;


public class PersistenciaAerolineaJson implements IPersistenciaAerolinea{
	
	private static final String AEROPUERTOS = "aeropuertos";
    private static final String RUTAS = "rutas";
    private static final String VUELOS = "vuelos";
    private static final String CODIGO_RUTA = "codigoRuta";
    private static final String ORIGEN = "origen";
    private static final String DESTINO = "destino";
    private static final String HORA_SALIDA = "horaSalida";
    private static final String HORA_LLEGADA = "horaLlegada";
    private static final String AVION = "avion";
    private static final String FECHA = "fecha";

    public void cargarAerolinea(String archivo, Aerolinea aerolinea) throws IOException, InformacionInconsistenteException {
        String jsonCompleto = new String(Files.readAllBytes(new File(archivo).toPath()));
        JSONObject raiz = new JSONObject(jsonCompleto);

        cargarAeropuertos(aerolinea, raiz.getJSONArray(AEROPUERTOS));
        cargarRutas(aerolinea, raiz.getJSONArray(RUTAS));
        cargarVuelos(aerolinea, raiz.getJSONArray(VUELOS));
        cargarAviones(aerolinea, raiz.getJSONArray(AVION));
    }

    
    public void salvarAerolinea(String archivo, Aerolinea aerolinea) throws IOException {
        JSONObject jobject = new JSONObject();

        salvarAeropuertos(aerolinea, jobject);
        salvarRutas(aerolinea, jobject);
        salvarVuelos(aerolinea, jobject);
        salvarAviones(aerolinea, jobject);

        PrintWriter pw = new PrintWriter(archivo);
        jobject.write(pw, 2, 0); 
        pw.close();
    }

    private void cargarAeropuertos(Aerolinea aerolinea, JSONArray jAeropuertos) {
        for (int i = 0; i < jAeropuertos.length(); i++) {
            JSONObject jAeropuerto = jAeropuertos.getJSONObject(i);
            String codigo = jAeropuerto.getString("codigo");
            String nombre = jAeropuerto.getString("nombre");
            String nombreCiudad = jAeropuerto.getString("nombreCiudad"); 
            double latitud = jAeropuerto.getDouble("latitud");
            double longitud = jAeropuerto.getDouble("longitud");

            Aeropuerto aeropuerto = new Aeropuerto(nombre, codigo, nombreCiudad, latitud, longitud);

        }
    }

    private void cargarRutas(Aerolinea aerolinea, JSONArray jRutas) throws InformacionInconsistenteException {
        for (int i = 0; i < jRutas.length(); i++) {
            JSONObject jRuta = jRutas.getJSONObject(i);
            String codigoRuta = jRuta.getString(CODIGO_RUTA);
            JSONObject jOrigen = jRuta.getJSONObject(ORIGEN);
            JSONObject jDestino = jRuta.getJSONObject(DESTINO);
            String horaSalida = jRuta.getString(HORA_SALIDA);
            String horaLlegada = jRuta.getString(HORA_LLEGADA);

            String codigoOrigen = jOrigen.getString("codigo");
            String nombreOrigen = jOrigen.getString("nombre");
            String nombreCiudadOrigen = jOrigen.getString("nombreCiudad");
            double latitudOrigen = jOrigen.getDouble("latitud");
            double longitudOrigen = jOrigen.getDouble("longitud");

            Aeropuerto aeropuertoOrigen = new Aeropuerto(nombreOrigen, codigoOrigen, nombreCiudadOrigen, latitudOrigen, longitudOrigen);

            String codigoDestino = jDestino.getString("codigo");
            String nombreDestino = jDestino.getString("nombre");
            String nombreCiudadDestino = jDestino.getString("nombreCiudad");
            double latitudDestino = jDestino.getDouble("latitud");
            double longitudDestino = jDestino.getDouble("longitud");

            Aeropuerto aeropuertoDestino = new Aeropuerto(nombreDestino, codigoDestino, nombreCiudadDestino, latitudDestino, longitudDestino);

            Ruta ruta = new Ruta(aeropuertoOrigen, aeropuertoDestino, horaSalida, horaLlegada, codigoRuta);

            aerolinea.agregarRuta(ruta);;  
        }
    }

    private void cargarVuelos(Aerolinea aerolinea, JSONArray jVuelos) throws InformacionInconsistenteException {
        for (int i = 0; i < jVuelos.length(); i++) {
            JSONObject jVuelo = jVuelos.getJSONObject(i);
            String codigoRuta = jVuelo.getString(CODIGO_RUTA);
            String fecha = jVuelo.getString(FECHA);
            String nombreAvion = jVuelo.getString(AVION);
            int capacidadAvion = jVuelo.getInt("capacidadAvion");  

            Ruta ruta = aerolinea.getRuta(codigoRuta);
            if (ruta == null) {
                throw new InformacionInconsistenteException("La ruta " + codigoRuta + " no existe.");
            }
            
            Avion avion = new Avion(nombreAvion, capacidadAvion);

            Vuelo vuelo = new Vuelo(ruta, fecha, avion);

            aerolinea.getVuelos().add(vuelo); 
        }
    }
    
    private void cargarAviones(Aerolinea aerolinea, JSONArray jAviones) {
        for (int i = 0; i < jAviones.length(); i++) {
            JSONObject jAvion = jAviones.getJSONObject(i);
            String nombreAvion = jAvion.getString("nombre");  
            int capacidadAvion = jAvion.getInt("capacidad");  

            Avion avion = new Avion(nombreAvion, capacidadAvion);

            aerolinea.getAviones().add(avion);  
        }
    }

    private void salvarAeropuertos(Aerolinea aerolinea, JSONObject jobject) {
        JSONArray jAeropuertos = new JSONArray();
        Set<Aeropuerto> aeropuertos = new HashSet<>(); 

        for (Ruta ruta : aerolinea.getRutas()) {
           
            aeropuertos.add(ruta.getOrigen());
            aeropuertos.add(ruta.getDestino());
        }

        for (Aeropuerto aeropuerto : aeropuertos) {
            JSONObject jAeropuerto = new JSONObject();
            jAeropuerto.put("codigo", aeropuerto.getCodigo());
            jAeropuerto.put("nombre", aeropuerto.getNombre());
            jAeropuerto.put("latitud", aeropuerto.getLatitud());
            jAeropuerto.put("longitud", aeropuerto.getLongitud());
            jAeropuerto.put("nombreCiudad", aeropuerto.getNombreCiudad());
            jAeropuertos.put(jAeropuerto);
        }

        jobject.put("aeropuertos", jAeropuertos);
    }

    private void salvarRutas(Aerolinea aerolinea, JSONObject jobject) {
        JSONArray jRutas = new JSONArray();
        for (Ruta ruta : aerolinea.getRutas()) {
            JSONObject jRuta = new JSONObject();
            jRuta.put(CODIGO_RUTA, ruta.getCodigoRuta());
            jRuta.put(ORIGEN, ruta.getOrigen().getCodigo());
            jRuta.put(DESTINO, ruta.getDestino().getCodigo());
            jRuta.put(HORA_SALIDA, ruta.getHoraSalida());
            jRuta.put(HORA_LLEGADA, ruta.getHoraLlegada());
            jRutas.put(jRuta);
        }
        jobject.put(RUTAS, jRutas);
    }

    private void salvarVuelos(Aerolinea aerolinea, JSONObject jobject) {
        JSONArray jVuelos = new JSONArray();
        for (Vuelo vuelo : aerolinea.getVuelos()) {
            JSONObject jVuelo = new JSONObject();
            jVuelo.put(CODIGO_RUTA, vuelo.getRuta().getCodigoRuta());
            jVuelo.put(FECHA, vuelo.getFecha());
            jVuelo.put(AVION, vuelo.getAvion().getNombre()); 
            jVuelos.put(jVuelo);
        }
        jobject.put(VUELOS, jVuelos);
    }
    
    private void salvarAviones(Aerolinea aerolinea, JSONObject jobject) {
        JSONArray jAviones = new JSONArray();
        for (Avion avion : aerolinea.getAviones()) {
            JSONObject jAvion = new JSONObject();
            jAvion.put("nombre", avion.getNombre());  
            jAvion.put("capacidad", avion.getCapacidad());  
            jAviones.put(jAvion);  
        }
        jobject.put("aviones", jAviones);  
    }
}
