package com.details.userdetails.repository.RepoService.RepoServiceImpl;

import com.details.userdetails.model.Userdetail;
import com.details.userdetails.repository.RepoService.UserRepoService;
import com.details.userdetails.repository.Userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRepoServiceImpl implements UserRepoService {
    @Autowired
    Userrepo userrepo;
    @Override
    public Userdetail findByEmail(String email) {

        return userrepo.findByEmail(email);
    }

    @Override
    public String deleteByEmail(String email) {
        userrepo.deleteByEmail(email);
        return "Successfully deleted";
    }

    @Override
    public List<Userdetail> findAllByEmail(String email){
        return userrepo.findAllByEmail(email);
    }
}
