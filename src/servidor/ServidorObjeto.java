package servidor;

import java.io.*;
import java.net.*;

import datos.*;
public class ServidorObjeto {
	public static void main(String[] arg) throws IOException, ClassNotFoundException, InterruptedException {
		int numeroPuerto = 6000; //Puerto
		int i = 0, max_cliente = 3;
		String host = "localhost";
		boolean continua=true;
		
		System.out.println("Arrancando el servidor");
		ServerSocket servidor = new ServerSocket();
		InetSocketAddress direccion=new InetSocketAddress(host,numeroPuerto);
		servidor.bind(direccion);
		Socket[] S_conexiones = new Socket[max_cliente];
		atendercliente[] H_clientes=new atendercliente[max_cliente];
		
		while(continua){
			if(i < max_cliente) {
				System.out.println("Esperando al cliente…" + i);
				Socket cliente = servidor.accept();
				System.out.println("Cliente aceptado: " + i);
				
				S_conexiones[i]=cliente;
				atendercliente hiloCliente = new atendercliente(cliente,i);
				H_clientes[i]=hiloCliente;
				hiloCliente.start();
				i+=1;
				System.out.println("Sumo uno: " + i);
			} else {
				System.out.println("maximo de conexiones. Llegando todos los números");
				continua=false;
			}
		}
		
		//espera por todos los clientes y su número de la primitiva
		for(i=0;i<max_cliente;i++){
			H_clientes[i].join();
		}

		/*// obtener un stream para leer objetos
		ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
		Datos dato = (Datos) inObjeto.readObject();
		System.out.println("Enviado: " + dato.getNum1() + "" + dato.getOperacion() + "" + dato.getNum2() + "= " + dato.getRes());*/
		
		//Cerrar Streams y Sockets
		//inObjeto.close();
		
		//Cerramos todos los clientes
		for(i=0;i<max_cliente;i++){
			S_conexiones[i].close();
		}
		servidor.close();
	}
}
