package jndi.update;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/update/data/")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Context context= new InitialContext();
            Connection connection=null;
//            PreparedStatement preparedStatement;
            PreparedStatement preparedStatement;
            DataSource dataSource = (DataSource) context.lookup("java:/OracleDS");
            connection = dataSource.getConnection();
            String username=req.getParameter("user");
            String password = req.getParameter("password");
            if(username !=null && password !=null){
                String query = "update account set password=? where username=?";
                preparedStatement=connection.prepareStatement(query);
                preparedStatement.setString(1,password);
                preparedStatement.setString(2,username);
                int updatesRows=preparedStatement.executeUpdate();
                if (updatesRows==1)
                    resp.getWriter().println("Account updated");
                else
                    resp.getWriter().println("Failed to update");
            }
        }catch (NamingException | SQLException e){
            e.printStackTrace();
        }
    }
}