package com.example.demo3.mapper;

import com.example.demo3.dto.TestDto;
import com.example.demo3.entity.TestEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface TestEntityMapper {

    TestEntity map(TestDto testDto);

    TestDto map(TestEntity testEntity);

    List<TestDto> map(List<TestEntity> entities);
}
