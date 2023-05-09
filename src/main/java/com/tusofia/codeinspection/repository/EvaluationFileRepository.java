package com.tusofia.codeinspection.repository;

import com.tusofia.codeinspection.model.EvaluationFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationFileRepository extends JpaRepository<EvaluationFile, String> {

}
