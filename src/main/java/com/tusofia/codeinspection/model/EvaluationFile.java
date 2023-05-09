package com.tusofia.codeinspection.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class EvaluationFile {

  @Id
  private String id;

  private String filePath;
}
