package com.example.demo3.service.impl;

import com.example.demo3.dto.TestDto;
import com.example.demo3.entity.TestEntity;
import com.example.demo3.mapper.TestEntityMapper;
import com.example.demo3.repository.TestRepository;
import com.example.demo3.service.TestService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@ApplicationScoped
@Transactional
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final TestEntityMapper testEntityMapper;

    @Inject
    public TestServiceImpl(TestRepository testRepository,
                           TestEntityMapper testEntityMapper) {
        this.testRepository = testRepository;
        this.testEntityMapper = testEntityMapper;
    }

    @Override
    public TestDto find(Long id) {
        TestEntity entity = testRepository.find(id);
        return testEntityMapper.map(entity);
    }

    @Override
    public List<TestDto> findAll() {
        List<TestEntity> entities = testRepository.findAll();
        return testEntityMapper.map(entities);
    }

    @Override
    public TestDto create(TestDto testDto) {
        TestEntity map = testEntityMapper.map(testDto);
        TestEntity entity = testRepository.create(map);
        return testEntityMapper.map(entity);
    }

    @Override
    public TestDto update(TestDto testDto) {
        Optional<TestEntity> testEntity = Optional.ofNullable(testRepository.find(testDto.getId()));
        testEntity.ifPresentOrElse(entity -> {
            TestEntity map = testEntityMapper.map(testDto);
            testRepository.update(map);
        }, testEntity::orElseThrow);
        return testDto;
    }

    @Override
    public void delete(Long id) {
        testRepository.delete(id);
    }
}
