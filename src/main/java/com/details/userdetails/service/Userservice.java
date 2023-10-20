package com.details.userdetails.service;
import com.details.userdetails.Exception.ResourceNotFoundException;
import com.details.userdetails.model.Userdetail;
import com.details.userdetails.repository.Userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.List;

@Service
public class Userservice {
    @Autowired
    Userrepo repo;
    public Userdetail insertData(Userdetail detail){
            detail.setLast(new Timestamp(System.currentTimeMillis()));
            return this.repo.save(detail);
    }

    public List<Userdetail> getAllRecords(){
        return this.repo.findAll();
    }

    public List<Userdetail> getEveryRecords(){
        List<Userdetail> records=repo.findAllByOrderByInsertionTimestampDesc();
        return records;
    }

    private Userrepo repository;

    public void UserService(Userrepo repository) {
        this.repository = repo;
    }

    public Userdetail getRecords(String email){
        return this.repo.findByEmail(email);
    }

    public List<Userdetail> getByEmail(String email){
        int x=email.compareTo("admin@gmail.com");
        if(x==0){
            return getAllRecords();
        }
        return this.repo.findAllByEmail(email);
    }

    public Userdetail updateRec(String email, Userdetail detail){
        Userdetail oldRecord=this.repo.findByEmail(email);
        if (oldRecord==null){
            throw new ResourceNotFoundException("User not found exception");
        }
        oldRecord.setFirstname(detail.getFirstname());
        oldRecord.setLastname(detail.getLastname());
        oldRecord.setEmail(detail.getEmail());
        oldRecord.setMobile(detail.getMobile());
        oldRecord.setAddress(detail.getAddress());
        oldRecord.setDob(detail.getDob());
        oldRecord.setLast(new Timestamp(System.currentTimeMillis()));
        return this.repo.save(oldRecord);
    }

    public String deleteRec(String email){
        if (validate(email).equals("true")){
            return "Given emaiId doesn't exist";
        }
        Userdetail record=repo.findByEmail(email);
        repo.delete(record);
        return "Successfully deleted";
    }

    public String validate(String email){
        Userdetail record=repo.findByEmail(email);
        if(record==null){
            return "true";
        }
        else{
            return "false";
        }
    }

    public String confirmPass(String email, String password) {
        Userdetail record=repo.findByEmail(email);
        int ans=record.getPassword().compareTo(password);
        if(ans==0){
            return "true";
        }
        else{
            return "false";
        }
    }

    public Userdetail getUserData(String email) {
        return this.repo.findByEmail(email);
    }
}
