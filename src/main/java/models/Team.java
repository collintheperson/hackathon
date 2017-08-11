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

        public Team(String name, String description)    {
            this.name=name;
            this.description=description;
        }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static ArrayList<Team> getInstances() {
        return instances;
    }
}
