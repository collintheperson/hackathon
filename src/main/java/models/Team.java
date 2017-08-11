package models;

import java.util.ArrayList;

import static spark.Spark.staticFileLocation;

/**
 * Created by Guest on 8/11/17.
 */
public class Team {
        private String name;
        private String description;
        private static ArrayList<Team> instances = new ArrayList<>();
        private int id;

        public Team(String name, String description)    {
            this.name=name;
            this.description=description;
            instances.add(this);
            this.id=instances.size();
        }

    public static void clearAllTeams()  {
            instances.clear();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static ArrayList<Team> getAll() {
            return instances;
    }
    public int getId() {
        return id;
    }
    public static Team findById(int id) {
            return instances.get(id -1);
    }
    public void update(String name, String description )    {
            this.name=name;
            this.description=description;
    }
}
