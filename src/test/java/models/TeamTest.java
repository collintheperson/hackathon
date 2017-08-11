package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Guest on 8/11/17.
 */
public class TeamTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void NewObjectsGetCorrectlyCreated_true() throws Exception   {
        Team team = new Team("Codeagedon","Team of coders from Epicodus");
    assertEquals(true,team instanceof Team);
    }
    @Test
    public void TeamInstantiatesWithNewContent_true()   throws Exception    {
        Team team = new Team("Codeagedon","Team of coders from Epicodus");
        assertEquals("Codeagedon", team.getName());
    }
    @Test
    public void TeamWillContainAllInfo_true()   throws Exception    {
        Team team = new Team("Codeagedon","Team of coders from Epicodus");
        Team team2 = new Team("Moonrise Coders","Coders that happen to be big fans of Wes Anderson");
        assertTrue(Team.getAll().contains(team));
        assertTrue(Team.getAll().contains(team2));
    }
    @Test
    public void TeamsWillBeCorrectlyReturned_true() throws Exception    {
        Team team = new Team ("Codegagedon","Team of coders from Epicodus");
        assertEquals(2,Team.getAll().size());
    }
}