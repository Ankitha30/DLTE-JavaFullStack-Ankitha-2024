package element.spring.boot.springjdbctemplate.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyBankUserService implements UserDetailsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public MyBankUsers signUp(MyBankUsers myBankUsers){
        jdbcTemplate.update("insert into mybankusers values(?,?,?,?,?,?,?)",new Object[]{
                myBankUsers.getUsername(),
                myBankUsers.getPassword(), myBankUsers.getRole(),
                myBankUsers.getAddress(),
                myBankUsers.getContact(),
                myBankUsers.getEmail()

        });
        return myBankUsers;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyBankUsers myBankUsers = findByUserName(username);
        if(myBankUsers == null)
            throw new UsernameNotFoundException(username);
        return myBankUsers;
    }

    private MyBankUsers findByUserName(String username) {
        MyBankUsers myBankUsers=jdbcTemplate.queryForObject("select * from  transaction_users where username=?",
              new Object[]{username},new BeanPropertyRowMapper<>(MyBankUsers.class));
        return myBankUsers;
    }
}
