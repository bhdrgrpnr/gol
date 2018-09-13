package com.gol.wallet;

import org.apache.commons.lang3.RandomStringUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

@Stateless
public class WalletService {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    WalletService walletService;

    public void insertData(String username, long userId, Long balance){
        entityManager.createNamedQuery(WalletModel.INSERT)
                .setParameter("username", username)
                .setParameter("userId", userId)
                .setParameter("balance", balance)
                .executeUpdate();
    }

    public String getTransactionIdOfUser(long userId) {
        List<String> transactionIds = entityManager.createNamedQuery(WalletModel.TRANSACTIONID)
                .setParameter("userId", userId)
                .getResultList();
        if (transactionIds != null && !transactionIds.isEmpty()) {
            return transactionIds.get(0);
        } else {
            return null;
        }
    }


    private Integer updateTransactionID(long userId, String transactionId) {
        return entityManager.createNamedQuery(WalletModel.UPDATE_TRANSACTION_ID)
                .setParameter("userId", userId)
                .setParameter("transactionId", transactionId)
                .executeUpdate();
    }


    public String generateOrGetTransactionId(Long userId){
        String transactionId = getTransactionIdOfUser(userId);
        if(transactionId == null || transactionId.equals("")){
            transactionId = RandomStringUtils.randomAlphanumeric(24);
            int result = updateTransactionID(userId, transactionId);
            if(result!=1){
                throw new IllegalArgumentException(" error generating transaction id");
            }
        }

        return transactionId;
    }


    public Long fetchCurrentBalanceOfUser(Long userId, String transactionId){

        List<Object[]> usersTransaction = entityManager.createNamedQuery(WalletModel.FETCH_USER_WITH_TRANSACTION)
                .setParameter("userId", userId)
                .setParameter("transactionId", transactionId)
                .getResultList();

        if(usersTransaction == null || usersTransaction.isEmpty()){
            throw new IllegalArgumentException(" possible wrong transaction or user id, fetch a new transaction id, check your user id and retry");
        }
        else {
            return ((BigInteger)usersTransaction.get(0)[1]).longValue();
        }
    }

    public  List<Object[]> fetchUser(Long userId){
        List<Object[]> users = entityManager.createNamedQuery(WalletModel.FETCH_USER)
                .setParameter("userId", userId)
                .getResultList();

        if(users == null || users.isEmpty()){
            throw new IllegalArgumentException("wrong user Id");
        }
        else {
            return users;
        }
    }


    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public boolean manipulateUserBalance(Long userId, String transactionId, long amount) {
        int effectedRowCount =  entityManager.createNamedQuery(WalletModel.UPDATE_USER_BALANCE)
                .setParameter("userId", userId)
                .setParameter("transactionId", transactionId)
                .setParameter("amount", amount)
                .executeUpdate();

        if(effectedRowCount != 1){
            throw new IllegalArgumentException("balance of the user may not be sufficient or  possible wrong transaction/user id, ");
        }

        updateTransactionID(userId,  RandomStringUtils.randomAlphanumeric(24));

        return Boolean.TRUE;
    }






}
