package com.example.channuoithaiviet.service;

import java.util.List;

import com.example.channuoithaiviet.entity.Tag;
import com.example.channuoithaiviet.model.request.CreateTagRequest;

public interface TagService {
    
    List<Tag> getListTag();

    Tag createTag(CreateTagRequest request);

    Tag updateTag(long id,CreateTagRequest request);

    void enableTag(long id);

    void deleleTag(long id);

}
