package note;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import connection.DAO;
import connection.MartConnection;
import graphicsModel.MartList;

public class CoefDAO extends DAO<Coefficient> {
    Coefficient coefficient = new Coefficient();

    public CoefDAO(Connection conn) {
	super(conn);
    }

    @Override
    public boolean createObj(Coefficient obj) {
	// Insert une ligne dans table_perso
	this.coefficient = obj;

	String strQuery = "'" + coefficient.getPrimaryKey() + "','" + coefficient.getCoef() + "'";

	try {
	    Statement state = MartConnection.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		    ResultSet.CONCUR_READ_ONLY);

	    String query = "INSERT INTO " + tableCoef + " (intitule_coef,valeur_coef) VALUES (" + strQuery + ")";

	    System.out.println(query);
	    state.executeUpdate(query);

	} catch (SQLException e) {
	    e.printStackTrace();
	}

	return false;
    }

    @Override
    public boolean updateObj(Coefficient obj) {
	coefficient = obj;

	try {
	    Statement state = MartConnection.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		    ResultSet.CONCUR_READ_ONLY);

	    String query = "UPDATE " + tableCoef + " SET intitule_coef='" + coefficient.getIntitule()
		    + "', valeur_coef='" + coefficient.getCoef() + "' WHERE intitule_coef='"
		    + coefficient.getPrimaryKey() + "'";

	    System.out.println(query);
	    state.executeUpdate(query);

	} catch (SQLException e) {
	    e.printStackTrace();
	}

	return true;
    }

    @Override
    public boolean update_create(Coefficient obj) {
	if (exists(obj) == true) {
	    updateObj(obj);
	    return true;
	} else {
	    createObj(obj);
	    return false;
	}
    }

    @Override
    public boolean deleteObj(Coefficient obj) {
	String query = "DELETE FROM " + tableCoef + " WHERE " + "intitule_coef='" + obj.getPrimaryKey() + "'";
	updateDB(query);

	return true;
    }

    @Override
    public MartList<Coefficient> getList() {
	// se connecte et rechercher
	String niveau = classe;
	String nivCor = clscor;
	listT = new MartList<>();
	try {
	    Statement state = MartConnection.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		    ResultSet.CONCUR_READ_ONLY);
	    String query = "SELECT intitule_coef, valeur_coef FROM " + tableCoef;

	    // System.out.println(query);
	    ResultSet rst = state.executeQuery(query);
	    ResultSetMetaData rstMeta = rst.getMetaData();

	    rst.last();
	    int length = rst.getRow();
	    int col = rstMeta.getColumnCount();

	    rst.beforeFirst();

	    while (rst.next()) {
		String key = rst.getString(1);
		double co = Double.parseDouble(rst.getString(2));

		Coefficient coef = new Coefficient(co, key);

		listT.add(coef);
	    }
	    rst.close();
	    state.close();

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return listT;
    }

    @Override
    public Coefficient findObj(String code) {
	for (Coefficient coef : listT) {

	    // System.out.println("*****************************coef= "
	    // + coef.getPrimaryKey() + " et le code est:" + code);

	    while (coef.getPrimaryKey().equals(code)) {
		coefficient = coef;
		break;
	    }
	}

	// System.out.println("*****************************coef= "
	// + coefficient.getCoef());

	return coefficient;
    }

    @Override
    public int getOrdre(String intitule) {
	// TODO Auto-generated method stub
	return 0;
    }

}
