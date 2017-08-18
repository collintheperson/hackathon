package dao;

import models.Member;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

/**
 * Created by Guest on 8/18/17.
 */
public class Sql2oMemberDao implements MemberDao {

    private final Sql2o sql2o;

    public Sql2oMemberDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public void add(Member member) {
        String sql = "INSERT INTO members (memberName, badge) VALUES (:memberName,:badge)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .addParameter("memberName", member.getMemberName())
                    .addParameter("badge", member.getBadgeNumber())
                    .addColumnMapping("NAME", "memberName")
                    .addColumnMapping("DESCRIPTION", "badge")
                    .executeUpdate()
                    .getKey();
            member.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex); //oops we have an error!
        }
    }
}
