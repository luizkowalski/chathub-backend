package com.inkdrop.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import com.inkdrop.domain.models.PrivateMessage;

public interface PrivateMessageRepostiry extends CrudRepository<PrivateMessage, Long> {

}
