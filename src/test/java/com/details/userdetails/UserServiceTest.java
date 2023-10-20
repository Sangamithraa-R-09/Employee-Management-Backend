package com.details.userdetails;

import com.details.userdetails.Exception.ResourceNotFoundException;
import com.details.userdetails.model.Userdetail;
import com.details.userdetails.repository.Userrepo;
import com.details.userdetails.service.Userservice;
import org.h2.engine.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    Userrepo repo;

    @InjectMocks
    Userservice userservice;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsertData() {
        Userdetail expectedResult = new Userdetail();
        Timestamp tmp = Timestamp.valueOf("2023-09-06 11:36:52.004000");
        expectedResult.setLast(new Timestamp(System.currentTimeMillis()));

        when(repo.save(expectedResult)).thenReturn(expectedResult);

        Userdetail actualResult = userservice.insertData(expectedResult);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetAllRecords(){
        List<Userdetail> expectedResult=new ArrayList<>();
        when(repo.findTop10ByOrderByLastDesc()).thenReturn(expectedResult);
        List<Userdetail> actualResult=userservice.getAllRecords();
        Assertions.assertEquals(expectedResult,actualResult);
    }

    @Test
    void testGetRecords(){
        Userdetail expectedResult=new Userdetail();
        String email="abc@gmail.com";
        when(repo.findByEmail(email)).thenReturn(expectedResult);
        Userdetail actualResult=userservice.getRecords(email);
        Assertions.assertEquals(expectedResult,actualResult);
    }

    @Test
    void testDeleteRecEmailNotFound(){
        String expectedResult="Given emaiId doesn't exist";
        String email="abc@gmail.com";
        Userdetail record=new Userdetail();
        when(repo.findByEmail(email)).thenReturn(null);
        String actualResult=userservice.deleteRec(email);
        Assertions.assertEquals(expectedResult,actualResult);
    }

    @Test
    void testDeleteRecEmailFound(){
        String expectedResult="Successfully deleted";
        String email="abc@gmail.com";
        Userdetail record=new Userdetail();
        when(repo.findByEmail(email)).thenReturn(record);
        String actualResult=userservice.deleteRec(email);
        Assertions.assertEquals(expectedResult,actualResult);
    }

    @Test
    void testUpdateRecord(){
        Userdetail expectedResult=new Userdetail();
        String email="abc@gmail.com";
        Userdetail record=new Userdetail();
        when(repo.findByEmail(email)).thenReturn(expectedResult);
        when(repo.save(expectedResult)).thenReturn(expectedResult);
        Userdetail actualRecord=userservice.updateRec(email,expectedResult);
        Assertions.assertEquals(expectedResult,actualRecord);

    }

    @Test
    void testUpdateRecordNotFound(){
        Userdetail expectedResult=new Userdetail();
        Userdetail record=new Userdetail();
        String email="abc@gmail.com";
        when(repo.findByEmail(email)).thenReturn(null);
        Assertions.assertThrows(ResourceNotFoundException.class,()->{
            userservice.updateRec(email,expectedResult);
        });
    }

    @Test
    void testValidate(){
        String expectedResult="true";
        String email="abc@gmail.com";
        Userdetail record=new Userdetail();
        when(repo.findByEmail(email)).thenReturn(null);
        String actualResult=userservice.validate(email);
        Assertions.assertEquals(expectedResult,actualResult);
    }

    @Test
    void testValidateFalse(){
        String expectedResult="false";
        String email="abc@gmail.com";
        Userdetail record=new Userdetail();
        when(repo.findByEmail(email)).thenReturn(record);
        String actualResult=userservice.validate(email);
        Assertions.assertEquals(expectedResult,actualResult);
    }



}




