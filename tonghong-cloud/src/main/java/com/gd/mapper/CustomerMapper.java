package com.gd.mapper;

import com.gd.model.Customer;
import com.gd.model.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * description: CustomerMapper <br>
 * date: 2022-10-14 11:35 <br>
 * author: DaTao <br>
 * version: 1.0 <br>
 */
@Mapper
public interface CustomerMapper {

    @Select({"select * from customer LIMIT #{recordStart},#{pageSize} "})
    List<Customer> queryCourse(@Param("recordStart") Integer recordStart, @Param("pageSize") Integer pageSize);

    @Select({"SELECT count(*) FROM customer"})
    int getRecordCount();

    @Insert("insert into customer(openid,session_key,nickname,avatar_url,name) value(#{customer.openid},#{customer.sessionKey},#{customer.nickname},#{customer.avatarUrl},'')")
    void add(@Param("customer") Customer customer);

    @Delete("delete from customer where id=#{id}")
    void delete(@Param("id") Integer id);

    @Update("update customer set password=#{customer.password} where id=#{customer.id}")
    void update(@Param("customer") Customer customer);


    @Select("select * from customer")
    List<Customer> list();

    @Select("select count(*)=1 from customer where nickname = #{customer.nickname}")
    int check(@Param("customer") Customer customer);

    @Select("select * from customer where nickname = #{customer.nickname} and name = #{customer.name} and password = #{customer.password}")
    Customer login(@Param("customer") Customer customer);

    @Select("select count(*)=1 from customer where nickname = #{customer.nickname} and `name`=''")
    int checkuser(@Param("customer") Customer customer);


    //UPDATE runoob_tbl SET runoob_title='学习 C++' WHERE runoob_id=3;
    @Insert("update customer set `name`=#{customer.name} ,password = #{customer.password} where nickname=#{customer.nickname}")
    void fill(@Param("customer") Customer customer);


//    @Select("select * from customer where nickname = #{customer.nickname} and name=#{customer.name} and password=#{#{customer.password}}   ")
//    Customer getuser(@Param("customer") Customer customer);


}
