package managers;

import beans.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;
import org.mockito.runners.MockitoJUnitRunner;
import services.DAO.DBConnectionDAO;


@RunWith(MockitoJUnitRunner.class)
public class PersonManagerFromDBFactoryTest {

    @InjectMocks
    PersonManagerFromDB personManagerFromDB = new PersonManagerFromDB();

    @Mock
    DBConnectionDAO connectionDAO;

    @Mock
    PreparedStatement preparedStatement;

    @Mock
    Connection connection ;

    @Mock
    ResultSet resultSet;


    @Test
    public void writePersonToDBTest() throws Exception {
        Person person = new Person("Matt",32);
        String query = " INSERT INTO person (name,age) VALUES (?,?)";

        when(connectionDAO.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(query)).thenReturn(preparedStatement);

        personManagerFromDB.writePerson(person);

        verify(preparedStatement).executeUpdate();
        verify(preparedStatement).setString(1,"Matt");
        verify(preparedStatement).setInt(2,32);
    }

    @Test
    public void readLastPersonFromDBTest() throws Exception {
        String  name = "David";
        String query = "SELECT * FROM person  where idPerson=(SELECT MAX(idPerson) FROM person)";

        when(connectionDAO.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(query)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true,false);
        when(resultSet.getString("NAME")).thenReturn(name);
        Person person = personManagerFromDB.readLastPerson();
        assertEquals(person.getName(), name);
    }

    @Test
    public void readPersonByNameFromDBTest() throws Exception {
        String query = "SELECT * FROM person WHERE NAME = ?";
        String  name = "Alex";
        when(connectionDAO.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(query)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true,false);
        when(resultSet.getString("NAME")).thenReturn(name);

        Person person = personManagerFromDB.readPerson(name);
        assertEquals(person.getName(), name);
    }
}