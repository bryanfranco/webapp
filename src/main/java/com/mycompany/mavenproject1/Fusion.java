/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import simplejdbc.CustomerEntity;
import simplejdbc.DAOException;
import simplejdbc.DataSourceFactory;

/**
 *
 * @author pedago
 */
@WebServlet(name = "Fusion", urlPatterns = {"/Fusion"})
public class Fusion extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            DataSource myDataSource = DataSourceFactory.getDataSource();
            subDAO d = new subDAO(myDataSource);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Fusion</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Fusion at " + request.getContextPath() + "</h1>");
            ////////////////////////////////////////////////////////////////////////
            List<String> states = d.getAllStates();
            Iterator ite = states.iterator();
            out.printf("<FORM>");
            out.printf("<select name='etats'>");
            while(ite.hasNext()){
                String n = String.valueOf(ite.next());
                System.out.println(n.toString());
                out.printf("<option value= %s> %s</option>", n,n);
            }
            out.printf("</select>");
            out.printf("<button type='submit'>bouton</button>");
            out.printf("</FORM>");
            
            ////////////////////////////////////////////////////////////////////////
            out.printf("<table>");
            out.println("<tr>");
            out.println("<th> ID </th>");
            out.println("<th> Name </th>");
            out.println("<th> Adress </th>");
            out.println("</tr>");
            try{
                String val = request.getParameter("etats");
                List<CustomerEntity> cus = d.customersInState(val);
                Iterator it = cus.iterator();
                while(it.hasNext()){
                    CustomerEntity custom = (CustomerEntity) it.next();
                    out.println("<tr>");
                    out.printf("<th> %d </th>", custom.getCustomerId());
                    out.printf("<th> %s </th>", custom.getName());
                    out.printf("<th> %s </th>", custom.getAddressLine1());
                    out.println("</tr>");
                    
                }
            }catch(DAOException e){
                System.out.println("com.mycompany.mavenproject1.NewServlet.processRequest()");
            }
            out.printf("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
