package services;

import services.DAO.DBConnectionDAO;
import services.DAOImpl.DBConnectionDAOSQLImpl;


public class DBDAOFactory {
    public DBDAOFactory() {
    }
    public DBConnectionDAO getDAO () {
        return new DBConnectionDAOSQLImpl();
    }
}
