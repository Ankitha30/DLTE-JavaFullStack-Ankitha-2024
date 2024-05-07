package debits.cards.dao.services;

import debits.cards.dao.entities.DebitCard;
import debits.cards.dao.exceptions.DebitCardException;
import debits.cards.dao.remotes.DebitCardRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;

@Service
public class DebitCardService implements DebitCardRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    CardSecurityService service;


    Logger logger = LoggerFactory.getLogger(DebitCardService.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    @Override
    public List<DebitCard> listAllCards(int customerId)  {
        List<DebitCard> debitCardList = null;

            debitCardList = jdbcTemplate.query("SELECT * FROM mybank_app_debitcard d JOIN  mybank_app_account a on a.account_number=d.account_number WHERE a.account_status='active' AND d.customer_id=?", new Object[]{customerId}, new DebitCardMapper());
            logger.info(resourceBundle.getString("list.total.cards") + debitCardList.size());

        if (debitCardList.size() == 0)
            throw new DebitCardException(resourceBundle.getString("list.no.data"));

        return debitCardList;

    }


    @Override
    public String updateDebitCardStatus(DebitCard debitCard) {
     logger.info(debitCard.getDebitCardNumber()+" "+debitCard.getDebitCardCvv()+" "+debitCard.getAccountNumber()+" "+debitCard.getDebitCardStatus()+" "+debitCard.getDebitCardExpiry()+" "+debitCard.getDomesticLimit()+" "+debitCard.getInternationalLimit());
        try {
            CallableStatementCreator creator = con -> {
                CallableStatement statement = con.prepareCall("{call UPDATE_DEBITCARD_STATUS(?, ?, ?, ?)}");
                statement.setLong(1, debitCard.getAccountNumber());
                statement.setString(2, debitCard.getDebitCardStatus());
                statement.setInt(3, debitCard.getDebitCardPin());
                statement.registerOutParameter(4, Types.VARCHAR);
                return statement;
            };

            Map<String, Object> returnedExecution = jdbcTemplate.call(creator, Arrays.asList(
                    new SqlParameter[]{
                            new SqlParameter(Types.NUMERIC),
                            new SqlParameter(Types.VARCHAR),
                            new SqlParameter(Types.INTEGER),
                            new SqlOutParameter("p_status", Types.VARCHAR)
                    }
            ));
            String resultMessage = returnedExecution.get("p_status").toString();
            if (resultMessage.equals("SQLCODE-000")) {
                logger.info(resourceBundle.getString("status.update.success"));
            } else {

                if (resultMessage.equals("SQLCODE-003")) {
                    logger.error(resourceBundle.getString("status.update.failed"));
                    throw new DebitCardException(resourceBundle.getString("status.update.failed"));
                } else if (resultMessage.equals("SQLCODE-005")) {
                    logger.error(resourceBundle.getString("internal.error"));
                    throw new DebitCardException(resourceBundle.getString("internal.error"));
                }

            }
            return resourceBundle.getString("status.update.success");
        } catch (DataAccessException e) {
            // Handle JDBC data access exception
            logger.error("DataAccessException occurred: " + e.getMessage());
            throw new DebitCardException("DataAccessException occurred: " + e.getMessage());
        }
    }


    // mapping objects
    public class DebitCardMapper implements RowMapper<DebitCard> {

        @Override
        public DebitCard mapRow(ResultSet rs, int rowNum) throws SQLException {
            DebitCard debitCard = new DebitCard();
            debitCard.setDebitCardNumber(rs.getLong(1));
            debitCard.setAccountNumber(rs.getLong(2));
            debitCard.setCustomerId(rs.getInt(3));
            debitCard.setDebitCardCvv(rs.getInt(4));
            debitCard.setDebitCardPin(rs.getInt(5));
            debitCard.setDebitCardExpiry(rs.getDate(6));
            debitCard.setDebitCardStatus(rs.getString(7));
            debitCard.setDomesticLimit(rs.getDouble(8));
            debitCard.setInternationalLimit(rs.getDouble(9));
            return debitCard;
        }
    }






}

