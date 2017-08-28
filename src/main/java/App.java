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
        String connectionString = "jdbc:h2:~/greatdatabase.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oTeamDao teamDao = new Sql2oTeamDao(sql2o);
        Sql2oMemberDao memberDao = new Sql2oMemberDao(sql2o);

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> teams = teamDao.getAll();
            model.put("teams", teams);

            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

//        get: delete all teams
        get("/teams/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            teamDao.clearAllTeams();
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete all members
        get("/members/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            memberDao.clearAllMembers();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

//      show a team form
        get("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());


        //process a new team
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

        //      show new member form
        get("/teams/:id/members/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int teamId = Integer.parseInt(request.params("id"));
            model.put("teamId",teamId);
            return new ModelAndView(model, "member-form.hbs");
        }, new HandlebarsTemplateEngine());

        //process a new member
        post("/teams/:id/members/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String memberName = request.queryParams("memberName");
            int badge = Integer.parseInt(request.queryParams("badge"));
            int teamId = Integer.parseInt((request.params("id")));
            Member newMember = new Member(memberName, badge, teamId);
            memberDao.add(newMember);
            List<Member> member = memberDao.getAll();
            model.put("members", newMember);
            response.redirect("/teams/" + teamId);
            return null;
        });
        //show all members
        get("/members", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Member> members = memberDao.getAll();
            model.put("members", members);
            return new ModelAndView(model, "member-detail.hbs");
                }, new HandlebarsTemplateEngine());


//        get("/teams/:teamId/members/memberId",  (req,res) -> {
//            Map<String,Object> model = new HashMap<>();
//            Member member = memberDao.findById(Integer.parseInt(req.params("memberId")));
//            model.put("members", member);
//            List<Team> teams = teamDao.getAll();
//            model.put("team",teams);
//            return new ModelAndView(model, "member-detail.hbs");
//        },   new HandlebarsTemplateEngine());
        //show all teams
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
            Team foundTeam = teamDao.findById(idOfTeamToFind);
//            Member members = memberDao.getAll();
//            model.put("members", members);
            model.put("teams", foundTeam);
            return new ModelAndView(model, "team-detail.hbs");
        }, new HandlebarsTemplateEngine());


        //form to update member
        get("/teams/:id/members/:membersId/update", (request, response) -> {
            Map<String,Object> model = new HashMap<>();
            int idOfMembermToEdit = Integer.parseInt(request.params("id"));
            Member editMember = memberDao.findById(idOfMembermToEdit);
            int memberId = Integer.parseInt(request.params("membersId"));
            model.put("memberId",memberId);
            model.put("editMember",editMember);
            return new ModelAndView(model, "member-form.hbs");
        }, new HandlebarsTemplateEngine());

//        process an updated member
      post("/teams/:id/members/:membersId/update", (request, response) -> {
          Map<String,Object> model =  new HashMap<>();
          String memberName = request.queryParams("membername");
          int badge = Integer.parseInt(request.queryParams("badge"));
          int id = Integer.parseInt(request.params("id"));
          int teamId = Integer.parseInt(request.params("teamId"));
          Team team = teamDao.findById(teamId);
          memberDao.update(id,memberName,badge);
          response.redirect("/teams" + teamId);
          return null;
      });

        get("/teams/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToEdit = Integer.parseInt(req.params("id"));
            Team editTeam = teamDao.findById(idOfTeamToEdit);
            model.put("editTeam", editTeam);
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());


        post("/teams/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToEdit = Integer.parseInt(req.params("id"));
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
