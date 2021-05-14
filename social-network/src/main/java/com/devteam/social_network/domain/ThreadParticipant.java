package com.devteam.social_network.domain;

import com.devteam.social_network.composite.ThreadParticipantCompositeKey;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "THREAD_PARTICIPANT")
@Data
@IdClass(ThreadParticipantCompositeKey.class)
public class ThreadParticipant {

    @Id
    @Column(name = "THREADID")
    private Long threadId;
    @Id
    @Column(name = "USEREMAIL")
    private String userEmail;
}
