package oit.is.z2883.kaizi.janken.model;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MatchInfoMapper {

  @Insert("""
      INSERT INTO MATCHINFO (USER1, USER2, USER1HAND, ISACTIVE)
      VALUES (#{user1}, #{user2}, #{user1Hand}, #{isActive})
      """)
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void insertMatchInfo(MatchInfo m);

  @Select("SELECT ID, USER1, USER2, USER1HAND, ISACTIVE AS isActive FROM MATCHINFO WHERE ISACTIVE = TRUE AND USER2 = #{user2} ORDER BY ID DESC")
  ArrayList<MatchInfo> selectActiveByUser2(long user2);
}
