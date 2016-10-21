package managers;


import beans.Person;
import org.junit.Test;
import org.mockito.InjectMocks;

public class PersonManagerFromDBFactoryTest {

    @InjectMocks
    PersonManagerFromDB personManagerFromDB = new PersonManagerFromDB();


    @Test
    public void readAllPersonFromDBTest() {

    }

    @Test
    public void readPersonByNameFromDBTest() {
        String  name = "Alex";

    }

    @Test
    public void createPersonTest() {

    }

}
