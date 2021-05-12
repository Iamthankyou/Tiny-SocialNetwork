package com.devteam.social_network.repos.impl;

import com.devteam.social_network.common.DataUtil;
import com.devteam.social_network.common.DateUtil;
import com.devteam.social_network.repos.PostRepoService;
import com.devteam.social_network.sdo.PostCustomSdo;
import com.devteam.social_network.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepoServiceImpl implements PostRepoService {
    @Autowired
    EntityManager em;
    @Override
    public Page<PostCustomSdo> listPost(Pageable pageable) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT POST.PostId,POST.Content,PostTime,PostDate,POST.UserEmail,ReactionType ");
        sql.append(" FROM POST INNER JOIN POST_REACTION ON POST_REACTION.PostID = POST.PostID ");
        Query query = em.createNativeQuery(sql.toString());
        List<PostCustomSdo> result = new ArrayList<>();

        List<Object[]> queryResult = query.getResultList();
        queryResult.forEach(q ->{
            System.out.println(q[3].getClass());
        });
        PostCustomSdo postCustomSdo;
        if (!DataUtil.isNullOrEmpty(queryResult)){
            for (Object[] objects:queryResult){
                postCustomSdo = new PostCustomSdo();
                postCustomSdo = DataUtil.convertObjectsToClass(objects,postCustomSdo);
                result.add(postCustomSdo);
            }
        }
        Long countAll = countAllItem();
        result.forEach(ele ->{
            System.out.println("Ele-->"+ele);
        });
        return new PageImpl<>(result,pageable,countAll);
    }

    private Long countAllItem(){
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT Count(Post.PostId) as numberOfPost ");
        sql.append(" FROM POST INNER JOIN POST_REACTION ON POST_REACTION.PostID = POST.PostID ");
        Query query = em.createNativeQuery(sql.toString());
        List<PostCustomSdo> result = new ArrayList<>();

        List<Object[]> queryResult = query.getResultList();
        return DataUtil.safeToLong(queryResult);
    }

}
