package com.fedorov.wallmart.repository;

import com.fedorov.wallmart.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<Producer, Long> {
}
