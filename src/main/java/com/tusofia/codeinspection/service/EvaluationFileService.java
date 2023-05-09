package com.tusofia.codeinspection.service;

import com.tusofia.codeinspection.model.EvaluationFile;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface EvaluationFileService {

  EvaluationFile storeFile(MultipartFile jarFile) throws IOException;
  EvaluationFile findEvaluationFile(String id);
}
