package servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;

import beans.PreArrivalForm;
import dao.*;

@WebServlet(urlPatterns= {"/submitPaf","/submitBR","/customs","/shipAgent"})
public class FrontController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    @JDBC
    AgentDAO agentDao;
    MasterDAO masterDao;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Action action = null;
        String operation = request.getRequestURI();
        System.out.println(operation);
        if (operation.equals("/SoftHavenLL")) {
            request.getRequestDispatcher("Login.jsp").forward(request, response);

        } else if (operation.equals("/SoftHavenLL/shipMaster")) {
            try {
                String sName = request.getParameter("sName");
                String callSign = request.getParameter("callSign");
                int imo = Integer.parseInt(request.getParameter("agent"));
                String agentInfo = request.getParameter("agentInfo");
                String aFrom = request.getParameter("aFrom");
                int eta = Integer.parseInt(request.getParameter("eta"));
                int bNum = Integer.parseInt(request.getParameter("bNum"));
                String nextPort = request.getParameter("nextPort");
                int etd = Integer.parseInt(request.getParameter("etd"));
                String disCargoD = request.getParameter("disCargoD");
                int disCargoA = Integer.parseInt(request.getParameter("disCargoA"));
                String loadCargoD = request.getParameter("loadCargoD");
                int loadCargoA = Integer.parseInt(request.getParameter("loadCargoA"));
                int nPassArr = Integer.parseInt(request.getParameter("nPassArr"));
                int nPassDep = Integer.parseInt(request.getParameter("nPassDep"));

                PreArrivalForm paf = new PreArrivalForm(sName, callSign, imo, agentInfo, aFrom, eta, bNum, nextPort, etd,
                        disCargoD, disCargoA, loadCargoD, loadCargoA, nPassArr, nPassDep);
                masterDao.addPaf(paf);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                System.out.println("Exception");
            }
            request.getRequestDispatcher("shipMaster.jsp").forward(request,response);

        }else if(operation.equals("/SoftHavenLL/submitPaf")){
            request.getRequestDispatcher("submitted.jsp").forward(request,response);
        }else if(operation.equals("/SoftHavenLL/shipAgent")) {
            request.getRequestDispatcher("shipAgent.jsp").forward(request, response);
        }else if(operation.equals("/SoftHavenLL/submitBR")){
            request.getRequestDispatcher("submittedBR.jsp");
        }else if(operation.equals("/SoftHavenLL/customs")){
            request.getRequestDispatcher("Customs.jsp");
        }
    }
}