package com.fedorov.wallmart.service;

import com.fedorov.wallmart.model.ProducerModel;
import java.util.List;

public interface ProducerService {
    void create(ProducerModel producerModel);
    List<ProducerModel> readAll();
    ProducerModel getById (Long id);
    boolean update(ProducerModel producerModel, Long id);
    void delete(Long id);
}