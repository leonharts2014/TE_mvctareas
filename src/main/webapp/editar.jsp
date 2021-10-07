<%@page import="com.emergentes.modelo.Tarea"%>
<%
    Tarea item = (Tarea) request.getAttribute("miTarea");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <h1><%= (item.getId() ==0 )?"Nuevo Registro":"Editar registro" %></h1>
        <form action="MainController" method="post">
            <input type="hidden" name="id" value="<%= item.getId() %>">
            <table>
                <tr>
                    <td>Tarea</td>
                    <td><input type="text" name="tarea" value="<%= item.getTarea() %>"></td>
                </tr>
                <tr>
                    <td>Prioridad</td>
                    <td><select name="prioridad" value="<%= item.getCompletado()%>"/>
                                <option value="Alta">1</option>
                                <option value="Media">2</option>
                                <option value="Baja">3</option>
                    </td>
                </tr>
                <tr>
                    <td>Completado</td>
                    <td><input type="checkbox" name="completado"  value="Concluido"<%= item.getCompletado()%>/>1</td>
                    <td><input type="checkbox" name="completado"  value="Pendiente"<%= item.getCompletado()%>/>2</td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Enviar"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
