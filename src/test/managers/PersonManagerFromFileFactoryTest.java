package managers;

import beans.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import java.io.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class PersonManagerFromFileFactoryTest {

    private PersonManagerFromFile personManagerFromFile = new PersonManagerFromFile();
    private Person person;

    @Test
    public void readLastPersonFromFileTestShouldBeEquals() throws Exception {

        String testString = "Sergey,35\nMatt,43";
        BufferedReader bufferedReader= new BufferedReader(new StringReader(testString));
        person = personManagerFromFile.getLastPerson(bufferedReader);
        assertEquals(person.getName(), "Matt");
    }

    @Test
    public void readLastPersonFromFileTestShouldBeNotEquals() throws Exception {

        String testString = "Sergey,35\nMatt,43";
        BufferedReader bufferedReader= new BufferedReader(new StringReader(testString));
        person = personManagerFromFile.getLastPerson(bufferedReader);
        assertNotEquals(person.getName(), "Sergey");
    }

    @Test
    public void readPersonByNameFromFileTestShouldBeEquals() throws Exception {

        String testString = "Sergey,35";
        BufferedReader bufferedReader= new BufferedReader(new StringReader(testString));
        person= personManagerFromFile.getPersonByName(bufferedReader,"Sergey");
        assertEquals(person.getName(), "Sergey");
    }

    @Test
    public void readPersonByNameFromFileTestShouldBeNotEquals() throws Exception {

        String testString = "Sergey,35";
        BufferedReader bufferedReader= new BufferedReader(new StringReader(testString));
        person = personManagerFromFile.getPersonByName(bufferedReader,"Matt");
        assertNull(person.getName());
    }
}
