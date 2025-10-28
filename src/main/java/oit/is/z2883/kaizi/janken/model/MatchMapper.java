package oit.is.z2883.kaizi.janken.model;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MatchMapper {

  @Select("SELECT id, user1, user2, user1hand, user2hand FROM matches")
  ArrayList<Match> selectAllMatches();
}
