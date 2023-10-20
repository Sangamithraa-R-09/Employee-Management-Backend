package com.details.userdetails.repository.RepoService;

import com.details.userdetails.model.Userdetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserRepoService {
    Userdetail findByEmail(String email);

    String deleteByEmail(String email);

    List<Userdetail> findAllByEmail(String email);
}
