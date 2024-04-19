package controlador;

import modelo.DaoImplementacionBD;
import vista.VInicio;

public class Main {

	public static void main(String[] args) {

		Dao dao = new DaoImplementacionBD();
		VInicio ini = new VInicio(dao);
		ini.setVisible(true);
	}
}
