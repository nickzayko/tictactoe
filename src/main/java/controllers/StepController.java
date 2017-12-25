package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


@Controller
@RequestMapping("/in")
public class StepController {
    private static String VICTORYCOMBINATION1 = "123";
    private static String VICTORYCOMBINATION2 = "456";
    private static String VICTORYCOMBINATION3 = "789";
    private static String VICTORYCOMBINATION4 = "147";
    private static String VICTORYCOMBINATION5 = "258";
    private static String VICTORYCOMBINATION6 = "369";
    private static String VICTORYCOMBINATION7 = "159";
    private static String VICTORYCOMBINATION8 = "357";


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
        if (checkVictory(userStep)) {
            request.setAttribute("victory", "THE USER IS WINNER!!!   GAME OVER!!! PRESS BUTTON To Restart Game");
            userStep = null;
        }
//        request.setAttribute("victory", checkVictory(userStep));
        return "/WEB-INF/pages/table.jsp";
    }

    //метод проверки на выигрыш
    public boolean checkVictory(String userStep) {
        Boolean check = false;
        String sortedUserCombination = null;
        //создаем из массив из комбинации ходов пользователя, сортируем его по возрастанию и сравниваем с победной комбинацией
        String tmp[] = new String[userStep.length()];
        if (userStep.length() >= 3) {
            for (int i = 0; i < userStep.length(); i++) {
                tmp[i] = String.valueOf(userStep.charAt(i));
            }
            Arrays.sort(tmp);
            sortedUserCombination = String.valueOf(tmp[0]);
            for (int i = 1; i < userStep.length(); i++) {
                sortedUserCombination = sortedUserCombination + String.valueOf(tmp[i]);
            }
            if (sortedUserCombination.contains(VICTORYCOMBINATION1) || sortedUserCombination.contains(VICTORYCOMBINATION2) || sortedUserCombination.contains(VICTORYCOMBINATION3) || sortedUserCombination.contains(VICTORYCOMBINATION4) || sortedUserCombination.contains(VICTORYCOMBINATION5) || sortedUserCombination.contains(VICTORYCOMBINATION6) || sortedUserCombination.contains(VICTORYCOMBINATION7) || sortedUserCombination.contains(VICTORYCOMBINATION8)) {
                check = true;
            }
        }
        return check;
    }

    @RequestMapping("/restartGame")
    public String restartGame(HttpServletRequest request) {
        userStep = null;
        return "/index.jsp";
    }


}
