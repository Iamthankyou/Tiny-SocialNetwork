package com.devteam.social_network.repos;

import com.devteam.social_network.sdo.PostCustomSdo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepoService {
    Page<PostCustomSdo> listPost(Pageable pageable);
}
