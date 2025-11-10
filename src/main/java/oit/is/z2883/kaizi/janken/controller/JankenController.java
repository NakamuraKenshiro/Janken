package oit.is.z2883.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z2883.kaizi.janken.model.Entry;
import oit.is.z2883.kaizi.janken.model.Match;
import oit.is.z2883.kaizi.janken.model.MatchMapper;
import oit.is.z2883.kaizi.janken.model.User;
import oit.is.z2883.kaizi.janken.model.UserMapper;

@Controller
public class JankenController {

  @Autowired
  Entry entry;

  @Autowired
  MatchMapper matchMapper;

  @Autowired
  UserMapper userMapper;

  @GetMapping("/")
  public String index() {
    return "redirect:/index.html";
  }

  @GetMapping("/janken")
  public String janken(Model model, Principal prin) {
    String loginUser = prin.getName();

    entry.addUser("CPU");
    entry.addUser(loginUser);

    ArrayList<User> users = userMapper.selectAllUsers();
    ArrayList<Match> matches = matchMapper.selectAllMatches();

    model.addAttribute("loginUser", loginUser);
    model.addAttribute("entry", entry);
    model.addAttribute("users", users);
    model.addAttribute("matches", matches);

    return "janken.html";
  }

  @GetMapping("/match")
  public String match(@RequestParam Integer id, Model model, Principal prin) {
    String loginUser = prin.getName();
    User user1 = userMapper.selectByName(loginUser);
    User user2 = userMapper.selectById(id);

    model.addAttribute("loginUser", user1.getName());
    model.addAttribute("opponent", user2.getName());
    model.addAttribute("opponentId", id);

    return "match.html";
  }

  @GetMapping("/fight")
  public String fight(@RequestParam Integer id, @RequestParam String hand, Model model, Principal prin) {
    String loginUser = prin.getName();
    User user1 = userMapper.selectByName(loginUser);
    User user2 = userMapper.selectById(id);

    String cpuHand = "Gu";
    String result = judge(hand, cpuHand);

    Match match = new Match();
    match.setUser1(user1.getId());
    match.setUser2(user2.getId());
    match.setUser1Hand(hand);
    match.setUser2Hand(cpuHand);
    matchMapper.insertMatch(match);

    model.addAttribute("loginUser", user1.getName());
    model.addAttribute("opponent", user2.getName());
    model.addAttribute("opponentId", id);
    model.addAttribute("myHand", hand);
    model.addAttribute("cpuHand", cpuHand);
    model.addAttribute("result", result);

    return "match.html";
  }

  private String judge(String myHand, String cpuHand) {
    if (myHand.equals(cpuHand))
      return "Draw";
    if ((myHand.equals("Gu") && cpuHand.equals("Choki")) ||
        (myHand.equals("Choki") && cpuHand.equals("Pa")) ||
        (myHand.equals("Pa") && cpuHand.equals("Gu"))) {
      return "Win";
    }
    return "Lose";
  }
}
