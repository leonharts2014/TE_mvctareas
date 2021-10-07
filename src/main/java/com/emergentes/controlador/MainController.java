
package com.emergentes.controlador;

import com.emergentes.modelo.Tarea;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession();
        
        if(ses.getAttribute("listaTarea") == null){
            ArrayList<Tarea> listaux = new ArrayList<Tarea>();
            ses.setAttribute("listaTarea", listaux);
        }
        
        ArrayList<Tarea> lista = (ArrayList<Tarea>)ses.getAttribute("listaTarea");
        
        
        String op = request.getParameter("op");
        String opcion = (op != null) ? op : "view";
        
        Tarea obj1 = new Tarea();
        int id, pos;
        
        switch(opcion){
            case"nuevo":
                request.setAttribute("miTarea", obj1);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "editar":
                id = Integer.parseInt(request.getParameter("id"));
                pos = buscarIndice(request, id);
                obj1 = lista.get(pos);
                request.setAttribute("miTarea", obj1);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "eliminar":
                id = Integer.parseInt(request.getParameter("id"));
                pos = buscarIndice(request, id);
                lista.remove(pos);
                ses.setAttribute("listaTarea", lista);
                response.sendRedirect("index.jsp");
                break;
            case "view":
                response.sendRedirect("index.jsp");
        }
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession();
        ArrayList<Tarea> lista = (ArrayList<Tarea>)ses.getAttribute("listaTarea");
        
        Tarea obj1 = new Tarea();
        
        obj1.setId(Integer.parseInt(request.getParameter("id")));
        obj1.setTarea(request.getParameter("tarea"));
        obj1.setPrioridad(request.getParameter("prioridad"));
        obj1.setCompletado(request.getParameter("completado"));
        
        int idt = obj1.getId();
        
        if(idt == 0){
            //Nuevo
            //Actualizar el ultimo id
            int ultID;
            ultID = ultimoId(request);
            obj1.setId(ultID);
            lista.add(obj1);
        }
        else{
            //Edicion
            lista.set(buscarIndice(request,idt), obj1);
        }
        ses.setAttribute("listaTarea", lista);
        response.sendRedirect("index.jsp");
       
    }

    
    private int buscarIndice(HttpServletRequest request, int id){
        HttpSession ses = request.getSession();
        ArrayList<Tarea> lista = (ArrayList<Tarea>)ses.getAttribute("listaTarea");
        
        int i = 0;
        
        if(lista.size() > 0){
            while(i < lista.size()){
                if(lista.get(i).getId() == id){
                    break;
                }
                else{
                    i++;
                }
            }
        }
        return i;
    }
    
    private int ultimoId(HttpServletRequest request){
        HttpSession ses = request.getSession();
        ArrayList<Tarea> lista = (ArrayList<Tarea>)ses.getAttribute("listaTarea");
        
        int idaux = 0;
        for(Tarea item: lista){
            idaux = item.getId();
        }
        return idaux + 1;
    }
}
