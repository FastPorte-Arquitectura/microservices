package com.fastporte.ServiceService.repository;

import com.fastporte.ServiceService.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INotificationRepository extends JpaRepository<Notification, Long> {

    //Notification findByClient(Long id);

}
