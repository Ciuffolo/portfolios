/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.redoddity.portfolios.controller;

import it.redoddity.portfolios.dao.ProjectDAO;
import it.redoddity.portfolios.model.Project;
import it.redoddity.portfolios.model.User;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author daniel
 */
@Scope("prototype")
@Controller(value="project")
public class ProjectController extends ApplicationController{
    
    private ProjectDAO projectDAO;

    @Autowired
    public void setProjectDAO(ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }
    
    public void newproject() throws ServletException, IOException {
        render("new");
    }
    
    public void create() throws SQLException, ServletException, IOException {
        User user = getCurrentUser();
        Project project = new Project(user);
        project.bind(request.getParameterMap(), validator);
        if (project.isValid()) {
            projectDAO.create(project);
            redirect("dashboard");
        } else {
            request.setAttribute("project", project);
            render("new");
        }
    }
    
}
