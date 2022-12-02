

import java.io.IOException; 

import java.io.PrintWriter; 

import java.sql.Connection; 

import java.sql.DriverManager; 

import java.sql.ResultSet; 

import java.sql.SQLException; 

import java.sql.Statement; 

  

import javax.servlet.ServletException; 

import javax.servlet.annotation.WebServlet; 

import javax.servlet.http.HttpServlet; 

import javax.servlet.http.HttpServletRequest; 

import javax.servlet.http.HttpServletResponse; 

  


public class city extends HttpServlet { 

  /**
   * Set jdbc connection
   */

    private static final long serialVersionUID = 1L; 

    final String URL = "jdbc:mysql://localhost:3306/world";
    final String USER = "root"; 

    final String PASSWORD = ""; 

    final String DRIVER = "com.mysql.cj.jdbc.Driver"; 

    Connection ct = null; 

  

    public void init() throws ServletException { 

  

     
        try { 

            Class.forName(DRIVER); 

            ct = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", ""); 

        } catch (ClassNotFoundException | SQLException e) { 

            e.printStackTrace(); 

        } 

  

    } 

  

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 

  

        try { 

  

/**
   * Set html format for data query.
   */

           

            response.setContentType("text/html"); 

            PrintWriter pw = response.getWriter(); 

  

            pw.println("<html><body>"); 

            pw.println("<h3>City</h3>"); 

            pw.println("<table border=1><tr>" + "<td><b>name</b></td>" + "<td><b>countrycode</b></td>" + "<td><b>District</b></td>"

                    + "<td><b>Population</b></td>

   /**
   * Set query statement to retrieve from db.
   */

         
            Statement tm = ct.createStatement(); 

            String lo = "SELECT name,countrycode,district,population from city order by population DESC";
            ResultSet gh = tm.executeQuery(lo); 

  

           
            while (gh.next()) {  

                String Name = gh.getString(1); 

                String countrycode = gh.getString(2);
                
                String District = gh.getString(3); 

                String Population = gh.getString(4);

  

                pw.println("<tr>"  + "<td>" + Name + "</td>" + "<td>" +countrycode+ "</td>"

                        + "<td>" + District + "</td>" + "<td>" + Population + "</td>" + "<td>"); 

  

            } 

            pw.println("</table></body></html>"); 

  

/**
   * s Close connections 
   */

         
            gh.close(); 

            tm.close(); 

            pw.close(); 

  

        } catch (SQLException e) { 

            e.printStackTrace(); 

        } 

  

    } 

  

    public void destroy() { 

  

      

        try { 

            ct.close(); 

        } catch (SQLException e) { 

            e.printStackTrace(); 

        } 

    } 

  
}
