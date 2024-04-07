package debits.cards.dao.services;

import debits.cards.dao.entities.DebitCard;
import debits.cards.dao.exceptions.DebitCardException;
import debits.cards.dao.remotes.DebitCardRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.ResourceBundle;

@Service
public class DebitCardService implements DebitCardRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
   Logger logger = LoggerFactory.getLogger(DebitCardService.class);
   ResourceBundle resourceBundle = ResourceBundle.getBundle("application") ;

    @Override
    public List<DebitCard> listAllCards() throws SQLSyntaxErrorException {
        List<DebitCard> debitCardList=null;
        try {
             debitCardList = jdbcTemplate.query("SELECT * FROM mybank_app_debitcard where debitCard_status='inactive' or debitCard_status='active' ", new DebitCardMapper());
             logger.info(resourceBundle.getString("list.total.cards"),debitCardList.size());
        }catch (DataAccessException e){
            logger.error(resourceBundle.getString("list.sql.error"));
            throw new SQLSyntaxErrorException(e);
        }
        if(debitCardList.size()==0)
            throw new DebitCardException(resourceBundle.getString("list.no.data"));

        return debitCardList;

    }

    public class DebitCardMapper implements RowMapper<DebitCard>{

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
//            System.out.println(debitCard.toString());
            return debitCard;
        }
    }

}
