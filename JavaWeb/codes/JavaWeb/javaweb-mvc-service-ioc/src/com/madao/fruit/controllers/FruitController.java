package com.madao.fruit.controllers;

import com.madao.fruit.service.FruitService;
import com.madao.fruit.pojo.Fruit;
import com.madao.myssm.myspringmvc.ViewBaseServlet;
import com.madao.myssm.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


public class FruitController extends ViewBaseServlet {

    private FruitService fruitService = null;


    private String index(String oper, String keyword, Integer pageNo,  HttpServletRequest request) {

        HttpSession session = request.getSession();

        if(pageNo == null)
            pageNo = 1;

        if (StringUtil.isNotEmpty(oper) && "search".equals(oper)) {
            pageNo = 1;
            if (StringUtil.isEmpty(keyword)) {
                keyword = "";
            }
            session.setAttribute("keyword", keyword);
        } else {
            Object keywordObj = session.getAttribute("keyword");
            if(keywordObj != null)
                keyword = (String)keywordObj;
            else
                keyword = "";
        }


        session.setAttribute("pageNo", pageNo);

        List<Fruit> fruitList = fruitService.getFruitList(keyword, pageNo);
        session.setAttribute("fruitList", fruitList);

        int pageCount = fruitService.getPageCount(keyword);
        session.setAttribute("pageCount", pageCount);

        /*
         * 此处的视图名称是index,那么thymeleaf会将这个逻辑视图对应到物理视图名称上去
         * 逻辑视图名称：index
         * 物理视图名称：view-prefix + 逻辑视图名称 + view-suffix
         * 所以真实的视图名称是 `/index.html`
         */
//        super.processTemplate("index", request, response);
        return "index";
    }


    private String add(String fname, Integer price, Integer fcount, String remark) {

        Fruit fruit = new Fruit(0, fname, price, fcount, remark);
        fruitService.addFruit(fruit);
        return "redirect:fruit.do";
    }

    private String del(Integer fid) {
        if (fid != null) {
            fruitService.delFruit(fid);
            return "redirect:fruit.do";
        }
        return "error";

    }

    private String edit(Integer fid, HttpServletRequest request) {

        if (fid != null) {
            Fruit fruit = fruitService.getFruitByFid(fid);
            request.setAttribute("fruit", fruit);
//            super.processTemplate("edit", request, response);
            return "edit";
        }
        return "error2";

    }

    private String update(Integer fid, String fname, Integer price, Integer fcount, String remark){

        fruitService.updateFruit(new Fruit(fid, fname, price, fcount, remark));
        return "redirect:fruit.do";
    }

}
