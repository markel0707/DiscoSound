package controlador;

import clase.Usuario;
import modelo.DaoImplementacionBD;
import vista.VInicio;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Usuario usu = null;
		Dao dao = new DaoImplementacionBD();
		VInicio ini=new VInicio(dao, usu);
		ini.setVisible(true);
	}

}
