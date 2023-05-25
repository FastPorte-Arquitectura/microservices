package com.fastporte.ServiceService.service;

import com.fastporte.ServiceService.entities.Comment;

import java.util.List;

public interface ICommentService extends CrudService<Comment> {

    List<Comment> findByDriverId(Long driverId) throws Exception;
    List<Comment> findByClientId(Long clientId) throws Exception;

}
