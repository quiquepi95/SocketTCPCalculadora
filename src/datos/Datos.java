package datos;

import java.io.Serializable;
@SuppressWarnings("serial")
//Esta anotación se utiliza para evitar un error en tiempo de compilación al implementar la interfaz java.io.Serializable
public class Datos implements Serializable {
	String operacion;
	int num1;
	int num2;
	int res;
	public Datos(String opera, int num1, int num2, int res){
		super();
		this.operacion=opera;
		this.num1=num1;
		this.num2=num2;
		this.res=res;
	}
	public Datos() {super();}
	public String getOperacion() {return operacion;}
	public void setOperacion( String operacion) {this.operacion = operacion;}
	
	public int getNum1() {return num1;}
	public void setNum1(int num1) {this.num1 = num1;}
	
	public int getNum2() {return num2;}
	public void setNum2(int num2) {this.num2 = num2;}
	
	public int getRes() {return res;}
	public void setRes(int res) {this.res = res;}
}
