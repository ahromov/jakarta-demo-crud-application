package com.example.demo3.controller.impl;

import com.example.demo3.service.TestService;
import com.example.demo3.service.dto.TestDto;
import com.oneandone.iocunit.IocUnitRunner;
import com.oneandone.iocunit.analyzer.annotations.SutClasses;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import javax.enterprise.inject.Produces;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(IocUnitRunner.class)
@SutClasses({ TestControllerImpl.class })
public class TestControllerImplTest {

    @Produces
    @Mock
    private TestService testService;

    @Before
    public void setUp() {
        //        RestAssured.defaultParser = io.restassured.parsing.Parser.JSON;
    }

    @Test
    public void find() {
        TestDto build = TestDto.builder().id(1L).text("Some text").build();
        when(testService.find(anyLong()))
                .thenReturn(build);

        TestDto extract = RestAssured
                .given()
                .when()
                .get("/tests/1")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(TestDto.class);

        assertEquals(extract, build);
    }

    @Test
    public void findAll() {
        when(testService.findAll()).thenReturn(List.of());

        var list = RestAssured.given()
                .when()
                .get("/tests")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().body().as(List.class);

        assertTrue(list.isEmpty());
    }

    @Test
    public void create() {
        TestDto build = TestDto.builder().id(2L).text("test").build();

        when(testService.create(any(TestDto.class)))
                .thenReturn(build);

        TestDto testDto = RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .body(TestDto.builder().build())
                .put("/tests")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().body()
                .as(TestDto.class);

        assertEquals(testDto, build);
    }

    @Test
    public void update() {
        TestDto build1 = TestDto.builder().id(2L).text("test2").build();

        when(testService.update(any(TestDto.class)))
                .thenReturn(build1);

        TestDto test = TestDto.builder().id(2L).text("test").build();
        TestDto testDto = RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .body(test)
                .patch("/tests")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().body()
                .as(TestDto.class);

        assertEquals(testDto, build1);
    }

    @Test
    public void delete() {
        doNothing().when(testService).delete(anyLong());

        RestAssured.given()
                .when()
                .delete("/tests/1")
                .then()
                .assertThat()
                .statusCode(204);
    }

}