package services;

import services.DAO.DBConnectionDAO;
import services.DAO.DBConnectionDAOSQLImpl;


public class DBDAOFactory {
    public DBDAOFactory() {
    }
    public DBConnectionDAO getDAO () {
        return new DBConnectionDAOSQLImpl();
    }
}
