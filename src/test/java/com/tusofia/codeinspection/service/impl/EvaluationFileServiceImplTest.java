package com.tusofia.codeinspection.service.impl;

import com.tusofia.codeinspection.model.EvaluationFile;
import com.tusofia.codeinspection.repository.EvaluationFileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.internal.util.Assert;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.modelmapper.internal.bytebuddy.matcher.ElementMatchers.any;

@ExtendWith(SpringExtension.class)
class EvaluationFileServiceImplTest {
    @Mock
    private EvaluationFileRepository evaluationFileRepository;

    @InjectMocks
    private EvaluationFileServiceImpl underTest;

    @Test
    void storeId_shouldReturnEvaluationFile_withIdAndFilePath_notNull() throws IOException {
        //Arrange
        final MockMultipartFile testFile = new MockMultipartFile("file", "testfile.txt",
                MediaType.MULTIPART_FORM_DATA_VALUE, "testfile".getBytes());
        EvaluationFile evaluationFile = new EvaluationFile();
        evaluationFile.setId(UUID.randomUUID().toString());
        evaluationFile.setFilePath("testFilePath");
        Mockito.when(evaluationFileRepository.save(Mockito.any(EvaluationFile.class))).thenReturn(evaluationFile);

        //Act
        EvaluationFile newEvaluationFile = underTest.storeFile(testFile);

        //Assert
        Assert.notNull(newEvaluationFile.getId());
        Assert.notNull(newEvaluationFile.getFilePath());
    }

    @Test
    void findEvaluationFile_shouldReturnEvaluationFile() {
        //Arrange
        String id = "TEST";
        EvaluationFile evaluationFile = new EvaluationFile();
        evaluationFile.setId(id);
        evaluationFile.setFilePath("testFilePath");
        Mockito.when(evaluationFileRepository.findById(id)).thenReturn(Optional.of(evaluationFile));

        //Act
        EvaluationFile newEvaluationFile = underTest.findEvaluationFile(id);

        //Assert
        Assertions.assertEquals("TEST", newEvaluationFile.getId());
    }
}
