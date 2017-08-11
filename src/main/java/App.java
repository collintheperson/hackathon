import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;
import models.Team;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/",(request, response) -> {
            Map<String,Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        },   new HandlebarsTemplateEngine());

        get("/teams/new",(request, response) -> {
            Map<String,Object> model = new HashMap<>();
            return new ModelAndView(model, "team-form.hbs");
        },   new HandlebarsTemplateEngine());

        post("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();

            String content = request.queryParams("name");
            String content2 = request.queryParams("description");
            System.out.println(content);
            System.out.println(content2);
            Team newTeam = new Team(content,content2);
            model.put("team", newTeam);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
        //get: show all posts

        get("/teams", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Team> teams = Team.getAll();
            model.put("teams", teams);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
