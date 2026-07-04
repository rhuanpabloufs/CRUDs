package com.engdados.arrocha.arrochadb.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.engdados.arrocha.arrochadb.Entities.Vinculo;

@Repository
public interface VinculoRepository extends JpaRepository<Vinculo, Long>{

}
