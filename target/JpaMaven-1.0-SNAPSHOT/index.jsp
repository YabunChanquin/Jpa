<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import="Entities.Marcas"%>
<%@page import="Manager.MarcasJpaController"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="java.util.List"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.EntityManagerFactory"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
    </head>
    <body>
        <h1>JPA Fina_Form</h1>
        <%
            //EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_JpaMaven_war_1.0-SNAPSHOTPU");
            //EntityManager man=emf.createEntityManager();
            //Marcas obj_marcas = new Marcas();
            MarcasJpaController marc = null;
  //          List<Marcas> ls_marc = marc.findMarcasEntities();
            
        %>
    </body>
</html>
