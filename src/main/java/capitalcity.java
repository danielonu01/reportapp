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

  


public class capitalcity extends HttpServlet { 

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

  

           

            response.setContentType("text/html"); 

            PrintWriter pw = response.getWriter(); 



  /**
   * set html format for jdbc data.
   */

            pw.println("<html><body>"); 

            pw.println("<h3>Capital city</h3>"); 

            pw.println("<table border=1><tr>" + "<td><b>name</b></td>" + "<td><b>country</b></td>" 

                    + "<td><b>Population</b></td>
   
   /**
   * Set query statement to retrieve data.
   */
         
            Statement tm = ct.createStatement(); 

            String lo = "SELECT name,country,population from country,city WHERE city.id=country.capital order by population DESC";
            ResultSet gh = tm.executeQuery(lo); 

  

           
            while (gh.next()) { 

                String name = gh.getString(1); 

                String country = gh.getString(2); 

                String Population = gh.getString(3);

  

                pw.println("<tr>" + "<td>" + name + "</td>" + "<td>" + "</td>"

                        + "<td>" + Population + "</td>" + "<td>"); 

  

            } 

            pw.println("</table></body></html>"); 

  

/**
   * close connection 
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
