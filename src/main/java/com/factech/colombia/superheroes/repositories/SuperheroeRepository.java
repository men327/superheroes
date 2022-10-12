package com.factech.colombia.superheroes.repositories;

import com.factech.colombia.superheroes.entities.SuperheroeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuperheroeRepository extends JpaRepository<SuperheroeEntity, Integer> {

    List<SuperheroeEntity> findByNombreContainingIgnoreCase(String name);

}
