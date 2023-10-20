package com.details.userdetails;

import com.details.userdetails.controller.Usercontroller;
import com.details.userdetails.model.Userdetail;
import com.details.userdetails.service.Userservice;
import org.h2.engine.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class UserControllerTest {

    @Mock
    private Userservice userservice;

    @InjectMocks
    private Usercontroller usercontroller;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert(){
        Userdetail userdetail = new Userdetail();
        userdetail.setEmail("abc@gmail.com");

        when(userservice.insertData(userdetail)).thenReturn(userdetail);

        Userdetail response = usercontroller.insert(userdetail);

        Assertions.assertEquals(userdetail,response);
    }

    @Test
    void testGetData(){
        List<Userdetail> expectedRecords=new ArrayList<>();

        when(userservice.getAllRecords()).thenReturn(expectedRecords);

        List<Userdetail> actualRecords=usercontroller.getData();

        Assertions.assertEquals(expectedRecords,actualRecords);
    }

    @Test
    void testGetParticularRecord(){
        Userdetail expectedResult=new Userdetail();
        String email="abc@gmail.com";
        when(userservice.getRecords(email)).thenReturn(expectedResult);

        Userdetail actualResult=usercontroller.getParticularData(email);

        Assertions.assertEquals(expectedResult,actualResult);
    }

    @Test
    void testUpdateRecords(){
        Userdetail expectedResult=new Userdetail();
        String email="abc@gmail.com";
        expectedResult.setEmail("xyz@gmail.com");
        when(userservice.updateRec(email,expectedResult)).thenReturn(expectedResult);

        Userdetail actualResult=usercontroller.updateRecords(email,expectedResult);

        Assertions.assertEquals(expectedResult,actualResult);
    }

    @Test
    void testGet(){
        String expectedResult="true";
        String email="abc@gmail.com";
        when(userservice.validate(email)).thenReturn("true");
        String actualResult=usercontroller.validateEmail(email);
        Assertions.assertEquals(expectedResult,actualResult);
    }

    @Test
    void testDelete(){
        String expectedResult="Successfully deleted";
        String email="abc@gmail.com";
        when(userservice.deleteRec(email)).thenReturn("Successfully deleted");

        String actualResult=usercontroller.deleteRecords(email);
        Assertions.assertEquals(expectedResult,actualResult);
    }

}
