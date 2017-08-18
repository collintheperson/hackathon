package dao;

import org.sql2o.Sql2o;

/**
 * Created by Guest on 8/18/17.
 */
public class Sql2oTeamDao implements TeamDao {

    private final Sql2o sql2o;

    public Sql2oTeamDao(Sql2o sql2o)    {
        this.sql2o = sql2o;
    }
}
