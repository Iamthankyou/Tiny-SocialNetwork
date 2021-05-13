package com.devteam.social_network.repos.impl;

import com.devteam.social_network.common.DataUtil;
import com.devteam.social_network.repos.MessageThreadRepoService;
import com.devteam.social_network.sdo.MessageInfoSdo;
import com.devteam.social_network.sdo.PostCustomSdo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MessageThreadRepoServiceImpl implements MessageThreadRepoService {
    @Autowired
    EntityManager em;
    @Override
    public List<MessageInfoSdo> getListMessageOfThreadMessage(Long threadId) {
        List<MessageInfoSdo> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT MESSAGE.ThreadID,MESSAGE.MessageId,Content,CreateAt,UpdateAt,Type,FileName ");
        sql.append(" FROM MESSAGE LEFT JOIN MessageMedia ON MESSAGE.MessageId = MessageMedia.MessageMediaId ");
        sql.append(" WHERE MESSAGE.ThreadID = :threadId ");
        Query query = em.createNativeQuery(sql.toString());
        query.setParameter("threadId",threadId);

        List<Object[]> queryResult = query.getResultList();
        MessageInfoSdo messageInfoSdo;
        if (!DataUtil.isNullOrEmpty(queryResult)){
            for (Object[] objects:queryResult){
                messageInfoSdo = new MessageInfoSdo();
                messageInfoSdo = DataUtil.convertObjectsToClass(objects,messageInfoSdo);
                result.add(messageInfoSdo);
            }
        }
        return result;
    }
}
