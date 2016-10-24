package managers;

import beans.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest({BufferedReader.class,FileReader.class})
public class PersonManagerFromFileFactoryTest {
    @InjectMocks
    PersonManagerFromFile personManagerFromFile = new PersonManagerFromFile();

    @Mock
    File file;

    @Mock
    FileReader filereader ;

    @Mock
    Person person;
@Mock
    BufferedReader bufferedReader;

    /*@Before
    public  void setUp (){
        BufferedReader bufferedReader = new BufferedReader( filereader );
    }*/

    @Test
    public void writePersonIntoFileTest() throws Exception {
        /*Person person = new Person("Matt",32);
        String query = " INSERT INTO person (name,age) VALUES (?,?)";

        when(connectionDAO.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(query)).thenReturn(preparedStatement);

        personManagerFromDB.writePerson(person);

        verify(preparedStatement).executeUpdate();
        verify(preparedStatement).setString(1,"Matt");
        verify(preparedStatement).setInt(2,32);*/
    }

    @Test
    public void readLastPersonFromFileTest() throws Exception {
        Person person = new Person("Matt",32);
        String line = "Sergey,35";
        when(file.exists()).thenReturn(true);
        when(bufferedReader.readLine()).thenReturn(line);
        personManagerFromFile.readPerson("Matt");

        assertEquals(person.getName(), "Matt");
    }

    @Test
    public void readPersonByNameFromFileTest() throws Exception {

        String line = "Sergey,35";
        BufferedReader bufferedReader = mock(BufferedReader.class);

        PowerMockito.whenNew(BufferedReader.class).withArguments(filereader).thenReturn(bufferedReader);

        when(file.exists()).thenReturn(true);
        when(bufferedReader.readLine()).thenReturn(line);
        person = personManagerFromFile.readPerson("Matt");

        assertEquals(person.getName(), "Matt");
    }
}
