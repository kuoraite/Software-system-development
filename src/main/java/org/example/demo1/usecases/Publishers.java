package org.example.demo1.usecases;

import lombok.Getter;
import lombok.Setter;
import org.example.demo1.entities.Book;
import org.example.demo1.mybatis.model.Publisher;
import org.example.demo1.mybatis.dao.PublisherMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Publishers {

    @Inject
    private PublisherMapper publisherMapper;

    @Getter @Setter
    private Publisher publisherToCreate = new Publisher();

    @Getter
    private List<Publisher> allPublishers;

    @PostConstruct
    public void init(){
        loadAllPublishers();
    }

    @Transactional
    public String createPublisher(){
        publisherMapper.insert(publisherToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllPublishers(){
        allPublishers = publisherMapper.selectAll();
    }
}
