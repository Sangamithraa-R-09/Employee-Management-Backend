package com.details.userdetails.controller;

import com.details.userdetails.model.Userdetail;
import com.details.userdetails.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/home")
public class Usercontroller {

    @Autowired
    Userservice service;

    @PostMapping("/add")
    public Userdetail insert(@RequestBody Userdetail detail){
        return this.service.insertData(detail);
    }

    @GetMapping("/getRecords")
    public List<Userdetail> getData(){
        return this.service.getAllRecords();
    }

    @GetMapping("all/{email}")
    public List<Userdetail> getAllByEmail(@PathVariable (value = "email") String email){
        return this.service.getByEmail(email);
    }

    @GetMapping("{email}")
    public Userdetail getUser(@PathVariable(value="email") String email){
        return this.service.getRecords(email);
    }

    @GetMapping("/getAll")
    public List<Userdetail> getAllTheRecords(){
        return this.service.getEveryRecords();
    }

    @PutMapping("{email}")
    public Userdetail updateRecords(@PathVariable (value="email") String emailaddress,
                                    @RequestBody Userdetail detail ){
        return  this.service.updateRec(emailaddress,detail);
    }

    @GetMapping("checkemail/{email}")
    public String validateEmail(@PathVariable (value = "email") String emailaddress){
        return this.service.validate(emailaddress);
    }

    @GetMapping("checkpass/{email}/{password}")
    public String validatePass(@PathVariable(value="email") String email,@PathVariable(value="password") String password){
        return this.service.confirmPass(email,password);
    }

    @DeleteMapping("{email}")
    public String deleteRecords(@PathVariable (value="email") String emailaddress){
        return this.service.deleteRec(emailaddress);
    }
}
