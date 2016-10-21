package services;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QueryFactoryTest {
    @Test
    public void getPersonByNameQueryTest(){

        String expectedName = "SELECT * FROM person WHERE NAME = ?";
        assertEquals(expectedName, new QueryFactory().getQuery("GET_PERSON_BY_NAME"));
    }

    @Test
    public void getPersonQueryTest(){

        String expectedName = "SELECT * FROM person  where idPerson=(SELECT MAX(idPerson) FROM person)";
        assertEquals(expectedName, new QueryFactory().getQuery("GET_PERSON"));
    }

    @Test
    public void getPersonByAgeQueryTest(){

        String expectedName = "SELECT * FROM person WHERE AGE = ?";
        assertEquals(expectedName, new QueryFactory().getQuery("GET_PERSON_BY_AGE"));
    }
}
