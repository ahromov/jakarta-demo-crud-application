package com.example.demo3.service.impl;

import com.example.demo3.entity.TestEntity;
import com.example.demo3.repository.TestRepository;
import com.example.demo3.service.dto.TestDto;
import com.example.demo3.service.mapper.TestEntityMapper;
import com.oneandone.iocunit.IocUnitRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.enterprise.inject.Produces;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(IocUnitRunner.class)
public class TestServiceImplTest {

    @Produces
    @Mock
    private TestRepository testRepository;

    @Mock
    private TestEntityMapper testEntityMapper;

    @InjectMocks
    private TestServiceImpl testService;

    @Before
    public void setUp() {
        TestEntity entity = TestEntity.builder().id(1L).text("test").build();
        when(testEntityMapper.map(any(TestDto.class))).thenReturn(entity);
        TestDto expected = TestDto.builder().id(1L).text("test").build();
        when(testEntityMapper.map(any(TestEntity.class))).thenReturn(expected);
        when(testEntityMapper.map(any(List.class))).thenReturn(List.of(expected));
        when(testRepository.findAll()).thenReturn(List.of(entity));
    }

    @Test
    public void find() {
        TestEntity entity = TestEntity.builder().id(1L).text("test").build();
        when(testRepository.find(anyLong())).thenReturn(entity);
        TestDto testDto = testService.find(1L);
        TestDto expected = TestDto.builder().id(1L).text("test").build();
        assertEquals(expected, testDto);
    }

    @Test
    public void findAll() {
        List<TestDto> testDto = testService.findAll();
        TestDto expected = TestDto.builder().id(1L).text("test").build();
        assertEquals(List.of(expected), testDto);
    }

    @Test
    public void create() {
        TestEntity entity = TestEntity.builder().id(1L).text("test").build();
        when(testRepository.create(any(TestEntity.class))).thenReturn(entity);
        TestDto testDto = testService.create(TestDto.builder().build());
        TestDto expected = TestDto.builder().id(1L).text("test").build();
        assertEquals(expected, testDto);
    }

    @Test
    public void update() {
        TestEntity entity = TestEntity.builder().id(1L).text("test1").build();
        when(testRepository.create(any(TestEntity.class))).thenReturn(entity);
        TestDto expected = TestDto.builder().id(1L).text("test1").build();
        when(testEntityMapper.map(any(TestEntity.class))).thenReturn(expected);
        TestDto test = TestDto.builder().id(1L).text("test").build();
        TestDto testDto = testService.create(test);
        assertEquals(expected, testDto);
    }

    @Test
    public void delete() {
        doNothing().when(testRepository).delete(anyLong());

        testService.delete(1L);

        verify(testRepository, times(1)).delete(anyLong());
    }

}