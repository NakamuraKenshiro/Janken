package oit.is.z2883.kaizi.janken.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import oit.is.z2883.kaizi.janken.model.Entry;
import oit.is.z2883.kaizi.janken.model.Janken;

@Controller
public class JankenController {

  @Autowired
  Entry entry;

  @GetMapping("/janken")
  public String janken(Model model, Principal prin) {
    String loginUser = prin.getName();
    entry.addUser(loginUser);
    model.addAttribute("loginUser", loginUser);
    model.addAttribute("entry", entry);
    return "janken.html";
  }

  @GetMapping("/janken/game")
  public String play(@RequestParam String hand, Model model, Principal prin) {
    String loginUser = prin.getName();
    entry.addUser(loginUser);

    Janken game = new Janken(hand);

    model.addAttribute("loginUser", loginUser);
    model.addAttribute("entry", entry);
    model.addAttribute("myHand", game.getMyHand());
    model.addAttribute("cpuHand", game.getCpuHand());
    model.addAttribute("result", game.getResult());
    return "janken.html";
  }
}
