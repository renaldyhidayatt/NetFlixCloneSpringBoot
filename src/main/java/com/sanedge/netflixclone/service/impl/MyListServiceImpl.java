package com.sanedge.netflixclone.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanedge.netflixclone.dto.request.MyListRequest;
import com.sanedge.netflixclone.dto.response.MessageResponse;
import com.sanedge.netflixclone.models.MyList;
import com.sanedge.netflixclone.models.User;
import com.sanedge.netflixclone.repository.MyListRepository;
import com.sanedge.netflixclone.service.MyListService;

@Service
public class MyListServiceImpl implements MyListService {
    private MyListRepository myListRepository;
    private UserServiceImpl userServiceImpl;

    @Autowired
    public MyListServiceImpl(MyListRepository myListRepository, UserServiceImpl userServiceImpl) {
        this.myListRepository = myListRepository;
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public MessageResponse createMyList(MyListRequest myListRequest) {
        User user = this.userServiceImpl.getCurrentUser();
        if (user.getIsAdmin()) {
            try {
                MyList myList = new MyList();
                myList.setTitle(myListRequest.getTitle());
                myList.setType(myListRequest.getType());
                myList.setGenre(myListRequest.getGenre());
                myList.setContent(myListRequest.getContent());
                myList.setCreatedAt(LocalDateTime.now());
                this.myListRepository.save(myList);

                return MessageResponse.builder().message("Berhasil membuat data").data(myList).statusCode(200).build();
            } catch (Exception e) {
                return MessageResponse.builder().message(e.toString()).statusCode(400).build();
            }
        }

        return MessageResponse.builder().message("Bukan admin").statusCode(400).build();
    }

    @Override
    public MessageResponse deleteMyList(Long id) {
        User user = this.userServiceImpl.getCurrentUser();

        if (user.getIsAdmin()) {
            this.myListRepository.deleteById(id);

            return MessageResponse.builder().message("Berhasil menghapus data").statusCode(200).build();
        }
        return MessageResponse.builder().message("bukan admin").build();
    }

    @Override
    public MessageResponse getList(String type, String genre) {

        User user = this.userServiceImpl.getCurrentUser();

        if (user.getIsAdmin()) {
            List<MyList> list;
            if (type != null) {
                if (genre != null) {
                    list = myListRepository.findByTypeAndGenre(type, genre);
                } else {
                    list = myListRepository.findByType(type);
                }
            } else {
                list = myListRepository.findAll();
            }

            return MessageResponse.builder().message("Berhasil mendapatkan data").data(list).statusCode(200).build();
        }
        return MessageResponse.builder().message("bukan admin").build();
    }
}
