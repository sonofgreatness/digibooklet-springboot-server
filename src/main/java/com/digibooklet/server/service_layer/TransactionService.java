package com.digibooklet.server.service_layer;

import com.digibooklet.server.data_access_layer.models.transaction.Transaction;
import com.digibooklet.server.data_access_layer.models.transaction.TransactionRequest;
import com.digibooklet.server.data_access_layer.models.user.User;
import com.digibooklet.server.data_access_layer.repositories.TransactionRepository;
import com.digibooklet.server.data_access_layer.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.mapping.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@Service
public class TransactionService {

    public static final String TRANSACTION_ADDED_SUCCESSFULLY = "TRANSACTION ADDED SUCCESSFULLY";
    @Autowired
    private final UserRepository userRepository;
    private  final TransactionRepository transactionRepository;


    /**
     * Adds a transaction to db  , related particular user
     * @param  username  String used to find user in db
     * @param  request request used to build transaction
     * @throws Exception  if user not found or on failure to add  transaction to db
     */
    public String addTransaction(String username, TransactionRequest request) throws Exception
    {


        User user = userRepository.findById(username).orElseThrow(
                ()-> new Exception("Error User Not Found"));
        try {

            Timestamp time;
            if (request.getTimestamp() == null)
                time = new Timestamp(System.currentTimeMillis());
            else
                time = request.getTimestamp();

            Transaction transaction
                    = Transaction.builder()
                    .transactionId(request.getTransactionId())
                    .timestamp(time)
                    .time(request.getTime())
                    .customerPhone(request.getCustomerPhone())
                    .customerName(request.getCustomerName())
                    .customerId(request.getCustomerId())
                    .transactionType(request.getTransactionType())
                    .amount(request.getAmount())
                    .customerPhone(request.getCustomerPhone())
                    .signature(request
                            .convertIntegerListToByteArray
                                    (
                                            request.getSignature()
                                    )
                    )
                    .user(user)
                    .build();
            transactionRepository.save(transaction);


            return  TRANSACTION_ADDED_SUCCESSFULLY;
        }catch (Exception e ) {
             throw new Exception("FAILED TO ADD TRANSACTION"+e.getMessage());
        }
    }


    /**
     * getAllTransactions -  gets all transactions  related to  a
     * user
     *
     * @param username
     * @return List<Transaction>
     */
    public List<TransactionRequest>
    getAllTransactionsByUser(String username) throws Exception {

        try {
            List<TransactionRequest> transactions = new ArrayList<>();

            List<Transaction> allTransactions = transactionRepository.findAllValidTransactionByUser(username);

            for (Transaction transaction : allTransactions) {
                TransactionRequest temp = TransactionRequest.builder()
                        .transactionId(transaction.getTransactionId())
                        .timestamp(transaction.getTimestamp())
                        .amount(transaction.getAmount())
                        .time(transaction.getTime())
                        .transactionType(transaction.getTransactionType())
                        .customerId(transaction.getCustomerId())
                        .customerName(transaction.getCustomerName())
                        .signature(convertByteArrayToList(transaction.getSignature()))//transaction.getSignature())
                        .customerPhone(transaction.getCustomerPhone())
                        .username(transaction.getUser().getUserName())
                        .build();
                transactions.add(temp);
            }
            return

                        transactions;

        }
        catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }




    public  List<Transaction> getAllTransactions() throws Exception
    {
        try{
               return
                       transactionRepository.findAll();
        }
        catch (Exception e)
        {
             throw  new Exception(e.getMessage());
        }

    }

    private static List<Integer> convertByteArrayToList(Byte[] byteArray) {
        List<Integer> integerList = new ArrayList<>();
        for (Byte b : byteArray) {
            // Convert Byte to Integer
            integerList.add((int) b);
        }
        return integerList;
    }


}
