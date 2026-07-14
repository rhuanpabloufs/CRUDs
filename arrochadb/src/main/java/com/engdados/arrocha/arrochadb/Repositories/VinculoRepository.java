package com.engdados.arrocha.arrochadb.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.engdados.arrocha.arrochadb.Entities.Vinculo;

import java.time.LocalDate;

@Repository
public interface VinculoRepository extends JpaRepository<Vinculo, Integer>{

    @Modifying
    @Query(value = "INSERT INTO universidade.vinculo (mat_estudante, curso, data_entrada, status, data_saida) VALUES (:mat, :curso, :dataEntrada, CAST(:status AS universidade.status_estudante), :dataSaida)", nativeQuery = true)
    void inserirVinculo(@Param("mat") String mat, @Param("curso") Integer curso, @Param("dataEntrada") LocalDate dataEntrada, @Param("status") String status, @Param("dataSaida") LocalDate dataSaida);

    @Modifying
    @Query(value = "UPDATE universidade.vinculo SET mat_estudante = :mat, curso = :curso, data_entrada = :dataEntrada, status = CAST(:status AS universidade.status_estudante), data_saida = :dataSaida WHERE idvinculo = :id", nativeQuery = true)
    void atualizarVinculo(@Param("id") Integer id, @Param("mat") String mat, @Param("curso") Integer curso, @Param("dataEntrada") LocalDate dataEntrada, @Param("status") String status, @Param("dataSaida") LocalDate dataSaida);

    @Query(value = "SELECT idvinculo FROM universidade.vinculo ORDER BY idvinculo DESC LIMIT 1", nativeQuery = true)
    Integer ultimoId();

    @Query(value = "SELECT setval(pg_get_serial_sequence('universidade.vinculo', 'idvinculo'),(SELECT MAX(idvinculo) FROM universidade.vinculo), true)", nativeQuery = true)
    void sincronizaSerialVinculo();
}
