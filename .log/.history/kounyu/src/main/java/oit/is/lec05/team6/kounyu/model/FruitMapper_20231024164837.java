package oit.is.lec05.team6.kounyu.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface FruitMapper {
  @Select("SELECT ID, NAME,PRICE FROM FRUIT")
  ArrayList<Fruit> selectAllFruit();

  @Select("select ID, NAME,PRICE from FRUIT WHERE ID = #{id}")
  Fruit selectById(int id);

  @Delete("delete from Fruit where id = #{id}")
  boolean deleteById(int id);

  @Update("update fruit set name=#{name},price=#{price} where id = #{id}")
  void updateById(FRUIT fruit);
}
