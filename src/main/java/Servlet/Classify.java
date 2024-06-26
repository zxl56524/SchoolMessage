package Servlet;

import DBC.NewsOP;
import Execute.CharChange;
import Execute.News;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/Servlet.Classify")
public class Classify extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request , response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("post");
        response.addHeader(  "Access-Control-Allow-Origin", "*");
        response.addHeader(  "Access-Control-Allow-Method","POST , GET");
        String classify = new CharChange().ISO_to_UTF(request.getParameter("classify"));
        //String classify = request.getParameter("classify");
        String Sum = request.getParameter("sum");
        int sum = Integer.parseInt(Sum) * 10 + 1;

        //System.out.println(classify);
        ArrayList<News> ClassList = new NewsOP().find("classify" , classify);
        ArrayList<News> NewsList = new ArrayList<>();

        for(int i = sum - 1 ; (i < (sum + 10)) && (i < ClassList.size()) ; i ++){
            NewsList.add(ClassList.get(i));
        }
        PrintWriter out = response.getWriter();
        JSON json = new JSONArray(NewsList);
        out.print(json);
    }
}
