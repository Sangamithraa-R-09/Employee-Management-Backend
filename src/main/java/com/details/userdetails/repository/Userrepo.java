package com.details.userdetails.repository;

import com.details.userdetails.model.Userdetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Userrepo extends JpaRepository<Userdetail,Integer> {
//    userdetail findByEmail(String email);
      Userdetail findByEmail(String email);
      List<Userdetail> findTop10ByOrderByLastDesc();

      List<Userdetail> findAllByEmail(String email);
      String deleteByEmail(String email);

      List<Userdetail> findAllByOrderByInsertionTimestampDesc();


}
