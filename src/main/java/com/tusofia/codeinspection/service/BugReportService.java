package com.tusofia.codeinspection.service;

import com.tusofia.codeinspection.model.BugReport;
import com.tusofia.codeinspection.model.EvaluationFile;
import java.io.IOException;

public interface BugReportService {
  BugReport analyzeJar(EvaluationFile evaluationFile) throws IOException, InterruptedException;
  BugReport getBugReport(String id);
}
