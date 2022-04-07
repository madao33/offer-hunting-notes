package com.madao.fruit.servlets;

import com.madao.fruit.dao.FruitDAO;
import com.madao.fruit.dao.impl.FruitDAOImpl;
import com.madao.fruit.pojo.Fruit;
import com.madao.myssm.myspringmvc.ViewBaseServlet;
import com.madao.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/fruit.do")
public class FruitServlet extends ViewBaseServlet {

    private FruitDAO fruitDAO = new FruitDAOImpl();


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置编码
        request.setCharacterEncoding("UTF-8");

        String operate = request.getParameter("operate");
        if(StringUtil.isEmpty(operate)) {
            operate = "index";
        }

        switch (operate) {
            case "index":
                index(request, response);
                break;
            case "add":
                add(request, response);
                break;
            case "del":
                del(request, response);
                break;
            case "edit":
                edit(request, response);
                break;
            case "update":
                update(request, response);
                break;
            default :
                throw new RuntimeException("operate值非法");
//                break;
        }
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        Integer pageNo = 1;
        String oper = request.getParameter("oper");

        String keyword = null;
        if (StringUtil.isNotEmpty(oper) && "search".equals(oper)) {
            pageNo = 1;
            keyword = request.getParameter("keyword");
            if (StringUtil.isEmpty(keyword)) {
                keyword = "";
            }
            session.setAttribute("keyword", keyword);
        } else {
            String pageNoStr = request.getParameter("pageNo");
            if (StringUtil.isNotEmpty(pageNoStr))
                pageNo = Integer.parseInt(pageNoStr);

            Object keywordObj = session.getAttribute("keyword");
            if(keywordObj != null)
                keyword = (String)keywordObj;
            else
                keyword = "";
        }


        session.setAttribute("pageNo", pageNo);

        FruitDAO fruitDAO = new FruitDAOImpl();
        List<Fruit> fruitList = fruitDAO.getFruitList(keyword, pageNo);
        session.setAttribute("fruitList", fruitList);

        int fruitCount = fruitDAO.getFruitCount(keyword);
        int pageCount = (fruitCount + 5 - 1) / 5;
        session.setAttribute("pageCount", pageCount);

        /*
         * 此处的视图名称是index,那么thymeleaf会将这个逻辑视图对应到物理视图名称上去
         * 逻辑视图名称：index
         * 物理视图名称：view-prefix + 逻辑视图名称 + view-suffix
         * 所以真实的视图名称是 `/index.html`
         */
        super.processTemplate("index", request, response);
    }


    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String fname = request.getParameter("fname");
        Integer price = Integer.parseInt(request.getParameter("price"));
        Integer fcount = Integer.parseInt(request.getParameter("fcount"));
        String remark = request.getParameter("remark");

        Fruit fruit = new Fruit(0, fname, price, fcount, remark);

        fruitDAO.addFruit(fruit);

        response.sendRedirect("fruit.do");
    }

    private void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fidStr = request.getParameter("fid");
        if (StringUtil.isNotEmpty(fidStr)) {
            int fid = Integer.parseInt(fidStr);
            fruitDAO.delFruit(fid);

            response.sendRedirect("fruit.do");
        }

    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fidStr = request.getParameter("fid");
        if (StringUtil.isNotEmpty(fidStr)) {
            int fid = Integer.parseInt(fidStr);
            Fruit fruit = fruitDAO.getFruitByFid(fid);
            request.setAttribute("fruit", fruit);
            super.processTemplate("edit", request, response);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 设置编码
        request.setCharacterEncoding("utf-8");

        // 2. 获取参数
        String fidStr = request.getParameter("fid");
        Integer fid = Integer.parseInt(fidStr);
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        int price = Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");

        // 3.执行更新
        fruitDAO.updateFruit(new Fruit(fid, fname, price, fcount, remark));

        // 4. 资源调整
//        super.processTemplate("index", request, response);
        // 相当于request.getRequestDispatcher("index.html").forward(request, response);
        // 需要重新跳入index, 重新刷新
        response.sendRedirect("fruit.do");
    }
}
