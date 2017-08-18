package dao;

import models.Team;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

/**
 * Created by Guest on 8/18/17.
 */
public class Sql2oTeamDao implements TeamDao {

    private final Sql2o sql2o;

    public Sql2oTeamDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public void add(Team team) {
        String sql = "INSERT INTO teams (name, description) VALUES (:name,:description)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .addParameter("name", team.getName())
                    .addParameter("description", team.getDescription())
                    .addColumnMapping("NAME", "name")
                    .addColumnMapping("DESCRIPTION", "description")
                    .executeUpdate()
                    .getKey();
            team.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex); //oops we have an error!
        }
    }

    public Team findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM teams WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Team.class);
        }
    }

    public List<Team> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM teams") //raw sql
                    .executeAndFetch(Team.class); 
        }
    }
}

//




