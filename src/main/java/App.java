import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.Sql2oMemberDao;
import dao.Sql2oTeamDao;
import dao.Sql2oTeamDao;
import dao.TeamDao;
import models.Member;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;
import models.Team;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/todolist.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oTeamDao teamDao = new Sql2oTeamDao(sql2o);
        Sql2oMemberDao memberDao = new Sql2oMemberDao(sql2o);

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> teams = teamDao.getAll();
            model.put("teams", teams);

            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Member> members = memberDao.getAll();
            model.put("members", members);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

//        get: delete all teams
        get("/teams/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            teamDao.clearAllTeams();
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());


        get("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();

            String name = request.queryParams("name");
            String description = request.queryParams("description");
            Team newTeam = new Team(name, description);
            teamDao.add(newTeam);
            List<Team> teams = teamDao.getAll();
            model.put("team", teams);
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams/:id/members/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "member-form.hbs");
        }, new HandlebarsTemplateEngine());


        post("/teams/members/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String memberName = request.queryParams("memberName");
            int badge = Integer.parseInt(request.queryParams("badge"));
  //          int memberId = Integer.parseInt(request.queryParams("id"));
            Member newMember = new Member(memberName, badge);
            System.out.println(newMember);
            System.out.println(badge);
            memberDao.add(newMember);
            System.out.println(newMember);
            List<Team> teamList = teamDao.getAll();
            model.put("teams",teamList);
            List <Member> members = memberDao.getAll();
            model.put("newMember", members);
            
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/members", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Member> members = memberDao.getAll();
            model.put("members", members);
            return new ModelAndView(model, "member-detail.hbs");
                }, new HandlebarsTemplateEngine());

        get("/teams/:teamId/members/memberId",  (req,res) -> {
            Map<String,Object> model = new HashMap<>();
            Member member = memberDao.findById(Integer.parseInt(req.params("memberId")));
            model.put("member", member);
            List<Team> teams = teamDao.getAll();
            model.put("team",teams);
            return new ModelAndView(model, "member-detail.hbs");
        },   new HandlebarsTemplateEngine());

        get("/teams", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> teams = teamDao.getAll();
            model.put("teams", teams);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
//        get: delete an individual task

        get("/teams/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToFind = Integer.parseInt(req.params("id"));
            Team foundTeam = teamDao.findById(idOfTeamToFind); //use it to find post
            model.put("teams", foundTeam); //add it to model for template to display

            return new ModelAndView(model, "team-detail.hbs"); //individual post page.
        }, new HandlebarsTemplateEngine());

//        get("/teams/:id/delete", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            int idOfTeamToDelete = Integer.parseInt(req.params("id")); //pull id - must match route segment
//            //    Team deleteTeam = teamDao.findById(idOfTeamToDelete); //use it to find task
//            teamDao.deleteById(idOfTeamToDelete);
//            return new ModelAndView(model, "index.hbs");
//        }, new HandlebarsTemplateEngine());


        get("/teams/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("editTeam", true);
            List<Team> allTeam = teamDao.getAll();
            model.put("teams", allTeam);
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());


        get("/teams/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToEdit = Integer.parseInt(req.params("id"));
            Team editTeam = teamDao.findById(idOfTeamToEdit);
            model.put("editTeam", editTeam);
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());


        post("/teams/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToEdit = Integer.parseInt(req.params("id")); //What is up with this line
            String newName = req.queryParams("name");
            String newDescription = req.queryParams("description");
            Team editTeam = teamDao.findById(idOfTeamToEdit);

            teamDao.update(teamDao.findById(idOfTeamToEdit).getId(), newName, newDescription);
            List<Team> teams = teamDao.getAll();
            model.put("teams", teams);

            Team team = teamDao.findById(idOfTeamToEdit);
            model.put("id",idOfTeamToEdit);

            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

//        get("/teams/:teams_id/members/:members_id", (req, res) ->  {
//            Map<String, Object> model = new HashMap<>();
//            int idOfMemberToFind = Integer.parseInt(req.params("member_id"));
//            Member foundmember = memberDao.findById(req.params(idOfMemberToFind);
//            model.put("members",foundmember);
//            return new ModelAndView(model,"member-detail.hbs");
//        }, new HandlebarsTemplateEngine());
    }
}
