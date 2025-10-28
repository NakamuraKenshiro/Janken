package oit.is.z2883.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import oit.is.z2883.kaizi.janken.model.User;
import oit.is.z2883.kaizi.janken.model.UserMapper;
import oit.is.z2883.kaizi.janken.model.Match;
import oit.is.z2883.kaizi.janken.model.MatchMapper;

@Controller
public class JankenController {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private MatchMapper matchMapper;

  @GetMapping("/janken")
  @Transactional
  public String showJanken(ModelMap model, Principal prin) {
    String loginUser = prin.getName();
    ArrayList<User> users = userMapper.selectAllUsers();
    ArrayList<Match> matches = matchMapper.selectAllMatches();
    model.addAttribute("loginUser", loginUser);
    model.addAttribute("users", users);
    model.addAttribute("matches", matches);
    return "janken.html";
  }

  @GetMapping("/match")
  @Transactional
  public String showMatch(@RequestParam Integer id, ModelMap model, Principal prin) {
    String loginUser = prin.getName();
    User opponent = userMapper.selectById(id);
    model.addAttribute("loginUser", loginUser);
    model.addAttribute("opponent", opponent);
    return "match.html";
  }

}
