package com.tusofia.codeinspection.service.impl;

import com.tusofia.codeinspection.exception.BugReportNotFound;
import com.tusofia.codeinspection.model.Bug;
import com.tusofia.codeinspection.model.BugReport;
import com.tusofia.codeinspection.model.EvaluationFile;
import com.tusofia.codeinspection.repository.BugReportRepository;
import com.tusofia.codeinspection.repository.EvaluationFileRepository;
import com.tusofia.codeinspection.service.BugReportService;
import edu.umd.cs.findbugs.BugCollection;
import edu.umd.cs.findbugs.BugCollectionBugReporter;
import edu.umd.cs.findbugs.BugInstance;
import edu.umd.cs.findbugs.BugReporter;
import edu.umd.cs.findbugs.DetectorFactoryCollection;
import edu.umd.cs.findbugs.FindBugs2;
import edu.umd.cs.findbugs.Project;
import edu.umd.cs.findbugs.SourceLineAnnotation;
import edu.umd.cs.findbugs.config.UserPreferences;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BugReportServiceImpl implements BugReportService {

    private final String BUG_REPORT_NOT_FOUND = "There is not such a bug report!";
    BugReportRepository bugReportRepository;

    EvaluationFileRepository evaluationFileRepository;

    @Override
    public BugReport analyzeJar(EvaluationFile evaluationFile)
            throws IOException, InterruptedException {
        // Initialize SpotBugs engine
        FindBugs2 spotbugs = new FindBugs2();
        List<Bug> bugs = new ArrayList<>();

        // Set user preferences (optional)
        UserPreferences preferences = UserPreferences.createDefaultUserPreferences();
        spotbugs.setUserPreferences(preferences);
        //Initialize DetectorFactoryCollection
        DetectorFactoryCollection detectorFactoryCollection = DetectorFactoryCollection.instance();

        // Load the JAR file
        File jarFile = new File(evaluationFile.getFilePath());
        Project project = new Project();
        project.addFile(jarFile.getAbsolutePath());

        // Set up the bug reporter
        BugCollectionBugReporter bugReporter = new BugCollectionBugReporter(project);
        bugReporter.setPriorityThreshold(BugReporter.NORMAL);
        spotbugs.setBugReporter(bugReporter);
        //Set the project
        spotbugs.setProject(project);
        //Set the detectorFactory
        spotbugs.setDetectorFactoryCollection(detectorFactoryCollection);

        // Analyze the project
        spotbugs.execute();

        // Output the report
        BugCollection bugCollection = bugReporter.getBugCollection();

        // Return the path to the report file

        for (BugInstance bug : bugCollection) {
            SourceLineAnnotation sourceLine = bug.getPrimarySourceLineAnnotation();
            bugs.add(
                    new Bug(UUID.randomUUID().toString(), bug.getType(), bug.getPriority(), bug.getMessage(),
                            sourceLine.getClassName(), sourceLine.getStartLine(), sourceLine.getEndLine()));
        }
        return bugReportRepository.save(
                new BugReport(UUID.randomUUID().toString(), evaluationFile, bugs));
    }

    @Override
    public BugReport getBugReport(String id) {
        return bugReportRepository.findById(id)
                .orElseThrow(() -> new BugReportNotFound(BUG_REPORT_NOT_FOUND));
    }


}
