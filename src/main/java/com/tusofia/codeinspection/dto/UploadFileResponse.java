package com.tusofia.codeinspection.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UploadFileResponse {
 private String fileId;
 private String eventType;
}
