package controllers;

import org.springframework.stereotype.Service;

@Service
public class AddSymbolService {
  public String userCombination = null;
    public String addSymbol (String st){
        userCombination = userCombination + st;
        return userCombination;
    }
}

