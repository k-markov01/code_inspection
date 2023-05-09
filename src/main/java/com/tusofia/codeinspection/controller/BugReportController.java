package com.tusofia.codeinspection.controller;

import com.tusofia.codeinspection.dto.BugReportResponse;
import com.tusofia.codeinspection.dto.BugResponse;
import com.tusofia.codeinspection.model.BugReport;
import com.tusofia.codeinspection.model.EvaluationFile;
import com.tusofia.codeinspection.service.BugReportService;
import com.tusofia.codeinspection.service.EvaluationFileService;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bug-report")
@AllArgsConstructor
public class BugReportController {

  private BugReportService bugReportService;
  private EvaluationFileService evaluationFileService;

  @PostMapping("/analyze/{fileId}")
  public ResponseEntity<BugReportResponse> analyzeBugs(
      @PathVariable(value = "fileId") String fileId)
      throws IOException, InterruptedException {
    EvaluationFile evaluationFile = evaluationFileService.findEvaluationFile(fileId);
    BugReport bugReport = bugReportService.analyzeJar(evaluationFile);

    return ResponseEntity.ok(new BugReportResponse(bugReport.getId(), "Project analyzed."));
  }

  @GetMapping("/get-bug-report/{reportId}")
  public ResponseEntity<List<BugResponse>> getBugReport(
      @PathVariable(value = "reportId") String reportId) {
    ModelMapper modelMapper = new ModelMapper();
    BugReport bugReport = bugReportService.getBugReport(reportId);
    List<BugResponse> bugResponse = bugReport.getBugs()
        .stream().map(bug -> modelMapper.map(bug, BugResponse.class)).collect(Collectors.toList());

    return ResponseEntity.ok(bugResponse);
  }
}
