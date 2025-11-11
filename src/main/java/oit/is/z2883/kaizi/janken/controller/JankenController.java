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
import oit.is.z2883.kaizi.janken.model.MatchInfo;
import oit.is.z2883.kaizi.janken.model.MatchInfoMapper;

@Controller
public class JankenController {

  @Autowired
  Entry entry;

  @Autowired
  MatchMapper matchMapper;

  @Autowired
  UserMapper userMapper;

  @Autowired
  MatchInfoMapper matchInfoMapper;

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

    User me = userMapper.selectByName(loginUser);
    ArrayList<MatchInfo> activeMatchInfos = matchInfoMapper.selectActiveByUser2(me.getId());

    model.addAttribute("loginUser", loginUser);
    model.addAttribute("entry", entry);
    model.addAttribute("users", users);
    model.addAttribute("matches", matches);
    model.addAttribute("activeMatchInfos", activeMatchInfos);

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

    MatchInfo mi = new MatchInfo();
    mi.setUser1(user1.getId());
    mi.setUser2(user2.getId());
    mi.setUser1Hand(hand);
    mi.setIsActive(true);
    matchInfoMapper.insertMatchInfo(mi);

    model.addAttribute("loginUser", user1.getName());
    return "wait.html";
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
