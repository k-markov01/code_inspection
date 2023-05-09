package com.tusofia.codeinspection.controller;

import com.tusofia.codeinspection.dto.UploadFileResponse;
import com.tusofia.codeinspection.model.EvaluationFile;
import com.tusofia.codeinspection.service.EvaluationFileService;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/evaluation-file")
@AllArgsConstructor
public class EvaluationFileController {

  private EvaluationFileService evaluationFileService;

  @PostMapping("/upload")
  public ResponseEntity<UploadFileResponse> uploadFile(
      @RequestParam(value = "jarFile") MultipartFile jarFile)
      throws IOException {
    EvaluationFile file = evaluationFileService.storeFile(jarFile);
    return ResponseEntity.ok(new UploadFileResponse(file.getId(), "File stored."));
  }

}
