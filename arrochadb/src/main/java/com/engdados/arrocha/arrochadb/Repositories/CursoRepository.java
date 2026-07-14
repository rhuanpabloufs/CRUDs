package com.engdados.arrocha.arrochadb.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.engdados.arrocha.arrochadb.Entities.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {

    @Query(value = "SELECT EXISTS(SELECT 1 FROM universidade.curso WHERE nome = :nome AND turno = CAST(:turno AS universidade.tipo_turno) AND campus = :campus AND nivel = CAST(:nivel AS universidade.tipo_nivel))", nativeQuery = true)
    boolean existeCombinacao(@Param("nome") String nome, @Param("turno") String turno, @Param("campus") String campus, @Param("nivel") String nivel);

    @Query(value = "SELECT idcurso FROM universidade.curso WHERE nome = :nome AND turno = CAST(:turno AS universidade.tipo_turno) AND campus = :campus AND nivel = CAST(:nivel AS universidade.tipo_nivel) LIMIT 1", nativeQuery = true)
    Integer idDaCombinacao(@Param("nome") String nome, @Param("turno") String turno, @Param("campus") String campus, @Param("nivel") String nivel);

    @Query(value = "SELECT idcurso FROM universidade.curso ORDER BY idcurso DESC LIMIT 1", nativeQuery = true)
    Integer ultimoId();

    @Modifying
    @Query(value = "INSERT INTO universidade.curso (nome, grau, turno, campus, nivel) VALUES (:nome, CAST(:grau AS universidade.tipo_grau), CAST(:turno AS universidade.tipo_turno), :campus, CAST(:nivel AS universidade.tipo_nivel))", nativeQuery = true)
    void inserirCurso(@Param("nome") String nome, @Param("grau") String grau, @Param("turno") String turno, @Param("campus") String campus, @Param("nivel") String nivel);

    @Modifying
    @Query(value = "UPDATE universidade.curso SET nome = :nome, grau = CAST(:grau AS universidade.tipo_grau), turno = CAST(:turno AS universidade.tipo_turno), campus = :campus, nivel = CAST(:nivel AS universidade.tipo_nivel) WHERE idcurso = :id", nativeQuery = true)
    void atualizarCurso(@Param("id") Integer id, @Param("nome") String nome, @Param("grau") String grau, @Param("turno") String turno, @Param("campus") String campus, @Param("nivel") String nivel);

    @Query(value = "SELECT setval(pg_get_serial_sequence('universidade.curso', 'idcurso'),(SELECT MAX(idcurso) FROM universidade.curso), true)", nativeQuery = true)
    void sincronizaSerialCurso();
}
