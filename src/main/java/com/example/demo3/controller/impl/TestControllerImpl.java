package com.example.demo3.controller.impl;

import com.example.demo3.controller.TestController;
import com.example.demo3.service.TestService;
import com.example.demo3.service.dto.TestDto;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
    public TestDto find(@PathParam("id") Long id) {
        return testService.find(id);
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
    public void delete(@PathParam("id") Long id) {
        testService.delete(id);
    }

}
