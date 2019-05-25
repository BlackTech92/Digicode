import java.sql.*;

public class ConnexionDigicode {

	final String driver = "com.mysql.cj.jdbc.Driver";
	final String url = "jdbc:mysql://localhost/digicode" + "?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
	final String user = "root";
	final String password = "";
	ResultSet res;
	int res2;

	public ResultSet executeRequete(String query) {
		try {
			Class.forName(driver).newInstance();
			Connection con = DriverManager.getConnection(url, user, password);
			Statement st = con.createStatement();
			res = st.executeQuery(query);
			return res;

		} catch (Exception e) {
			System.out.println("Erreur, " + e.getMessage());
			return null;
		}
	}

	public int executeUp(String query) {
		try {
			Class.forName(driver).newInstance();
			Connection con = DriverManager.getConnection(url, user, password);
			Statement st = con.createStatement();
			System.out.println("Connexion effective !");
			res2 = st.executeUpdate(query);
			return res2;
		} catch (Exception e) {
			System.out.println("Erreur :" + e.getMessage());
			return (Integer) null;
		}
	}
}
