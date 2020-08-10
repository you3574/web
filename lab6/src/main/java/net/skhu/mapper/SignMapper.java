package net.skhu.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import net.skhu.dto.Student;

@Mapper
public interface SignMapper {

    Student studentLogin(Map<String, Object> loginMap);

}
