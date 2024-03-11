package first;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="MyFirstServlet", value ="/first/api")
public class MyFirstServlet extends HttpServlet {
Logger logger;
public void  destroy(){
    logger.info("Destroy servlet");
}
public void init() throws ServletException{
    logger= LoggerFactory.getLogger(MyFirstServlet.class);
    logger.info("Server initialized");
}
protected void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException{
    logger.info("Servlet Get has executed");
}


}
