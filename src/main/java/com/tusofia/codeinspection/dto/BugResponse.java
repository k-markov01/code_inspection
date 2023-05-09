package com.tusofia.codeinspection.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BugResponse {

  private String Type;

  private int priority;

  private String message;

  private String className;

  private int startLine;

  private int endLine;
}
