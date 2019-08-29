package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import datos.Datos;

class atendercliente extends Thread{

	Socket cliente;
	
	atendercliente(Socket cliente, int i) {
		this.cliente = cliente;
	}
	
	public void run() {
		// obtener un stream para leer objetos
		ObjectInputStream inObjeto;
		
		try {
			inObjeto = new ObjectInputStream(cliente.getInputStream());
			Datos dato = (Datos) inObjeto.readObject();
			System.out.println("(Server)Recibido: " + dato.getNum1() + "" + dato.getOperacion() + "" + dato.getNum2() + "= " + dato.getRes());
			/*switch (dato.getOperacion()) {
			case "+":
				dato.setRes(dato.getNum1() + dato.getNum2());
				break;
			case "-":
				dato.setRes(dato.getNum1() - dato.getNum2());
				break;
			case "*":
				dato.setRes(dato.getNum1() * dato.getNum2());
				break;
			default:
				break;
			}*/
			if(dato.getOperacion().equals("+")) {
				dato.setRes(dato.getNum1() + dato.getNum2());
				System.out.println("Estoy en suma");
			} else if (dato.getOperacion().equals("-")) {
				dato.setRes(dato.getNum1() - dato.getNum2());
				System.out.println("Estoy en resta");
			} else if (dato.getOperacion().equals("*")) {
				dato.setRes(dato.getNum1() * dato.getNum2());
				System.out.println("Estoy en multi");
			}
			
			System.out.println("(Server)Enviado: " + dato.getNum1() + "" + dato.getOperacion() + "" + dato.getNum2() + "= " + dato.getRes());
			
			ObjectOutputStream perSal = new ObjectOutputStream(cliente.getOutputStream());
			perSal.writeObject(dato); // envio del objeto
			
			inObjeto.close();
			perSal.close();
			cliente.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
