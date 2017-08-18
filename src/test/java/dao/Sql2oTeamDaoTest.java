package dao;

import models.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;


import static org.junit.Assert.*;

/**
 * Created by Guest on 8/18/17.
 */
public class Sql2oTeamDaoTest {

    private Sql2oTeamDao teamDao;
    private Connection con;



    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        teamDao = new Sql2oTeamDao(sql2o); //ignore me for now

        //keep connection open through entire test so it does not get erased.
        con = sql2o.open();

    }

    @After
    public void tearDown() throws Exception {
        con.close();
    }
    @Test
    public void addingTeamSetsId() throws Exception {
        System.out.println();
        Team team = setupNewTeam();
        int originalTeamId = team.getId();
        teamDao.add(team);
        assertNotEquals(originalTeamId, team.getId()); //how does this work?
    }



    //Helper
    public Team setupNewTeam()  {
        return new Team("The water people", "desert");
    }
}