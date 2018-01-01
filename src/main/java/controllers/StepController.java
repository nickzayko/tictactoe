package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Random;


@Controller
@RequestMapping("/in")
public class StepController {
    //    private static String VICTORYCOMBINATION1 = "123";
//    private static String VICTORYCOMBINATION2 = "456";
//    private static String VICTORYCOMBINATION3 = "789";
//    private static String VICTORYCOMBINATION4 = "147";
//    private static String VICTORYCOMBINATION5 = "258";
//    private static String VICTORYCOMBINATION6 = "369";
//    private static String VICTORYCOMBINATION7 = "159";
//    private static String VICTORYCOMBINATION8 = "357";
    private static String ALLNUMBERCOMBINATION = "123456789";
    public char[] compStepCharArray = ALLNUMBERCOMBINATION.toCharArray();
    public String userStep = null;
    public String computerStep = null;
    Random random = new Random();
    int countOfNumbers = 9;


    // на данный момент не задействован
    @Autowired
    public AddSymbolService service;
    //....
    @RequestMapping("/step")
    private String step (HttpServletRequest request){
        String value = request.getParameter("stepParam");
        userStep = SaveUserStep(userStep, value);
        saveUserSteps(request);
        computerStep = SaveCompStep(userStep);
        printComputerSteps(request, computerStep);
        checkVictoryOfPlayers(userStep, computerStep, request);
        return "/WEB-INF/pages/table.jsp";
    }

//    @RequestMapping("/step1")
//    public String step1(HttpServletRequest request) {
//       String i = step(request, "1");
//        return i;
//    }
//
//    @RequestMapping("/step2")
//    public String step2(HttpServletRequest request) {
//        return  step(request, "2");
//    }
//
//
////    public String stepMain(HttpServletRequest request) {
////        request.getParameter()
////        return  step(request, "2");
////    }
//
//    @RequestMapping("/step3")
//    public String step3(HttpServletRequest request) {
//        userStep = SaveUserStep(userStep, "3");
//        saveUserSteps(request);
//        computerStep = SaveCompStep(userStep);
//        printComputerSteps(request, computerStep);
//        checkVictoryOfPlayers(userStep, computerStep, request);
//        return "/WEB-INF/pages/table.jsp";
//    }
//
//    @RequestMapping("/step4")
//    public String step4(HttpServletRequest request) {
//        userStep = SaveUserStep(userStep, "4");
//        saveUserSteps(request);
//        computerStep = SaveCompStep(userStep);
//        printComputerSteps(request, computerStep);
//        checkVictoryOfPlayers(userStep, computerStep, request);
//        return "/WEB-INF/pages/table.jsp";
//    }
//
//    @RequestMapping("/step5")
//    public String step5(HttpServletRequest request) {
//        userStep = SaveUserStep(userStep, "5");
//        saveUserSteps(request);
//        computerStep = SaveCompStep(userStep);
//        printComputerSteps(request, computerStep);
//        return "/WEB-INF/pages/table.jsp";
//    }
//
//    @RequestMapping("/step6")
//    public String step6(HttpServletRequest request) {
//        userStep = SaveUserStep(userStep, "6");
//        saveUserSteps(request);
//        computerStep = SaveCompStep(userStep);
//        printComputerSteps(request, computerStep);
//        checkVictoryOfPlayers(userStep, computerStep, request);
//        return "/WEB-INF/pages/table.jsp";
//    }
//
//    @RequestMapping("/step7")
//    public String step7(HttpServletRequest request) {
//        userStep = SaveUserStep(userStep, "7");
//        saveUserSteps(request);
//        computerStep = SaveCompStep(userStep);
//        printComputerSteps(request, computerStep);
//        checkVictoryOfPlayers(userStep, computerStep, request);
//        return "/WEB-INF/pages/table.jsp";
//    }
//
//    @RequestMapping("/step8")
//    public String step8(HttpServletRequest request) {
//        userStep = SaveUserStep(userStep, "8");
//        saveUserSteps(request);
//        computerStep = SaveCompStep(userStep);
//        printComputerSteps(request, computerStep);
//        checkVictoryOfPlayers(userStep, computerStep, request);
//        return "/WEB-INF/pages/table.jsp";
//    }
//
//    @RequestMapping("/step9")
//    public String step9(HttpServletRequest request) {
//        userStep = SaveUserStep(userStep, "9");
//        saveUserSteps(request);
//        computerStep = SaveCompStep(userStep);
//        printComputerSteps(request, computerStep);
//        checkVictoryOfPlayers(userStep, computerStep, request);
//        return "/WEB-INF/pages/table.jsp";
//    }



    //сброс игры
    @RequestMapping("/restartGame")
    public String restartGame(HttpServletRequest request) {
        userStep = null;
        computerStep = null;
        return "/index.jsp";
    }


    //метод запоминает комбинацию ходов пользователя , например 123 и это победа
    private String SaveUserStep(String userStep, String stepValue) {
        if (userStep == null) {
            userStep = stepValue;
        } else {
            userStep = userStep + stepValue;
        }
        return userStep;
    }

    // метод запоминает куда пользователь походит и делает отрисовку "Х" в таблице
    private String saveUserSteps(HttpServletRequest request) {
        for (int i = 0; i < userStep.length(); i++) {
            int tmp = Integer.parseInt(String.valueOf(userStep.charAt(i)));
            request.setAttribute("v" + (tmp), "X");
        }
        return "/WEB-INF/pages/table.jsp";
    }


    //учу комп ставить нолики
    // комп выбирает рандомно одну из оставшихся пустых ячеек, коммбинация ходов компа запоминается и возвращается
    private String SaveCompStep(String userStep) {
        String tmp[] = new String[userStep.length()];
        String sortedUserStep = null;
        String tmpComp[] = new String[9 - userStep.length()];
        String symbolSelectedByComp = null;
        int countForArray = 0;

        //сортирую комбинацию ходов пользователя по возрастанию
        for (int i = 0; i < userStep.length(); i++) {
            tmp[i] = String.valueOf(userStep.charAt(i));
        }
        Arrays.sort(tmp);
        sortedUserStep = String.valueOf(tmp[0]);
        for (int i = 1; i < userStep.length(); i++) {
            sortedUserStep = sortedUserStep + String.valueOf(tmp[i]);
        }
        ///****

        //создаю массив, который укажет на пустые ячейки
        for (int i = 0; i < compStepCharArray.length; i++) {
            if (!sortedUserStep.contains(String.valueOf(compStepCharArray[i]))) {
                tmpComp[countForArray] = String.valueOf(compStepCharArray[i]);
                countForArray++;
            }
        }
        ///****

        symbolSelectedByComp = String.valueOf(tmpComp[random.nextInt(tmpComp.length)]);

        if (computerStep == null) {
            computerStep = symbolSelectedByComp;
        } else {
            computerStep = computerStep + symbolSelectedByComp;
        }
        return computerStep;
    }

    // отрисовка в таблице ходов компа
    private String printComputerSteps(HttpServletRequest request, String computerStep) {
        for (int i = 0; i < computerStep.length(); i++) {
            int tmp = Integer.parseInt(String.valueOf(computerStep.charAt(i)));
            request.setAttribute("v" + (tmp), "O");
        }
        return "/WEB-INF/pages/table.jsp";
    }

    //Проверка и выявление победителя
    private String checkVictoryOfPlayers(String userStep, String computerStep, HttpServletRequest request) {
        Boolean checkUser = false;
        String sortedUserCombination = null;
        //создаем из массив из комбинации ходов пользователя, сортируем его по возрастанию и сравниваем с победной комбинацией
        String tmp[] = new String[userStep.length()];
        if (userStep.length() >= 1) {
            for (int i = 0; i < userStep.length(); i++) {
                tmp[i] = String.valueOf(userStep.charAt(i));
            }
            Arrays.sort(tmp);
            sortedUserCombination = String.valueOf(tmp[0]);
            for (int i = 1; i < userStep.length(); i++) {
                sortedUserCombination = sortedUserCombination + String.valueOf(tmp[i]);
            }
            if ((sortedUserCombination.contains("1") && ((sortedUserCombination.contains("2") && sortedUserCombination.contains("3")) || (sortedUserCombination.contains("4") && sortedUserCombination.contains("7")) || (sortedUserCombination.contains("5") && sortedUserCombination.contains("9")))) || (sortedUserCombination.contains("5") && ((sortedUserCombination.contains("4") && sortedUserCombination.contains("6")) || (sortedUserCombination.contains("2") && sortedUserCombination.contains("8")) || (sortedUserCombination.contains("3") && sortedUserCombination.contains("7")))) || (sortedUserCombination.contains("9") && ((sortedUserCombination.contains("7") && sortedUserCombination.contains("8")) || (sortedUserCombination.contains("3") && sortedUserCombination.contains("6"))))) {
                checkUser = true;
            }
        }
        Boolean checkComp = false;
        String sortedCompCombination = null;
        //создаем из массив из комбинации ходов пользователя, сортируем его по возрастанию и сравниваем с победной комбинацией
        String tmpC[] = new String[computerStep.length()];
        if (computerStep.length() >= 1) {
            for (int i = 0; i < computerStep.length(); i++) {
                tmpC[i] = String.valueOf(computerStep.charAt(i));
            }
            Arrays.sort(tmpC);
            sortedCompCombination = String.valueOf(tmpC[0]);
            for (int i = 1; i < computerStep.length(); i++) {
                sortedCompCombination = sortedCompCombination + String.valueOf(tmpC[i]);
            }
            if ((sortedCompCombination.contains("1") && ((sortedCompCombination.contains("2") && sortedCompCombination.contains("3")) || (sortedCompCombination.contains("4") && sortedCompCombination.contains("7")) || (sortedCompCombination.contains("5") && sortedCompCombination.contains("9")))) || (sortedCompCombination.contains("5") && ((sortedCompCombination.contains("4") && sortedCompCombination.contains("6")) || (sortedCompCombination.contains("2") && sortedCompCombination.contains("8")) || (sortedCompCombination.contains("3") && sortedCompCombination.contains("7")))) || (sortedCompCombination.contains("9") && ((sortedCompCombination.contains("7") && sortedCompCombination.contains("8")) || (sortedCompCombination.contains("3") && sortedCompCombination.contains("6"))))) {
                checkComp = true;
            }
        }
        if (checkUser) {
            request.setAttribute("victory", "THE USER IS WINNER!!!   GAME OVER!!! PRESS BUTTON To Restart Game");
            userStep = null;
            computerStep = null;
        } else {
            if (checkComp) {
                request.setAttribute("victory", "THE SHAITAN_MACHINE IS WINNER!!!   GAME OVER!!! PRESS BUTTON To Restart Game");
                computerStep = null;
                userStep = null;
            }
        }
        return "/WEB-INF/pages/table.jsp";
    }


}

