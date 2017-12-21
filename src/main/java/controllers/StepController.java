package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/in")
public class StepController {

    @RequestMapping("/step1")
    public String step1 (HttpServletRequest request) {
        int st = 1;
        return "/WEB-INF/pages/table.jsp";
    }

    @RequestMapping("/step2")
    public String step2 (HttpServletRequest request) {
        int st = 2;
        return "/WEB-INF/pages/table.jsp";
    }

    @RequestMapping("/step3")
    public String step3 (HttpServletRequest request) {
        int st = 3;
        return "/WEB-INF/pages/table.jsp";
    }

    @RequestMapping("/step4")
    public String step4 (HttpServletRequest request) {
        int st = 4;
        return "/WEB-INF/pages/table.jsp";
    }

    @RequestMapping("/step5")
    public String step5 (HttpServletRequest request) {
        int st = 5;
        return "/WEB-INF/pages/table.jsp";
    }

    @RequestMapping("/step6")
    public String step6 (HttpServletRequest request) {
        int st = 6;
        return "/WEB-INF/pages/table.jsp";
    }

    @RequestMapping("/step7")
    public String step7 (HttpServletRequest request) {
        int st = 7;
        return "/WEB-INF/pages/table.jsp";
    }

    @RequestMapping("/step8")
    public String step8 (HttpServletRequest request) {
        int st = 8;
        return "/WEB-INF/pages/table.jsp";
    }

    @RequestMapping("/step1")
    public String step9 (HttpServletRequest request) {
        int st = 9;
        return "/WEB-INF/pages/table.jsp";
    }
}
