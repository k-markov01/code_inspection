package com.tusofia.codeinspection.service.impl;

import com.tusofia.codeinspection.exception.EvaluationFileNotFound;
import com.tusofia.codeinspection.model.EvaluationFile;
import com.tusofia.codeinspection.repository.EvaluationFileRepository;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import com.tusofia.codeinspection.service.EvaluationFileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class EvaluationFileServiceImpl implements EvaluationFileService {

  private final String EVALUATION_FILE_NOT_FOUND = "There is not such evaluation file!";
  private final String JAR_FILES_FOLDER_PATH = "C:\\InteliJ Projects\\code-inspection\\jarFiles\\";
  EvaluationFileRepository evaluationFileRepository;

  @Override
  public EvaluationFile storeFile(MultipartFile jarFile) throws IOException {
    String path = saveMultipart(jarFile);
    EvaluationFile evaluationFile = new EvaluationFile();
    evaluationFile.setFilePath(path);
    evaluationFile.setId(UUID.randomUUID().toString());
    return evaluationFileRepository.save(evaluationFile);
  }

  @Override
  public EvaluationFile findEvaluationFile(String id) {
    return evaluationFileRepository.findById(id)
        .orElseThrow(() -> new EvaluationFileNotFound(EVALUATION_FILE_NOT_FOUND));
  }

  private String saveMultipart(MultipartFile multipartFile) throws IOException {
    File convFile = new File(
        JAR_FILES_FOLDER_PATH + multipartFile.getOriginalFilename());
    FileOutputStream fos = new FileOutputStream(convFile);
    fos.write(multipartFile.getBytes());
    fos.close();
    return convFile.getAbsolutePath();
  }
}
