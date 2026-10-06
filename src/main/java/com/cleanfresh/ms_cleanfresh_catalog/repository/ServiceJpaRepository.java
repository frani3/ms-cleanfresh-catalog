package com.cleanfresh.ms_cleanfresh_catalog.repository;

import com.cleanfresh.ms_cleanfresh_catalog.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceJpaRepository extends JpaRepository<ServiceEntity, Long> {

    List<ServiceEntity> findAllByOrderByIdAsc();

    List<ServiceEntity> findByDisponibleTrueOrderByIdAsc();
}
