//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//import spark.ModelAndView;
//import spark.template.handlebars.HandlebarsTemplateEngine;
//import static spark.Spark.*;
//import models.Team;
//
//public class App {
//    public static void main(String[] args) {
//        staticFileLocation("/public");
//        Team.clearAllTeams();
//
//        get("/",(request, response) -> {
//            Map<String,Object> model = new HashMap<>();
//            ArrayList<Team> teams = Team.getAll();
//            model.put("teams", teams);
//            return new ModelAndView(model, "index.hbs");
//        },   new HandlebarsTemplateEngine());
//
//        get("/teams/new",(request, response) -> {
//            Map<String,Object> model = new HashMap<>();
//            return new ModelAndView(model, "team-form.hbs");
//        },   new HandlebarsTemplateEngine());
//
//        post("/teams/new", (request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//
//            String name = request.queryParams("name");
//            String description = request.queryParams("description");
//            Team newTeam = new Team(name,description);
//            model.put("team", newTeam);
//            return new ModelAndView(model, "team-form.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        get("/teams", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            ArrayList<Team> teams = Team.getAll();
//            model.put("teams", teams);
//            return new ModelAndView(model, "index.hbs");
//        }, new HandlebarsTemplateEngine());
//
//
//        get("/teams/:id", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            int idOfPostToFind = Integer.parseInt(req.params("id")); //pull id - must match route segment
//            Team foundTeam = Team.findById(idOfPostToFind); //use it to find post
//            model.put("teams", foundTeam); //add it to model for template to display
//            return new ModelAndView(model, "team-detail.hbs"); //individual post page.
//        }, new HandlebarsTemplateEngine());
//
//        get("/teams/:id/update", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            int idOfPostToEdit = Integer.parseInt(req.params("id"));
//            Team editTeam = Team.findById(idOfPostToEdit);
//            model.put("editTeams", editTeam);
//            return new ModelAndView(model, "team-form.hbs");
//        }, new HandlebarsTemplateEngine());
//
//
//        post("/teams/:id/update", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            String newName = req.queryParams("name");
//            String newDescription = req.queryParams("description");
//            System.out.println(Integer.parseInt(req.params("id")));
//            int idOfTeamToEdit = Integer.parseInt(req.params("id"));
//            Team editTeam = Team.findById(idOfTeamToEdit);
//            editTeam.update(newName,newDescription);
//            return new ModelAndView(model, "team-form.hbs");
//        }, new HandlebarsTemplateEngine());
//
//
//        post("/teams/:id/update", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            String newName = req.queryParams("name");
//            String newDescription = req.queryParams("description");
//            int idOfTeamToEdit = Integer.parseInt(req.params("id"));
//            Team editTeam = Team.findById(idOfTeamToEdit);
//            editTeam.update(newName,newDescription);
//            System.out.println("where does this get lost3" + editTeam);
//            return new ModelAndView(model, "index.hbs");
//        }, new HandlebarsTemplateEngine());
//    }
//}
