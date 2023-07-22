package com.example.demo3.controller.impl;

import com.example.demo3.controller.TestController;
import com.example.demo3.dto.TestDto;
import com.example.demo3.service.TestService;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/tests")
@Model
public class TestControllerImpl implements TestController {

    @Inject
    private TestService testService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public TestDto find(@PathParam("id") String id) {
        return testService.find(Long.parseLong(id));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<TestDto> findAll() {
        return testService.findAll();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TestDto create(@RequestBody TestDto testDto) {
        return testService.create(testDto);
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public TestDto update(TestDto testDto) {
        return testService.update(testDto);
    }

    @DELETE
    @Path("/{id}")
    @Override
    public void delete(@PathParam("id") String id) {
        testService.delete(Long.parseLong(id));
    }
}
