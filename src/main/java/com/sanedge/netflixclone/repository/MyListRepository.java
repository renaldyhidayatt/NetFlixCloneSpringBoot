package com.sanedge.netflixclone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sanedge.netflixclone.models.MyList;

@Repository
public interface MyListRepository extends JpaRepository<MyList, Long> {
    List<MyList> findByType(String type);

    List<MyList> findByTypeAndGenre(String type, String genre);
}
