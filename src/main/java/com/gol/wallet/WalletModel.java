package com.gol.wallet;



import javax.persistence.*;


@Entity
@Table(name = "WALLET")
@SqlResultSetMappings(
        {
                @SqlResultSetMapping(name = WalletModel.TRANSACTION_ID_MAPPING,
                        classes = @ConstructorResult(
                                targetClass = WalletModel.class,
                                columns = {
                                        @ColumnResult(name = "TRANSACTION_ID", type = String.class)
                                }
                        )
                )
        }

)
@NamedNativeQueries({
        @NamedNativeQuery(name = WalletModel.INSERT, query = "INSERT INTO WALLET (id, USER_NAME, USER_ID, BALANCE) VALUES (nextval('WALLET_ID_SEQ'), :username, :userId, :balance)"),
        @NamedNativeQuery(name = WalletModel.TRANSACTIONID, query = "SELECT w.TRANSACTION_ID  from WALLET w where w.USER_ID=:userId"),
        @NamedNativeQuery(name = WalletModel.UPDATE_TRANSACTION_ID, query = "UPDATE WALLET  SET TRANSACTION_ID=:transactionId WHERE user_id=:userId"),
        @NamedNativeQuery(name = WalletModel.FETCH_USER_WITH_TRANSACTION, query = "SELECT w.*  from WALLET w where w.USER_ID=:userId and w.TRANSACTION_ID=:transactionId"),
        @NamedNativeQuery(name = WalletModel.FETCH_USER, query = "SELECT w.*  from WALLET w where w.USER_ID=:userId"),
        @NamedNativeQuery(name = WalletModel.UPDATE_USER_BALANCE, query = "UPDATE WALLET w SET balance = balance + :amount " +
                                                                             " WHERE w.user_id=:userId and w.TRANSACTION_ID=:transactionId          " +
                                                                             " and  (:amount+   (SELECT w2.balance from WALLET w2 where w.USER_ID = w2.USER_ID))  >=0  ")
})

public class WalletModel {

    public static final String INSERT = "WalletModel.insert";
    public static final String TRANSACTIONID = "WalletModel.transactionId";
    public static final String UPDATE_TRANSACTION_ID = "WalletModel.updateTransaction";
    public static final String TRANSACTION_ID_MAPPING = "WalletModel.transactionIdMapping";
    public static final String FETCH_USER_WITH_TRANSACTION = "WalletModel.fetchUserWithTransaction";
    public static final String FETCH_USER = "WalletModel.fetchUser";
    public static final String UPDATE_USER_BALANCE = "WalletModel.updateUserBalance";



    @Id
    @GeneratedValue(generator = "WALLET_ID_SEQ")
    @SequenceGenerator(name = "WALLET_ID_SEQ", sequenceName = "WALLET_ID_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_ID", unique = true, nullable = false)
    private Long userId;

    @Column(name = "BALANCE")
    private Long balance =0l;

    @Column(name = "TRANSACTION_ID", unique = true)
    private String transactionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}

