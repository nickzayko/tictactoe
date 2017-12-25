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


    // на данный момент не задействован
    @Autowired
    public AddSymbolService service;
    //....




    @RequestMapping("/step1")
    public String step1(HttpServletRequest request) {
        userStep = SaveUserStep(userStep, "1");
        saveUserSteps(request);
        return "/WEB-INF/pages/table.jsp";
    }

    @RequestMapping("/step2")
    public String step2(HttpServletRequest request) {
        userStep = SaveUserStep(userStep, "2");
        saveUserSteps(request);
        return "/WEB-INF/pages/table.jsp";
    }

    @RequestMapping("/step3")
    public String step3(HttpServletRequest request) {
        userStep = SaveUserStep(userStep, "3");
        saveUserSteps(request);
        return "/WEB-INF/pages/table.jsp";
    }

    @RequestMapping("/step4")
    public String step4(HttpServletRequest request) {
        userStep = SaveUserStep(userStep, "4");
        saveUserSteps(request);
        return "/WEB-INF/pages/table.jsp";
    }

    @RequestMapping("/step5")
    public String step5(HttpServletRequest request) {
        userStep = SaveUserStep(userStep, "5");
        saveUserSteps(request);
        return "/WEB-INF/pages/table.jsp";
    }

    @RequestMapping("/step6")
    public String step6(HttpServletRequest request) {
        userStep = SaveUserStep(userStep, "6");
        saveUserSteps(request);
        return "/WEB-INF/pages/table.jsp";
    }

    @RequestMapping("/step7")
    public String step7(HttpServletRequest request) {
        userStep = SaveUserStep(userStep, "7");
        saveUserSteps(request);
        return "/WEB-INF/pages/table.jsp";
    }

    @RequestMapping("/step8")
    public String step8(HttpServletRequest request) {
        userStep = SaveUserStep(userStep, "8");
        saveUserSteps(request);
        return "/WEB-INF/pages/table.jsp";
    }

    @RequestMapping("/step9")
    public String step9(HttpServletRequest request) {
        userStep = SaveUserStep(userStep, "9");
        saveUserSteps(request);

        return "/WEB-INF/pages/table.jsp";
    }

    //сброс игры
    @RequestMapping("/restartGame")
    public String restartGame(HttpServletRequest request) {
        userStep = null;
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
        if (checkVictory(userStep)) {
            request.setAttribute("victory", "THE USER IS WINNER!!!   GAME OVER!!! PRESS BUTTON To Restart Game");
            userStep = null;
        }
        getComputerSteps(request);
        return "/WEB-INF/pages/table.jsp";
    }

    //метод проверки ходов пользователя на выигрыш
    public boolean checkVictory(String userStep) {
        Boolean check = false;
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
            if ((sortedUserCombination.contains("1") && ((sortedUserCombination.contains("2") && sortedUserCombination.contains("3"))
                    || (sortedUserCombination.contains("4") && sortedUserCombination.contains("7")) ||
                    (sortedUserCombination.contains("5") && sortedUserCombination.contains("9")))) || (sortedUserCombination.contains("5") && ((sortedUserCombination.contains("4") && sortedUserCombination.contains("6"))
                    || (sortedUserCombination.contains("2") && sortedUserCombination.contains("8")) ||
                    (sortedUserCombination.contains("3") && sortedUserCombination.contains("7")))) || (sortedUserCombination.contains("9") && ((sortedUserCombination.contains("7") && sortedUserCombination.contains("8"))
                    || (sortedUserCombination.contains("3") && sortedUserCombination.contains("6"))))) {
                check = true;
            }
        }
        return check;
    }

    //учу комп ставить нолики

    private String getComputerSteps(HttpServletRequest request) {



        return "/WEB-INF/pages/table.jsp";
    }
//        String computerUsedNumbers = null;
//        int countOfCharArray = 9;
//        char [] newCompSteps = new char[countOfCharArray - 1];
//
//        //проверяем и удаляем из комбинации для компа ту цифру, которой уже пользователь походил
//        for (int i = 0; i < compStepCharArray.length; i++) {
//            if (!userStep.contains(String.valueOf(compStepCharArray[i]))) {
//                newCompSteps [i] = compStepCharArray[i];
//            }
//        }
//        //*******
//
//        int indexForSearchCompStep = random.nextInt((newCompSteps.length-1));
//        if (computerUsedNumbers == null){
//            computerUsedNumbers = String.valueOf(newCompSteps[indexForSearchCompStep]);
//        } else {
//            computerUsedNumbers = computerUsedNumbers + String.valueOf(newCompSteps[indexForSearchCompStep]);
//        }
//        for (int i = 0; i < computerUsedNumbers.length(); i++){
//            int tmp = Integer.parseInt(String.valueOf(computerUsedNumbers.charAt(i)));
//            request.setAttribute("v" + tmp, "O");
//        }

//        request.setAttribute("v" + (Integer.parseInt(String.valueOf(newCompSteps[indexForSearchCompStep]))), "O");
//        char [] compStep = new char[newCompSteps.length];
//        for (int i = 0; i < compStep.length; i++){
//            compStep [i] = newCompSteps [i];
//        }
//        return "/WEB-INF/pages/table.jsp";


//
//        if (computerStep == null){
//
//        } else {
//
//        }
//
//
//        return "/WEB-INF/pages/table.jsp";
//    }


//    private String computerSteps(HttpServletRequest request) {
//        Random random = new Random();
//        char [] newCompSteps = new char[(compStep.length - 1)];
//        //проверяем и удаляем из комбинации для компа ту цифру, которой уже пользователь походил
//        for (int i = 0; i < compStep.length; i++) {
//            if (!userStep.contains(String.valueOf(compStep[i]))) {
//                newCompSteps [i] = compStep[i];
//            }
//        }
//        //*******
//        int indexForSearchCompStep = random.nextInt((newCompSteps.length-1));
//        for (int i = 0; i < )
//        request.setAttribute("v" + (Integer.parseInt(String.valueOf(newCompSteps[indexForSearchCompStep]))), "O");
//        char [] compStep = new char[newCompSteps.length];
//        for (int i = 0; i < compStep.length; i++){
//            compStep [i] = newCompSteps [i];
//        }
//        return "/WEB-INF/pages/table.jsp";
//    }



}
