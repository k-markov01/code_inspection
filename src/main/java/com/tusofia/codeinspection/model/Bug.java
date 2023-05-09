package com.tusofia.codeinspection.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Bug {
    @Id

    private String id;

    private String Type;

    private int priority;

    private String message;

    private String className;

    private int startLine;

    private int endLine;
    @ManyToOne
    @JoinColumn(name="bug_id")
    private BugReport bugReport;

    public Bug(String id, String type, int priority, String message, String className, int startLine, int endLine) {
        this.id = id;
        Type = type;
        this.priority = priority;
        this.message = message;
        this.className = className;
        this.startLine = startLine;
        this.endLine = endLine;
    }
}
