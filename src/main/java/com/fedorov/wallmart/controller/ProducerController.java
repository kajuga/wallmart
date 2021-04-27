package com.fedorov.wallmart.controller;


import com.fedorov.wallmart.model.ProducerModel;
import com.fedorov.wallmart.service.ProducerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/producers")
@Api(value="onlinestore", description="Operations pertaining to producers in Wallmart online store")
public class ProducerController {
    @Autowired
    private ProducerService producerService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search a producer with an ID", response = ProducerModel.class)
    @ApiResponses(value = { @ApiResponse(code = 100, message = "100 is the message"),
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 666, message = "666 custom status"),
            @ApiResponse(code = 400, message = "Huyssful")})
    public ResponseEntity<ProducerModel> getProducer(@PathVariable("id") Long producerModelId) {
        if (producerModelId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ProducerModel producerModel = producerService.getById(producerModelId);
        if (producerModel == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(producerService.getById(producerModelId), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add a NEW producer")
    //TODO проверка на наличие producer  с идентичным названием
    public ResponseEntity<ProducerModel> saveProducer(@RequestBody ProducerModel producerModel) {
        HttpHeaders headers = new HttpHeaders();
        if (producerModel == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        producerService.create(producerModel);
        return new ResponseEntity<>(producerModel, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update a producer")
    public ResponseEntity<ProducerModel> updateProducer(@RequestBody @Validated ProducerModel producerModel, UriComponentsBuilder builder) {
        if (producerModel == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Long id = producerModel.getId();
        producerService.update(producerModel, id);
        return new ResponseEntity<>(producerModel, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete a producer")
    public ResponseEntity<ProducerModel> deleteProducer(@PathVariable("id") Long producerModelId) {
        if (producerService.getById(producerModelId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        producerService.delete(producerModelId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View a list of available producers",response = Iterable.class)
    public ResponseEntity<List<ProducerModel>> getAllProducers() {
        List<ProducerModel> producerModelList = producerService.readAll();
        if (producerModelList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(producerModelList, HttpStatus.OK);
    }
}