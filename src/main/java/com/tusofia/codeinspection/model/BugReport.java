package com.tusofia.codeinspection.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BugReport {
    @Id
    private String id;
    @OneToOne
    private EvaluationFile evaluationFile;

    @OneToMany
    @JoinColumn(name = "bug_id")
    private List<Bug> bugs;
}
