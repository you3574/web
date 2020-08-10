package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.skhu.dto.publisher;

@Mapper
public interface publisherMapper {
    List<publisher> findAll();
    //List<publisher>	findAll2();
}

