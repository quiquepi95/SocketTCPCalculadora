package cliente;

import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

import datos.Datos;

public class ClienteObjeto {
	public static void main(String[] arg) throws IOException, ClassNotFoundException {
		String Host= "localhost";
		int Puerto = 6000; //Puerto remoto
		System.out.println("Programa Cliente Iniciado…");
		Socket cliente = new Socket(Host,Puerto);

		
		Datos calcula = new Datos("+",20,10,0);
		
		calcula.setNum1(Integer.parseInt(JOptionPane.showInputDialog(null,"Introduce primer número:")));
		calcula.setOperacion(JOptionPane.showInputDialog(null,"Introduce suma(+), resta (-) o multiplicación(*):"));
		calcula.setNum2(Integer.parseInt(JOptionPane.showInputDialog(null,"Introduce segundo número:")));
		
		
		//Modificación del objeto
//		dato.setNum1(5);
//		dato.setOperacion("+");
//		dato.setNum2(10);
		
		System.out.println("(Client)Enviado: " + calcula.getNum1() + "" + calcula.getOperacion() + "" + calcula.getNum2() + "= " + calcula.getRes());
		//Flujo de salida para objetos
		ObjectOutputStream perSal = new ObjectOutputStream(cliente.getOutputStream());
		perSal.writeObject(calcula); // envio del objeto
		
		
		ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
		Datos dato = (Datos) inObjeto.readObject();
		System.out.println("(Client)Recibido: " + dato.getNum1() + "" + dato.getOperacion() + "" + dato.getNum2() + "= " + dato.getRes());
		
		// Cerrar Streams y Sockets
		perSal.close();
		inObjeto.close();
		cliente.close();
	}
}
