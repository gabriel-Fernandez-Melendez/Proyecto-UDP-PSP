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
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author link
 */
public class GestionClientes extends Thread {// extiende de hilo

    private int idCamello;
    private String jinete;
    private Servidor servidor;
    private DatagramPacket p;
    private DatagramSocket socket;

    public GestionClientes(Servidor s, DatagramPacket p, int idCammello, String jinete) {
        this.servidor = s;
        this.p = p;
        try {
            this.socket = new DatagramSocket(); //lo inicializa en el constructor
        } catch (SocketException ex) {
            Logger.getLogger(GestionClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.idCamello = idCammello;
        this.jinete = jinete;
    }

    @Override
    public void run() { //esto  no hace falta que se sincronze por que cada hilo lo ejecuta por separado
        byte[] buffer, buffer2 = new byte[1];
        int puerto, longitud;
        InetAddress dir;

        buffer = p.getData(); //obtengo datos
        puerto = p.getPort(); //obtengo puerto origen
        dir = p.getAddress(); //obtengo dir IP
        longitud = p.getLength(); //longitud del mensaje

        try {
            //Empieza la carrera
            String nombre = ""; 
            for(int i=0; i< servidor.getNombresJinetes().length-1; i++ ) {
                String s = servidor.getNombresJinetes()[i] +",";
                nombre += s;
                }
            nombre += servidor.getNombresJinetes()[Servidor.getNUMMAXJINETES()-1]; //esto  para hacer contrapeso al +1 (?)
            byte[] bufferEmpezar = nombre.getBytes();
            DatagramPacket pEmpezar = new DatagramPacket(bufferEmpezar, nombre.length(), dir, puerto);
            socket.send(pEmpezar); //se envia al cliente

            DatagramPacket p2;
            int total =0;
            int navance=1;
            boolean fin = false;
            do {
                //Realizamos un avance
                byte avance = avanzar();
                servidor.realizarAvance(idCamello, avance);
                total +=(int)avance;
                if(total<=100)
                    System.out.println("Jinete "+jinete+ " camello("+idCamello + ") y realizo avance_"+navance+". Llevo " + total +" en total.");
                else {    
                    if(!fin){
                        servidor.setPosicionFinal(idCamello);
                        //System.out.println("Jinete "+jinete+ " camello("+idCamello + ") ya llegó a meta. (avance_"+navance+").");
                    }
                    fin = true;
                }
                navance++;
                //Le enviamos al cliente los avances de todos los jinetes.
                byte[] avances = new byte[5];
                copiarArray(avances, servidor.getAvances());
                avances[Servidor.getNUMMAXJINETES()] = 0;
                p2 = new DatagramPacket(avances, 5, dir, puerto);
                socket.send(p2);
                
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GestionClientes.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } while (!servidor.isFin());

            //Esto lo hacemos para filtrar el mensaje.
            byte[] posicionesFinales = new byte[5];
            copiarArray(posicionesFinales, servidor.getPosicionesFinales());
            posicionesFinales[Servidor.getNUMMAXJINETES()] = -1;
            //Enviamos las posiciones finales de los jinetes.
            p2 = new DatagramPacket(posicionesFinales, 5, dir, puerto);
            socket.send(p2);
        } catch (IOException ex) {
            Logger.getLogger(GestionClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public byte avanzar() {
        int n = (int) (Math.random() * 100);
        byte pasos = 0;
        if (n <= 15) {
            pasos = 1;
        } else {
            if (n > 15 && n <= 30) {
                pasos = 2;
            } else {
                if (n > 30 && n <= 40) {
                    pasos = 3;
                } else {
                    if (n > 40 && n <= 48) {
                        pasos = 4;
                    } else {
                        if (n > 48 && n <= 56) {
                            pasos = 5;
                        } else {
                            if (n > 56 && n <= 63) {
                                pasos = 6;
                            } else {
                                if (n > 63 && n <= 70) {
                                    pasos = 7;
                                } else {
                                    if (n > 70 && n <= 80) {
                                        pasos = 8;
                                    } else {
                                        if (n > 80 && n <= 90) {
                                            pasos = 9;
                                        } else {
                                            if (n > 90) {
                                                pasos = 10;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return pasos;
    }

    public void copiarArray(byte[] a, byte[] b) {
        for (int i = 0; i < b.length; i++) {
            a[i] = b[i];
        }
    }

}
