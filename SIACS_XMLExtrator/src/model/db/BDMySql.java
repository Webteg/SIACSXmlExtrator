package model.db;

import java.sql.Connection;
 
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BDMySql {

	private static BDMySql singleton = null;
	private Connection con;

	public static BDMySql getInstance() {
		if (singleton == null) {
			singleton = new BDMySql();
		}
		return singleton;
	}

	private BDMySql() {
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/siacs_xml_extrator", "siacs", "siacs");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	protected ResultSet executarBuscaSQL(String sql) {
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			return rs;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	protected void executarSQL(String sql) {
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	protected void fecharConexao() {
		try {
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	@Override
	protected void finalize() throws Throwable {
		fecharConexao();
		super.finalize();
	}

}