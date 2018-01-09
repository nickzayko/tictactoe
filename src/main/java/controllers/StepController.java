package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
@RequestMapping("/in")
public class StepController {

    // победные комбинации
//        private static String VICTORYCOMBINATION0 = "123";
//        private static String VICTORYCOMBINATION1 = "456";
//        private static String VICTORYCOMBINATION2 = "789";
//        private static String VICTORYCOMBINATION3 = "147";
//        private static String VICTORYCOMBINATION4 = "258";
//        private static String VICTORYCOMBINATION5 = "369";
//        private static String VICTORYCOMBINATION6 = "159";
//        private static String VICTORYCOMBINATION7 = "357";

    public String allNumberCombination;
    public String userStep;
    public String computerStep;
    Random random = new Random();

    //обработчик шагов
    @RequestMapping("/step")
    private String step(HttpServletRequest request, HttpServletResponse response) {

        //сохранение игры с помощью куки
        boolean flag = false;
        String cookieNames [] = new String[3];
        cookieNames[0] = "userStepName";
        cookieNames[1] = "computerStepName";
        cookieNames[2] = "allNumberCombinationName";
        Cookie [] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            for (int j = 0; j < cookieNames.length; j++) {
                if (cookies[i].getName().equals(cookieNames[j])) {
                    flag = true;
                }
            }
        }
        if (flag){
            for (int i = 0; i < cookies.length; i++){
                if (cookies[i].getName().equals("userStepName")){
                    userStep = String.valueOf(cookies[i].getValue());
                }
                if (cookies[i].getName().equals("computerStepName")){
                    computerStep = String.valueOf(cookies[i].getValue());
                }
                if (cookies[i].getName().equals("allNumberCombinationName")){
                    allNumberCombination = String.valueOf(cookies[i].getValue());
                }
            }
        } else {
            userStep = "";
            computerStep = "";
            allNumberCombination = "123456789";
        }
        //---------------------------------------------------------------------

        //сохранение игры с помощью сессии
        HttpSession session = request.getSession(true);
        //проверка, данные об игре записаны в сессию, пользователь новый или нет?
        if (session.getAttribute("userStepKey") == null) {
            allNumberCombination = "123456789";
            userStep = "";
            computerStep = "";
        } else {
            userStep = String.valueOf(session.getAttribute("userStepKey"));
            computerStep = String.valueOf(session.getAttribute("computerStepKey"));
            allNumberCombination = String.valueOf(session.getAttribute("allNumberCombinationKey"));
        }
//        //----------------------------------------------------------------------

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
            //это надо, чтобы в случае чьей-либо победы и наличие свободных ячеек запретить пользователю играть дальше
            if (checkVictoryOfPlayers(userStep, computerStep, request)){
                allNumberCombination = "";
            }
            //-------------------------------------------------------------------------------------------------------
        } else {
            request.setAttribute("inform", "Эй, чувак, ты сюда не ходи, ты в свободную ячейку ходи!!! ");
        }

//        //непосредственная запись данных в куки
//        Cookie cookieUser = new Cookie("userStepName", userStep);
//        Cookie cookieComp = new Cookie("computerStepName", computerStep);
//        Cookie cookieNumbers = new Cookie("allNumberCombinationName", allNumberCombination);
//        response.addCookie(cookieUser);
//        response.addCookie(cookieComp);
//        response.addCookie(cookieNumbers);
//        //--------------------------------------------------------------------

        //запись данных об игре с помощью сессии
        session.setAttribute("userStepKey", userStep);
        session.setAttribute("computerStepKey", computerStep);
        session.setAttribute("allNumberCombinationKey", allNumberCombination);
        //----------------------------------------------------------------------------

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
    private boolean checkVictoryOfPlayers(String userStep, String computerStep, HttpServletRequest request) {
        boolean flag = false;
        if (checkCombinationsIfContainVictoryCombination (userStep)) {
            request.setAttribute("victory", "THE USER IS WINNER!!!   GAME OVER!!! PRESS BUTTON To Restart Game");
            flag = true;
        } else {
            if (checkCombinationsIfContainVictoryCombination (computerStep)){
                request.setAttribute("victory", "THE SHAITAN_MACHINE IS WINNER!!!   GAME OVER!!! PRESS BUTTON To Restart Game");
                flag = true;
            }
        }
        return flag;
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
        return flag;
    }

    // отрисовка в таблице ходов
    private void printSteps(HttpServletRequest request, String step, String symbol) {
        for (int i = 0; i < step.length(); i++) {
            request.setAttribute("v" + step.charAt(i), symbol);
        }
    }

    //сброс игры
    @RequestMapping("/restartGame")
    public String restartGame(HttpServletRequest request, HttpServletResponse response) {

//        // при использовании куки
//        userStep = "";
//        computerStep = "";
//        allNumberCombination = "123456789";
//        Cookie cookieUser = new Cookie("userStepName", userStep);
//        Cookie cookieComp = new Cookie("computerStepName", computerStep);
//        Cookie cookieNumbers = new Cookie("allNumberCombinationName", allNumberCombination);
//        response.addCookie(cookieUser);
//        response.addCookie(cookieComp);
//        response.addCookie(cookieNumbers);
//        //----------------------------------------------------------------

        //при использованиии сессии
        HttpSession session = request.getSession(true);
        session.removeAttribute("userStepKey");
        session.removeAttribute("computerStepKey");
        session.removeAttribute("allNumberCombinationKey");
        //-----------------------------------------------------------------
        return "/index.jsp";
    }

}