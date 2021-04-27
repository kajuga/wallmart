package com.fedorov.wallmart.service.serviceImpl;

import com.fedorov.wallmart.entity.Producer;
import com.fedorov.wallmart.entity.Product;
import com.fedorov.wallmart.model.ProducerModel;
import com.fedorov.wallmart.model.ProductModel;
import com.fedorov.wallmart.repository.ProducerRepository;
import com.fedorov.wallmart.repository.ProducerRepository;
import com.fedorov.wallmart.repository.ProductRepository;
import com.fedorov.wallmart.service.ProducerService;
import com.fedorov.wallmart.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProducerServiceImpl implements ProducerService {
    @Autowired
    private ProducerRepository producerRepository;

    @Override
    public void create(ProducerModel producerModel) {
        log.info("In ProducerServiceImpl create(ProducerModel producerModel)", producerModel);
        Producer producerToSave = new Producer();
        producerToSave.setName(producerModel.getName());
        producerRepository.save(producerToSave);
    }

    @Override
    public ProducerModel getById(Long id) {
        log.info("In ProducerServiceImpl getById(Long id)", id);
        //TODO проверки
        return producerToModelTransformer(producerRepository.getOne(id));
    }

    @Override
    public List<ProducerModel> readAll() {
        log.info("In ProducerServiceImpl readAll()");
        List<ProducerModel> producerModels = new ArrayList<>();
        for (Producer producer: producerRepository.findAll()) {
            producerModels.add(producerToModelTransformer(producer));
        }
        return producerModels;
    }

    @Override
    public boolean update(ProducerModel producerModel, Long id) {
        log.info("In ProducerServiceImpl update(ProducerModel producerModel, Long id) " + id);
        Producer updatedProducer = producerRepository.getOne(id);
        //TODO
        updatedProducer.setName(producerModel.getName());
        producerRepository.save(updatedProducer);
        return true;
    }

    @Override
    public void delete(Long id) {
        log.info("In ProducerServiceImpl delete by Id{}", id);
        producerRepository.deleteById(id);
    }


    // Producer to ProducerModel converter
    private ProducerModel producerToModelTransformer (Producer producer) {
        ProducerModel producerModel = new ProducerModel();
        producerModel.setId(producer.getId());
        producerModel.setName(producer.getName());
        return producerModel;
    }
}

