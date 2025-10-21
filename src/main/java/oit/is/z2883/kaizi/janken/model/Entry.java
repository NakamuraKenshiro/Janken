package oit.is.z2883.kaizi.janken.model;

import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class Entry {
  ArrayList<String> users = new ArrayList<>();

  public void addUser(String name) {
    if (!users.contains(name)) {
      users.add(name);
    }
  }

  public ArrayList<String> getUsers() {
    return users;
  }
}
