package com.devteam.social_network.repos.impl;

import com.devteam.social_network.common.DataUtil;
import com.devteam.social_network.repos.MessageThreadRepoService;
import com.devteam.social_network.sdo.ListMessageInfoSdo;
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
    public ListMessageInfoSdo getListMessageOfThreadMessage(Long threadId) {
        List<MessageInfoSdo> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT MESSAGE.ThreadID,MESSAGE.MessageId,Content,CreateAt,UpdateAt,Type,Sender,avatar");
        sql.append(" FROM MESSAGE INNER JOIN ACCOUNT ON MESSAGE.SENDER = ACCOUNT.Email");
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
        ListMessageInfoSdo listMessageInfoSdo = new ListMessageInfoSdo();
        listMessageInfoSdo.setMessageInfoSdoList(result);
        listMessageInfoSdo.setTotal(result.size());
        return listMessageInfoSdo;
    }
}
