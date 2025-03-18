/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author link
 */
public class Servidor {

    private static final int NUMMAXJINETES = 4;
    private ArrayList<DatagramPacket> jinetes;  //DatagramPackets de los jinetes.
    private byte contadorPosicionFinal;  //Dice cuál es el siguiente puesto.
    private byte[] posicionesFinales;   //Posiciones finales de los jinetes.
    private byte[] avances;  //Avances de los jinetes.
    private int numJinetesListos;   //Número de jinetes que están listos para continuar.
    private int numJinetesAcabado;  //Número de jinetes que han acabado la carrera.
    private boolean fin;
    private String[] nombresJinetes;
    
    public void ejecutarServidor() {
        contadorPosicionFinal = 1;
        numJinetesAcabado = 0;
        numJinetesListos = 0;
        nombresJinetes = new String[NUMMAXJINETES];
        posicionesFinales = new byte[NUMMAXJINETES];
        avances = new byte[NUMMAXJINETES];
        for(int i=0; i<NUMMAXJINETES; i++) {
            nombresJinetes[i]="";
            posicionesFinales[i] = 0;
            avances[i] = 0;
        }
        fin=false;
        
        try {
            jinetes = new ArrayList();
            System.out.println("Comienza la ejecucion del servidor. Escuchando...");

            byte[] buffer = new byte[256], buffer2;
            DatagramSocket s = new DatagramSocket(5555); // puerto de eco
            DatagramPacket p, p2;

            int puerto, longitud;
            InetAddress dir;
            String mensaje;

            //Esperamos los jinetes
            int jinete = 0;
            do {
                p = new DatagramPacket(buffer, 256);
                s.receive(p); //espero un datagrama, se queda esperando hasta que llega un datagrama.

                buffer = p.getData(); //obtengo datos
                puerto = p.getPort(); //obtengo puerto origen
                dir = p.getAddress(); //obtengo dir IP
                longitud = p.getLength(); //longitud del mensaje
                mensaje = new String(buffer, 0, longitud); //texto del mensaje
                String nombre = mensaje;
                this.nombresJinetes[jinete]=mensaje;
                System.out.println("Entra el jinete: "+nombre+" por calle "+(jinete)+". Recibido desde " + dir + ":" + puerto + " > ");
                //Lanzo acuse de recibo
                mensaje = "aceptado,"+jinete;
                // lo convierte a vector de bytes
                buffer2 = mensaje.getBytes();
                //ahora construyo el paquete, especifico destino
                //Ahora creamos el datagrama que será enviado por el socket 's'.
                p2 = new DatagramPacket(buffer2, mensaje.length(), dir, puerto);
                s.send(p2); //envio datagrama 
                jinetes.add(p);
                jinete++;
            } while (jinetes.size() < NUMMAXJINETES);

            //Empezamos los hilos que manejan los clientes.
            int i = 0;
            for (DatagramPacket dp : jinetes) {
                GestionClientes h = new GestionClientes(this, dp, i, this.nombresJinetes[i]);
                h.start();
                i++;
            }

            //Durante la carrera
            do {
                synchronized (this) {
                    wait();
//                    System.out.println("----");
                }
                numJinetesListos = 0;
                synchronized (this) {
                    notifyAll();
                }
            } while (numJinetesAcabado < NUMMAXJINETES);

            fin=true;
           
            //Posiciones finales
            synchronized (this) {
                wait();
            }

            synchronized (this) {
                notifyAll();
            }
            System.out.println("La carrera ha terminado.");
            mostrarResultados();
 
        } catch (IOException | InterruptedException e) {
            System.out.println("Se ha producido una excepcion: "+ e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    /**
     * Funcion que muestra los resultados de la carrera por la salida estandar del servidor
     */
    private void mostrarResultados() {
        for(int i =0; i<posicionesFinales.length; i++){
            System.out.print(""+this.nombresJinetes[i]+" en posicion: "+posicionesFinales[i]+"\t");
        }
        System.out.println("\nPODIO final:");
        for(int i=1; i<=NUMMAXJINETES; i++){
            for(int j=0; j<posicionesFinales.length; j++){
                if(posicionesFinales[j]==i){
                    System.out.println(""+i+".\t"+ this.nombresJinetes[j]); break;
                }
            }
        }
    }

    /**
     * Se le añade el avance al camello en el vector avances.
     *
     * @param posicion Posición del vector.
     * @param avance Cantidad del avance.
     */
    public synchronized void realizarAvance(int posicion, byte avance) {
        if ((this.avances[posicion] + avance) < 100) {
            this.avances[posicion] += avance;
        } else {
            if (this.avances[posicion] < 100) {
                this.avances[posicion] = 100;
                this.numJinetesAcabado++;
            }
        }
        numJinetesListos++;

        //Si todos los hilos están listos para continuar se despierta al servidor.
        if (numJinetesListos == NUMMAXJINETES) {
            notifyAll();
        } else {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * Devuelve el vector con las avances de los camellos.
     *
     * @return
     */
    public synchronized byte[] getAvances() {
        return this.avances;
    }


    /**
     * Devuelve el vector con las posiciones finales de los jinetes.
     * @return
     */
    public synchronized byte[] getPosicionesFinales() {
        if (contadorPosicionFinal == NUMMAXJINETES+1) {
            notifyAll();
        } else {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.posicionesFinales;
    }
    
    public synchronized void setPosicionFinal(int idCamello) {
        this.posicionesFinales[idCamello] = contadorPosicionFinal;
        System.out.println("Jinete "+this.nombresJinetes[idCamello]+ " llega a meta en posicion: " +contadorPosicionFinal);
        contadorPosicionFinal++;
    }

    public int getNumJinetesAcabado() {
        return this.numJinetesAcabado;
    }

    public synchronized void aumentarNumJinetesAcabado() {
        this.numJinetesAcabado++;
    }
    
    public boolean isFin(){
        return this.fin;
    }
    
    public String[] getNombresJinetes() {
        return nombresJinetes;
    }

    public void setNombresJinetes(String[] nombresJinetes) {
        this.nombresJinetes = nombresJinetes;
    }

    public static int getNUMMAXJINETES() {
        return NUMMAXJINETES;
    }
    
    
}
