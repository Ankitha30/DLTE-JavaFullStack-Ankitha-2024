package org.example;

import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/sipcalci/*")
public class SipCalculator extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        String requestPrinciple=req.getParameter("Principle");
        String requestInterest = req.getParameter("Interest");
        String requestYear=req.getParameter("year");
        String requestRegime = req.getParameter("Regime");
        String requestIncome = req.getParameter("AnnualIncome");
        if(requestPrinciple!=null&&requestYear!=null) {
            double compoundInterest = Double.parseDouble(requestInterest);
            double investmentAmount = Double.parseDouble(requestPrinciple);
            int years = Integer.parseInt(requestYear);
            compoundInterest /= (12 * 100);
            years *= 12;
            resp.setContentType("application/json");
            Gson gson = new Gson();
            double returns = (investmentAmount * ((Math.pow((1 + compoundInterest), years) - 1) / compoundInterest) * (1 + compoundInterest));
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(returns);
        }
        else{
            String received = incomeSlab(requestRegime,Double.parseDouble(requestIncome));
            resp.getWriter().println(received);
        }

    }

    public String incomeSlab(String requestRegime, double annualIncome) {

        switch (requestRegime.toLowerCase()){
            case "new":
                if(annualIncome<250000)
                    return("Exempt in new regime");
                else if(annualIncome>=250000 && annualIncome<500000){
//                    newRegime=;
                    return ("In new regime tax slab(5%) will be "+(annualIncome*0.05));
                }
                else if(annualIncome>=50000 && annualIncome<750000){
//
                    return("In new regime tax slab(10%) will be "+(annualIncome*0.1));
                }
                else if(annualIncome>=750000 && annualIncome<1000000){
//
                    return ("In new regime tax slab(15%) will be "+(annualIncome*0.15));
                }
                else if(annualIncome>=1000000 && annualIncome<1250000){
//
                    return("In new regime tax slab(20%) will be "+(annualIncome*0.2));
                }
                else if(annualIncome>=1250000 && annualIncome<1500000){
//
                    return("In new regime tax slab(25%) will be "+(annualIncome*0.25));
                }
                else{
//
                    return ("In new regime tax slab(30%) will be "+(annualIncome*0.3));
                }
//                break;
            case "old" :
                if(annualIncome<250000)
                    return("Exempt in both old and new regims");
                else if(annualIncome>=250000 && annualIncome<500000){
                    return("In old regime tax slab(5%)  for above 250000 and below 500000 will be "+annualIncome*0.05);

                }
                else if(annualIncome>=500000 && annualIncome<7500000){
                    return("In old regime tax slab(20%) for above 500000 and below 7500000 will be "+annualIncome*0.2);
                }
                else if(annualIncome>=750000 && annualIncome<1000000){
                    return("In old regime tax slab(20%) for above 7500000 and below 1000000 will be "+annualIncome*0.2);

                }
                else if(annualIncome>=1000000 && annualIncome<1250000){
                    return("In old regime tax slab(30%) for above 1000000 and below 1250000 will be "+annualIncome*0.3);

                }
                else if(annualIncome>=1250000 && annualIncome<1500000){
                    return("In old regime tax slab(30%) for above 1250000 and below 1500000 will be "+annualIncome*0.3);

                }
                else{
                    return("In old regime tax slab  for above 1500000 will be "+annualIncome*0.3);

                }
//                break;
//            default: return;
        }
        return "Not Applicable to tax";
    }
}
