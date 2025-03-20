/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import server.Servidor;

/**
 *
 * @author link
 */
public class Cliente extends Thread {

	//no tiene atributos pero se le pone un string directo en el constructor
    public Cliente(String nombre) {
        this.setName(nombre);
    }

    @Override
    public void run() {

        try {
            String mensaje;
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

            //puerto origen del socket: el que encuentre libre.
            DatagramSocket s = new DatagramSocket();
            DatagramPacket p, p2;
            byte[] buffer2 = new byte[256];

            System.out.println("Puerto de origen reservado por el cliente: " + s.getLocalPort());

            
            System.out.print("Dame el nombre del jinete: ");
            mensaje=teclado.readLine();
            String nombre = mensaje;
            // lo convierte a vector de bytes
            byte[] buffer = mensaje.getBytes();
            //ahora construyo el paquete, especifico destino
            //Ahora creamos el datagrama que será enviado por el socket 's'.
            p = new DatagramPacket(buffer, mensaje.length(), InetAddress.getLocalHost(), 5555);//lo enviamos al mismo puerto que esta en escucha en el server 
            s.send(p); //envio datagrama

            //Acuse de recibo
            int puerto, longitud;
            InetAddress dir;
            p2 = new DatagramPacket(buffer2, 256);// se obtienen los datos de forma cruzada (INTERESANTE)
            s.receive(p2);
            buffer2 = p2.getData(); //obtengo datos
            puerto = p2.getPort(); //obtengo puerto origen
            dir = p2.getAddress(); //obtengo dir IP
            longitud = p2.getLength(); //longitud del mensaje
            mensaje = new String(buffer2, 0, longitud); //texto del mensaje
            System.out.println("El servidor:" + dir + ":" + puerto + " >  ha confirmado el mensaje anterior con el mensaje: " + mensaje);
            int idcamello;
            String[] aux = mensaje.split(",");
            idcamello = Integer.parseInt(aux[1]); 
            if (aux[0].equals("aceptado")) {
                System.out.println("Soy el camello "+nombre+". He entrado en la carrera con el camello" + idcamello);
            } else {
                //Esto no llega a ejecutarse porque una vez entrados todos los jinetes los demás se quedan 
                //esperando un mensaje.
                System.out.println("No he entrado en la carrera.");
            }
            
            byte[] bufferEmpezar = new byte[256];
            DatagramPacket pEmpezar = new DatagramPacket(bufferEmpezar, 256);
            s.receive(pEmpezar);
            mensaje=new String(pEmpezar.getData(), 0, pEmpezar.getLength());
            System.out.println("Soy el jinete "+nombre +", con el camello "+idcamello+". Empieza la carrera.");

            //Mostramos la ventana de la carrera
            ClienteVentanaCarrera v=new ClienteVentanaCarrera();
            v.setTitle("Ventana del jinete: " + nombre);
            v.setNombresJinetes(mensaje);
            v.setVisible(true);
            
            byte[] bufferPosiciones = new byte[5];
            do {
                p2 = new DatagramPacket(bufferPosiciones, 5, InetAddress.getLocalHost(), 1600);
                s.receive(p2);

                if (bufferPosiciones[Servidor.getNUMMAXJINETES()] != -1) {
                    //System.out.println("Posiciones: " + bufferPosiciones[0] + ", " + bufferPosiciones[1] + ", " + bufferPosiciones[2] + ", " + bufferPosiciones[3]);
                    v.avance(bufferPosiciones);
                } else {
                   // System.out.println("Posiciones finales: " + bufferPosiciones[0] + ", " + bufferPosiciones[1] + ", " + bufferPosiciones[2] + ", " + bufferPosiciones[3]);
                    v.setPosicionesFinales(bufferPosiciones);
                }
            } while (bufferPosiciones[Servidor.getNUMMAXJINETES()]!=-1); // ES LO MISMO QUE UN WHILE TRUE
            
        } catch (IOException | InterruptedException e) {
            System.out.println("Se ha producido una excepción: " + e.getLocalizedMessage()); 
            e.printStackTrace();
        }
    }
}
