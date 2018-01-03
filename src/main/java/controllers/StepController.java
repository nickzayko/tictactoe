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

    // победные комбинации
    //    private static String VICTORYCOMBINATION1 = "123";
    //    private static String VICTORYCOMBINATION2 = "456";
    //    private static String VICTORYCOMBINATION3 = "789";
    //    private static String VICTORYCOMBINATION4 = "147";
    //    private static String VICTORYCOMBINATION5 = "258";
    //    private static String VICTORYCOMBINATION6 = "369";
    //    private static String VICTORYCOMBINATION7 = "159";
    //    private static String VICTORYCOMBINATION8 = "357";


    public String allNumberCombination = "123456789";
    public String userStep = null;
    public String computerStep = null;
    Random random = new Random();

    //обработчик шагов
    @RequestMapping("/step")
    private String step(HttpServletRequest request) {
        String value = request.getParameter("stepParam");
        userStep = saveUserStep(userStep, value);
        printUserSteps(request, userStep);
        allNumberCombination = updateAllNumberCombinationAfterUserStep (userStep, allNumberCombination);
        if (allNumberCombination != null) {
            computerStep = saveCompStep(allNumberCombination, computerStep);

            allNumberCombination = updateAllNumberCombinationAfterCompStep(allNumberCombination, computerStep);
        } else {
            request.setAttribute("inform", "Партия сыграна, ходы закончились! Начните игру заново!");
        }
        printComputerSteps(request, computerStep);
        checkVictoryOfPlayers(userStep, computerStep, request);
        return "/WEB-INF/pages/table.jsp";
    }

    //метод запоминает комбинацию ходов пользователя
    private String saveUserStep(String userStep, String value) {
        if (userStep == null) {
            userStep = value;
        } else {
            userStep = userStep + value;
        }
        return userStep;
    }

    // метод запоминает куда пользователь походит и делает отрисовку "Х" в таблице
    private String printUserSteps(HttpServletRequest request, String userStep) {
        for (int i = 0; i < userStep.length(); i++) {
            int tmp = Integer.parseInt(String.valueOf(userStep.charAt(i)));
            request.setAttribute("v" + (tmp), "X");
        }
        return "/WEB-INF/pages/table.jsp";
    }

    private String updateAllNumberCombinationAfterUserStep(String userStep, String allNumberCombination) {
        String temporaryString = null;
        for (int i = 0; i < allNumberCombination.length(); i++){
            if (!userStep.contains(String.valueOf(allNumberCombination.charAt(i)))){
                if (temporaryString == null){
                    temporaryString = String.valueOf(allNumberCombination.charAt(i));
                } else {
                    temporaryString = temporaryString + String.valueOf(allNumberCombination.charAt(i));
                }
            }
        }
        allNumberCombination = temporaryString;
        return allNumberCombination;
    }

    //учу комп ставить нолики
    private String saveCompStep(String allNumberCombination, String computerStep) {
        String temporarySymbol = String.valueOf(allNumberCombination.charAt(random.nextInt(allNumberCombination.length())));
        if (computerStep == null){
            computerStep = String.valueOf(temporarySymbol);
        } else {
            computerStep = computerStep + String.valueOf(temporarySymbol);
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

    private String updateAllNumberCombinationAfterCompStep(String allNumberCombination, String computerStep) {
        String temporaryString = null;
        for (int i = 0; i < allNumberCombination.length(); i++){
            if (!computerStep.contains(String.valueOf(allNumberCombination.charAt(i)))){
                if (temporaryString == null){
                    temporaryString = String.valueOf(allNumberCombination.charAt(i));
                } else {
                    temporaryString = temporaryString + String.valueOf(allNumberCombination.charAt(i));
                }
            }
        }
        allNumberCombination = temporaryString;
        return allNumberCombination;
    }

    //Проверка и выявление победителя
    private String checkVictoryOfPlayers(String userStep, String computerStep, HttpServletRequest request) {
        Boolean checkUser = false;
        if ((userStep.contains("1") && ((userStep.contains("2") && userStep.contains("3")) || (userStep.contains("4") && userStep.contains("7")) || (userStep.contains("5") && userStep.contains("9")))) || (userStep.contains("5") && ((userStep.contains("4") && userStep.contains("6")) || (userStep.contains("2") && userStep.contains("8")) || (userStep.contains("3") && userStep.contains("7")))) || (userStep.contains("9") && ((userStep.contains("7") && userStep.contains("8")) || (userStep.contains("3") && userStep.contains("6"))))) {
            checkUser = true;
            request.setAttribute("victory", "THE USER IS WINNER!!!   GAME OVER!!! PRESS BUTTON To Restart Game");
            userStep = null;
            computerStep = null;
            allNumberCombination = "123456789";
            return "/WEB-INF/pages/table.jsp";
        }
        Boolean checkComp = false;
        if ((computerStep.contains("1") && ((computerStep.contains("2") && computerStep.contains("3")) || (computerStep.contains("4") && computerStep.contains("7")) || (computerStep.contains("5") && computerStep.contains("9")))) || (computerStep.contains("5") && ((computerStep.contains("4") && computerStep.contains("6")) || (computerStep.contains("2") && computerStep.contains("8")) || (computerStep.contains("3") && computerStep.contains("7")))) || (computerStep.contains("9") && ((computerStep.contains("7") && computerStep.contains("8")) || (computerStep.contains("3") && computerStep.contains("6"))))) {
            checkComp = true;
            request.setAttribute("victory", "THE SHAITAN_MACHINE IS WINNER!!!   GAME OVER!!! PRESS BUTTON To Restart Game");
            computerStep = null;
            userStep = null;
            allNumberCombination = "123456789";
            return "/WEB-INF/pages/table.jsp";
        }
        return "/WEB-INF/pages/table.jsp";
    }

    //сброс игры
    @RequestMapping("/restartGame")
    public String restartGame(HttpServletRequest request) {
        userStep = null;
        computerStep = null;
        allNumberCombination = "123456789";
        return "/index.jsp";
    }

}

