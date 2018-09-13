I implemented optimistic locking.




example usage:

1.create user with balance "0":
http://localhost:8080/gol/api/v1/wallet/createUser?userName=bahadir&userId=302&balance=0



2. check user's balance with wrong transaction id:
http://localhost:8080/gol/api/v1/wallet/fetchBalance?userId=302&transactionId=somewrongtransactionid



3. fetch user's transaction id:
http://localhost:8080/gol/api/v1/wallet/fetchTransactionId?userId=302

this will get the user's current transaction id, or create a new one, assign new transaction id to user and return transactionid



4. check user's balance with right transaction id:
http://localhost:8080/gol/api/v1/wallet/fetchBalance?userId=302&transactionId=ivcCQ1ugZhxEAeocv701Aj21

we used the transaction id that we get in step 3. We saw the balance as "0"



5. we add funds to user's wallet with wrong transaction id:
http://localhost:8080/gol/api/v1/wallet/manipulateUserBalance?userId=302&transactionId=WP7MWoH8KQf95zUPMmAn4e6L&amount=5

because of wrong transaction id we get error



6. we add funds to user's wallet with right transaction id:
http://localhost:8080/gol/api/v1/wallet/manipulateUserBalance?userId=302&transactionId=WP7MWoH8KQf95zUPMmAn4e6L&amount=5



7. fetch user's transaction id:
http://localhost:8080/gol/api/v1/wallet/fetchTransactionId?userId=302

we needed another transaction id to fetch balance, because transaction id may have been changed after some manipulation of user's wallet.



8. fetch  user's balance with new transaction id we got in step 7:
http://localhost:8080/gol/api/v1/wallet/fetchBalance?userId=302&transactionId=ivcCQ1ugZhxEAeocv701Aj21

we saw the result as "5"


9.  we delete funds to user's wallet with right transaction id, using negative number as amount input:
http://localhost:8080/gol/api/v1/wallet/manipulateUserBalance?userId=302&transactionId=WP7MWoH8KQf95zUPMmAn4e6L&amount=-6

we see an error because we had 5 as balance, if we try to withdraw 6 we see that error.









NOTES
* To see inner details of data after each step, here are the credentions of postgre:

Server                  :	tantor.db.elephantsql.com (tantor)
User & Default database :	apotanjb Reset
Password 	            :    vvXlk8TwNRzlT-ajJND1262UGA0vAvgh
URL                     :	postgres://apotanjb:vvXlk8TwNRzlT-ajJND1262UGA0vAvgh@tantor.db.elephantsql.com:5432/apotanjb








