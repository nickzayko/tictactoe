package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;


@Controller
@RequestMapping("/in")
public class StepController extends HttpServlet {

    // победные комбинации
//        private static String VICTORYCOMBINATION0 = "123";
//        private static String VICTORYCOMBINATION1 = "456";
//        private static String VICTORYCOMBINATION2 = "789";
//        private static String VICTORYCOMBINATION3 = "147";
//        private static String VICTORYCOMBINATION4 = "258";
//        private static String VICTORYCOMBINATION5 = "369";
//        private static String VICTORYCOMBINATION6 = "159";
//        private static String VICTORYCOMBINATION7 = "357";




    public String allNumberCombination = "123456789";
    public String userStep = "";
    public String computerStep = "";
    Random random = new Random();




    //обработчик шагов
    @RequestMapping("/step")
    private String step(HttpServletRequest request, HttpSession session) {
        session.getAttribute("stepParam");
        String value = request.getParameter("stepParam");
        if (allNumberCombination.contains(value)) {
            userStep = saveSteps(userStep, value);
            allNumberCombination = updateAllNumberCombination(userStep, allNumberCombination);
            if (allNumberCombination != "") {
                computerStep = saveCompSteps(computerStep, allNumberCombination);
                allNumberCombination = updateAllNumberCombination(computerStep, allNumberCombination);
            } else {
                request.setAttribute("inform", "Партия сыграна, ходы закончились! Начните игру заново!");
            }
            checkVictoryOfPlayers(userStep, computerStep, request);
        } else {
            request.setAttribute("inform", "Эй, чувак, ты сюда не ходи, ты в свободную ячейку ходи!!! ");
        }
        printSteps(request, userStep, "X");
        printSteps(request, computerStep, "O");
        return "/WEB-INF/pages/table.jsp";
    }


    //метод запоминает комбинацию ходов
    private String saveSteps(String step, String value) {
        return (step + value);
    }


    //учу комп ставить нолики
    private String saveCompSteps (String computerStep, String allNumberCombination) {
        String temporaryValue = String.valueOf(allNumberCombination.charAt(random.nextInt(allNumberCombination.length())));
        return saveSteps(computerStep, temporaryValue);
    }


    //из общей комбинации удаляются заполненные ячейки
    private String updateAllNumberCombination(String combinationOfSteps, String allNumberCombination) {
        String temporaryString = "";
        for (int i = 0; i < allNumberCombination.length(); i++){
            if (!combinationOfSteps.contains(String.valueOf(allNumberCombination.charAt(i)))){
                temporaryString = temporaryString + String.valueOf(allNumberCombination.charAt(i));
            }
        }
        return temporaryString;
    }


    //Проверка и выявление победителя
    private void checkVictoryOfPlayers(String userStep, String computerStep, HttpServletRequest request) {
        if (checkCombinationsIfContainVictoryCombination (userStep)) {
            request.setAttribute("victory", "THE USER IS WINNER!!!   GAME OVER!!! PRESS BUTTON To Restart Game");
        } else {
            if (checkCombinationsIfContainVictoryCombination (computerStep)){
                request.setAttribute("victory", "THE SHAITAN_MACHINE IS WINNER!!!   GAME OVER!!! PRESS BUTTON To Restart Game");
            }
        }
    }
    //проверяется, содержат ли ходы игроков победные комбинации
    private boolean checkCombinationsIfContainVictoryCombination(String playersCombinations) {
        String [] victoryCombination = new String[8];
        victoryCombination[0] = "123";
        victoryCombination[1] = "456";
        victoryCombination[2] = "789";
        victoryCombination[3] = "147";
        victoryCombination[4] = "258";
        victoryCombination[5] = "369";
        victoryCombination[6] = "159";
        victoryCombination[7] = "357";
        boolean flag = false;
        int j = 0;
        for (int i = 0; i < 8; i++) {
            if (playersCombinations.contains(String.valueOf(victoryCombination[i].charAt(j))) &&
                    playersCombinations.contains(String.valueOf(victoryCombination[i].charAt(j + 1))) &&
                    playersCombinations.contains(String.valueOf(victoryCombination[i].charAt(j + 2)))) {
                flag = true;
            }

        }
        // было так....
        //        if ((playersCombinations.contains("1") && ((playersCombinations.contains("2") && playersCombinations.contains("3")) || (playersCombinations.contains("4")
//                && playersCombinations.contains("7")) || (playersCombinations.contains("5") && playersCombinations.contains("9"))))
//                || (playersCombinations.contains("5") && ((playersCombinations.contains("4") && playersCombinations.contains("6"))
//                || (playersCombinations.contains("2") && playersCombinations.contains("8")) || (playersCombinations.contains("3") && playersCombinations.contains("7"))))
//                || (playersCombinations.contains("9") && ((playersCombinations.contains("7") && playersCombinations.contains("8")) || (playersCombinations.contains("3")
//                && playersCombinations.contains("6"))))) {
//            flag = true;
//        }

        return flag;
    }


    // отрисовка в таблице ходов
    private void printSteps(HttpServletRequest request, String step, String xOrO) {
        for (int i = 0; i < step.length(); i++) {
            request.setAttribute("v" + step.charAt(i), xOrO);
        }
    }


    //сброс игры
    @RequestMapping("/restartGame")
    public String restartGame(HttpServletRequest request, HttpSession session) {
        userStep = "";
        computerStep = "";
        allNumberCombination = "123456789";
        session.invalidate();
        return "/index.jsp";
    }

}