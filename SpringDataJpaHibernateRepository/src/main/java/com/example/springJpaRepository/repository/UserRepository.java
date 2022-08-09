package com.example.springJpaRepository.repository;

import com.example.springJpaRepository.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    //@Transactional NO in Repository!!!
    @Modifying
    @Query(value = "ALTER SEQUENCE persondb_person_id_seq RESTART;", nativeQuery = true)
    void updateSeq();
}
