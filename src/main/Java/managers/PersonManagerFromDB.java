package managers;

import beans.Person;
import services.DAO.DBConnectionDAO;
import services.DBDAOFactory;
import services.QueryFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonManagerFromDB implements PersonManager {
    private static final String GET_PERSON = "GET_PERSON";
    private static final String SAVE_PERSON = "SAVE_PERSON";
    private static final String GET_PERSON_BY_NAME = "GET_PERSON_BY_NAME";

    private DBConnectionDAO connectionDAO = new DBDAOFactory().getDAO();;
    private QueryFactory qf = new QueryFactory();

    public PersonManagerFromDB() {
    }

    public void writePerson(Person person) {

        Object lock = new Object();
        String query = qf.getQuery(SAVE_PERSON);

        try ( Connection connection = connectionDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
                synchronized (lock) {
                    preparedStatement.setString(1, person.getName());
                    preparedStatement.setInt(2, person.getAge());
                    preparedStatement.executeUpdate();
                }
        } catch (SQLException | IllegalArgumentException | ClassNotFoundException e) {
           throw new RuntimeException(e);
        }
    }

    public Person readPerson() {
        String query = qf.getQuery(GET_PERSON);

        Person person = null;

        try ( Connection connection = connectionDAO.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(query);) {

            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    person = createPerson(resultSet);
                }
            }
        } catch (SQLException | IllegalArgumentException | ClassNotFoundException  e) {
            throw new RuntimeException(e);
        }

        return person;
    }

    public Person readPerson(String name) {
        String query = qf.getQuery(GET_PERSON_BY_NAME);

        Person person = null;

        try ( Connection connection = connectionDAO.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(query);) {
                preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    person = createPerson(resultSet);
                }
            }
        } catch (SQLException | IllegalArgumentException | ClassNotFoundException  e) {
            throw new RuntimeException(e);
        }

        return person;
    }

    private Person createPerson (ResultSet rs) throws SQLException {
        Person person = new Person();

        person.setName(rs.getString("NAME"));
        person.setAge(rs.getInt("AGE"));

        return person;
    }
}
