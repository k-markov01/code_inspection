package com.tusofia.codeinspection.repository;

import com.tusofia.codeinspection.model.BugReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BugReportRepository extends JpaRepository<BugReport, String> {
}
