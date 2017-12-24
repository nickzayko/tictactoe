package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;



@Controller
@RequestMapping("/in")
public class StepController {


    // на данный момент не задействован
    @Autowired
    public AddSymbolService service;

    public String userStep = null;

    @RequestMapping("/step1")
    public String step1 (HttpServletRequest request) {
        checkAndSaveUserStep(userStep, "1");
//        userStep = userStep + String.valueOf(1);
        request.setAttribute("v1", userStep);
        return "/WEB-INF/pages/table.jsp";
    }

    @RequestMapping("/step2")
    public String step2 (HttpServletRequest request) {
        checkAndSaveUserStep(userStep, "2");
//        userStep = userStep + String.valueOf(2);
        request.setAttribute("v2", "X");
        return "/WEB-INF/pages/table.jsp";
    }

    @RequestMapping("/step3")
    public String step3 (HttpServletRequest request) {
//        userStep = userStep + String.valueOf(3);
        request.setAttribute("v3", "X");
        return "/WEB-INF/pages/table.jsp";
    }

    @RequestMapping("/step4")
    public String step4 (HttpServletRequest request) {
//        userStep = userStep + String.valueOf(4);
        request.setAttribute("v4", "X");
        return "/WEB-INF/pages/table.jsp";
    }

    @RequestMapping("/step5")
    public String step5 (HttpServletRequest request) {
//        userStep = userStep + String.valueOf(5);
        request.setAttribute("v5", "X");
        return "/WEB-INF/pages/table.jsp";
    }

    @RequestMapping("/step6")
    public String step6 (HttpServletRequest request) {
//        userStep = userStep + String.valueOf(6);
        request.setAttribute("v6", "X");
        return "/WEB-INF/pages/table.jsp";
    }

    @RequestMapping("/step7")
    public String step7 (HttpServletRequest request) {
//        userStep = userStep + String.valueOf(7);
        request.setAttribute("v7", "X");
        return "/WEB-INF/pages/table.jsp";
    }

    @RequestMapping("/step8")
    public String step8 (HttpServletRequest request) {
//        userStep = userStep + String.valueOf(8);
        request.setAttribute("v8", "X");
        return "/WEB-INF/pages/table.jsp";
    }

    @RequestMapping("/step9")
    public String step9 (HttpServletRequest request) {
//        userStep = userStep + String.valueOf(9);
        request.setAttribute("v9", "X");
        return "/WEB-INF/pages/table.jsp";
    }

    private String checkAndSaveUserStep(String userStep, String stepValue) {
        if (userStep == null){
            userStep = stepValue;
        } else {
            userStep = userStep + stepValue;
        }
        return userStep;
    }





}
