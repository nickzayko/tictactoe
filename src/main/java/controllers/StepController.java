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

//    private static String ALLNUMBERCOMBINATION = "123456789";
//    public char[] compStepCharArray = ALLNUMBERCOMBINATION.toCharArray();
    public String allNumberCombination = "123456789";
    public String userStep = null;
    public String computerStep = null;
    Random random = new Random();

    //обработчик шагов
    @RequestMapping("/step")
    private String step(HttpServletRequest request) {
        String value = request.getParameter("stepParam");
        userStep = saveUserStep(userStep, value);
        printUserSteps(request);
        updateAllNumberCombinationAfterUserStep (userStep, allNumberCombination);
        computerStep = saveCompStep(userStep);
        printComputerSteps(request, computerStep);
        updateCompStepCharArray(compStepCharArray, computerStep);
        checkVictoryOfPlayers(userStep, computerStep, request);
        return "/WEB-INF/pages/table.jsp";
    }

    private String updateAllNumberCombinationAfterUserStep(String userStep, String allNumberCombination) {
        String temporaryString = null;
        int temporaryCount = 0;
        for (int i = 0; i < allNumberCombination.length(); i++){
            if (!userStep.contains(String.valueOf(allNumberCombination.charAt(i)))){
                if (temporaryString == null){
                    temporaryString = String.valueOf(allNumberCombination.charAt(i));
                } else {
                    temporaryString = temporaryString + String.valueOf(allNumberCombination.charAt(i));
                }
            }
        }
        return allNumberCombination;
    }

    //сброс игры
    @RequestMapping("/restartGame")
    public String restartGame(HttpServletRequest request) {
        userStep = null;
        computerStep = null;
        String ALLNUMBERCOMBINATION = "123456789";
        char[] compStepCharArray = ALLNUMBERCOMBINATION.toCharArray();
        return "/index.jsp";
    }

    //метод запоминает комбинацию ходов пользователя
    private String saveUserStep(String userStep, String stepValue) {
        if (userStep == null) {
            userStep = stepValue;
        } else {
            userStep = userStep + stepValue;
        }
        return userStep;
    }

    // метод запоминает куда пользователь походит и делает отрисовку "Х" в таблице
    private String printUserSteps(HttpServletRequest request) {
        for (int i = 0; i < userStep.length(); i++) {
            int tmp = Integer.parseInt(String.valueOf(userStep.charAt(i)));
            request.setAttribute("v" + (tmp), "X");
        }
        return "/WEB-INF/pages/table.jsp";
    }


    //учу комп ставить нолики
    // комп выбирает рандомно одну из оставшихся пустых ячеек, комбинация ходов компа запоминается и возвращается
    private String saveCompStep(String userStep) {
        int countForArray = 0;
        String tmp[] = new String[9];
        String symbolSelectedByComp = null;
        String freedomNumbers = null;


        //создаем строку из оставшихся пустых ячеек, чтобы комб выбрал куда ходить
        for (int i = 0; i < compStepCharArray.length; i++) {
            if (!userStep.contains(String.valueOf(compStepCharArray[i]))) {
                tmp[countForArray] = String.valueOf(compStepCharArray[i]);
                countForArray++;
            }
        }
        int i = 0;
        do {
            if (freedomNumbers == null) {
                freedomNumbers = String.valueOf(tmp[i]);
            } else {
                freedomNumbers = freedomNumbers + String.valueOf(tmp[i]);
            }
            i++;
        } while (tmp[i] == null);
        //_____

        symbolSelectedByComp = String.valueOf(freedomNumbers.charAt(random.nextInt(freedomNumbers.length())));

        if (computerStep == null) {
            computerStep = symbolSelectedByComp;
        } else {
            computerStep = computerStep + symbolSelectedByComp;
        }
        return computerStep;
    }

    private char[] updateCompStepCharArray(char[] compStepCharArray, String computerStep) {
        int count = 0;
        for (int i = 0; i < compStepCharArray.length; i++) {
            if (!computerStep.contains(String.valueOf(compStepCharArray[i]))) {
                compStepCharArray[count] = compStepCharArray[i];
                count++;
            }
        }
        return compStepCharArray;
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
        //создаем массив из комбинации ходов пользователя, сортируем его по возрастанию и сравниваем с победной комбинацией
        String tmp[] = new String[userStep.length()];
        if ((userStep.contains("1") && ((userStep.contains("2") && userStep.contains("3")) || (userStep.contains("4") && userStep.contains("7")) || (userStep.contains("5") && userStep.contains("9")))) || (userStep.contains("5") && ((userStep.contains("4") && userStep.contains("6")) || (userStep.contains("2") && userStep.contains("8")) || (userStep.contains("3") && userStep.contains("7")))) || (userStep.contains("9") && ((userStep.contains("7") && userStep.contains("8")) || (userStep.contains("3") && userStep.contains("6"))))) {
            checkUser = true;
            request.setAttribute("victory", "THE USER IS WINNER!!!   GAME OVER!!! PRESS BUTTON To Restart Game");
            userStep = null;
            computerStep = null;
            return "/WEB-INF/pages/table.jsp";
        }
        Boolean checkComp = false;
        if ((computerStep.contains("1") && ((computerStep.contains("2") && computerStep.contains("3")) || (computerStep.contains("4") && computerStep.contains("7")) || (computerStep.contains("5") && computerStep.contains("9")))) || (computerStep.contains("5") && ((computerStep.contains("4") && computerStep.contains("6")) || (computerStep.contains("2") && computerStep.contains("8")) || (computerStep.contains("3") && computerStep.contains("7")))) || (computerStep.contains("9") && ((computerStep.contains("7") && computerStep.contains("8")) || (computerStep.contains("3") && computerStep.contains("6"))))) {
            checkComp = true;
            request.setAttribute("victory", "THE SHAITAN_MACHINE IS WINNER!!!   GAME OVER!!! PRESS BUTTON To Restart Game");
            computerStep = null;
            userStep = null;
            return "/WEB-INF/pages/table.jsp";
        }
        return "/WEB-INF/pages/table.jsp";


    }
}

