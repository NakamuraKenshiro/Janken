package oit.is.z2883.kaizi.janken.model;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

  @Select("SELECT id, username AS name FROM users")
  ArrayList<User> selectAllUsers();

  @Select("SELECT id, username AS name FROM users WHERE id = #{id}")
  User selectById(int id);
}
