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

  


public class country extends HttpServlet { 

  

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

  

            pw.println("<html><body>"); 

            pw.println("<h3>Country</h3>"); 

            pw.println("<table border=1><tr>" + "<td><b>code</b></td>" + "<td><b>Name</b></td>" + "<td><b>continent</b></td>"

                    +"<td><b>region</b></td>"+ "<td><b>Population</b></td>" ); 

  

         
            Statement tm = ct.createStatement(); 

            String lo = "SELECT code,name,continent,region,population from country order by population DESC;";
            ResultSet gh = tm.executeQuery(lo); 

  

           
            while (gh.next()) { 

                String code = gh.getString(1); 

                String Name = gh.getString(2); 
                
                String continent = gh.getString(3); 

                String region = gh.getString(4);

                String population = gh.getString(5);

  

                pw.println("<tr>" + "<td>" + code + "</td>" + "<td>" + Name + "</td>" + "<td>" + "</td>"

                        + "<td>" + continent + "</td>" + "<td>" + region + "</td>" + "<td>" + population + "</td>" + "<td>" ); 

  

            } 

            pw.println("</table></body></html>"); 

  

         
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