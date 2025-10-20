package oit.is.z2883.kaizi.janken.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import oit.is.z2883.kaizi.janken.model.Janken;

@Controller
public class JankenController {

  @GetMapping("/")
  public String index() {
    return "redirect:/index.html";
  }

  @GetMapping("/janken")
  public String janken(@RequestParam(required = false) String userName, Model model) {
    model.addAttribute("userName", userName);
    return "janken.html";
  }

  @GetMapping("/janken/game")
  public String play(
      @RequestParam String userName,
      @RequestParam String hand,
      Model model) {
    Janken game = new Janken(hand);
    model.addAttribute("userName", userName);
    model.addAttribute("myHand", game.getMyHand());
    model.addAttribute("cpuHand", game.getCpuHand());
    model.addAttribute("result", game.getResult());
    return "janken.html";
  }
}
