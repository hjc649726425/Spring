package com.hjc.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	@Insert("insert into t_user(name, age) values('jjj', 19)")
	public void add();
}
