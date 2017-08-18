package dao;

import models.Member;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;


public class Sql2oMemberDaoTest {

    private Sql2oMemberDao memberDao;
    private Connection con;
    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        memberDao = new Sql2oMemberDao(sql2o); //ignore me for now

        //keep connection open through entire test so it does not get erased.
        con = sql2o.open();

    }

    @After
    public void tearDown() throws Exception {
        con.close();

    }
    @Test
    public void addingMemberSetsId() throws Exception {
        System.out.println();
        Member member = setupNewMember();
        Member member1 = new Member("a",1):
        int originalMemberId = member.getId();
        memberDao.add(member);
        assertNotEquals(originalMemberId, member.getId()); //how does this work?
    }
    public Member setupNewMember()  {
        return new Member("Frank Ocean", 112);
    }
}