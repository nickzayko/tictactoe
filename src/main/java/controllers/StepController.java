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
    //....


    public String userStep = null;

    @RequestMapping("/step1")
    public String step1(HttpServletRequest request) {
        userStep = SaveUserStep(userStep, "1");
        saveSteps(request);
        return "/WEB-INF/pages/table.jsp";
    }

    @RequestMapping("/step2")
    public String step2(HttpServletRequest request) {
        userStep = SaveUserStep(userStep, "2");
        saveSteps(request);
        return "/WEB-INF/pages/table.jsp";
    }

    @RequestMapping("/step3")
    public String step3(HttpServletRequest request) {
        userStep = SaveUserStep(userStep, "3");
        saveSteps(request);
        return "/WEB-INF/pages/table.jsp";
    }

    @RequestMapping("/step4")
    public String step4(HttpServletRequest request) {
        userStep = SaveUserStep(userStep, "4");
        saveSteps(request);
        return "/WEB-INF/pages/table.jsp";
    }

    @RequestMapping("/step5")
    public String step5(HttpServletRequest request) {
        userStep = SaveUserStep(userStep, "5");
        saveSteps(request);
        return "/WEB-INF/pages/table.jsp";
    }

    @RequestMapping("/step6")
    public String step6(HttpServletRequest request) {
        userStep = SaveUserStep(userStep, "6");
        saveSteps(request);
        return "/WEB-INF/pages/table.jsp";
    }

    @RequestMapping("/step7")
    public String step7(HttpServletRequest request) {
        userStep = SaveUserStep(userStep, "7");
        saveSteps(request);
        return "/WEB-INF/pages/table.jsp";
    }

    @RequestMapping("/step8")
    public String step8(HttpServletRequest request) {
        userStep = SaveUserStep(userStep, "8");
        saveSteps(request);
        return "/WEB-INF/pages/table.jsp";
    }

    @RequestMapping("/step9")
    public String step9(HttpServletRequest request) {
        userStep = SaveUserStep(userStep, "9");
        saveSteps(request);
        return "/WEB-INF/pages/table.jsp";
    }

    //метод запоминает комбинацию ходов пользователя , например 951 и это победа
    private String SaveUserStep(String userStep, String stepValue) {
        if (userStep == null) {
            userStep = stepValue;
        } else {
            userStep = userStep + stepValue;
        }
        return userStep;
    }

    // метод запоминает куда пользователь походит и делает отрисовку "Х" в таблице
    private String saveSteps(HttpServletRequest request) {
        for (int i = 0; i < userStep.length(); i++) {
            int tmp = Integer.parseInt(String.valueOf(userStep.charAt(i)));
            request.setAttribute("v" + (tmp), "X");
        }
        return "/WEB-INF/pages/table.jsp";
    }

    //метод проверки на выигрыш, ДОДЕЛАЙ ЗАВТРА!!!!
    private boolean checkVictory (String userStep){
        int tmp [] = new int[userStep.length()];
        if (userStep.length() >= 2){
            for (int i = 0; i < userStep.length(); i++){
                tmp[i] = userStep.charAt(i);
            }
        }
        return true;
    }


}
