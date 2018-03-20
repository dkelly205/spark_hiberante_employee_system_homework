package controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import db.DBHelper;
import models.Department;
import models.Engineer;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class EngineersController {


    public EngineersController() {
        this.setupEndPoints();
    }

    private static void setupEndPoints(){
        get("/engineers", (req, res) ->{

            Map<String, Object> model = new HashMap();
            List<Engineer> engineers = DBHelper.getAll(Engineer.class);
            model.put("template", "templates/engineers/index.vtl");
            model.put("engineers", engineers);
            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());


        get("/engineers/new", (req, res)->{

            Map<String, Object> model = new HashMap<>();
            List<Department> departments = DBHelper.getAll(Department.class);

            model.put("departments", departments);
            model.put("template", "templates/engineers/create.vtl");

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        post("/engineers", (req, res)->{

            String firstName = req.queryParams("firstName");
            String lastName = req.queryParams("lastName");
            int salary = Integer.parseInt(req.queryParams("salary"));
            int departmentId = Integer.parseInt(req.queryParams("department"));
            Department department = DBHelper.find(departmentId, Department.class);

            Engineer engineer = new Engineer(firstName, lastName, salary, department);
            DBHelper.save(engineer);

            res.redirect("/engineers");
            return null;

        }, new VelocityTemplateEngine());

        get("/engineers/:id/edit", (req, res)->{
            Map<String,Object> model = new HashMap<>();
            Engineer engineer = DBHelper.find(,Engineer.class);




        })
    }
}
