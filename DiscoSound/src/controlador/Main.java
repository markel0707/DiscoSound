package controlador;

	public class Main {

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			Dao dao = new DaoImplementacionBD();
			VInicio ini=new VInicio(dao);
			ini.setVisible(true);
		}

	}
