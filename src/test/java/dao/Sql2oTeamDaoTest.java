package dao;

import org.junit.After;
import org.junit.Before;
import org.sql2o.Sql2o;

import java.sql.Connection;

import static org.junit.Assert.*;

/**
 * Created by Guest on 8/18/17.
 */
public class Sql2oTeamDaoTest {

    private Sql2oTeamDao sql2oTeamDao;
    private Connection con;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        teamDao = new Sql2oReviewDao(sql2o); //ignore me for now

        //keep connection open through entire test so it does not get erased.
        con = sql2o.open();

    }

    @After
    public void tearDown() throws Exception {
        con.close();
    }
}