package com.thymeleaf.transaction.mvc;


import com.thymeleaf.transaction.entity.Transaction;
import com.thymeleaf.transaction.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping()
public class TransactionWebController {

    @Autowired
    TransactionService transactionServices;



    @GetMapping("/ ")
    public String index(){
        return "index";
    }
//
//    @GetMapping("/display")
//    public String display(Model model){
//        model.addAttribute("transaction",new Transaction());
//        return "index";
//    }

    @GetMapping("/dash")
    public String homePage(){
        return "dashboard";
    }

    @GetMapping("/new")
    public String addTransaction(Model model){
        Transaction transaction=new Transaction();
        model.addAttribute("transaction",new Transaction());
        return "newTransaction";
    }
    @RequestMapping(value = "/new" ,method = RequestMethod.POST)
    public String newTransaction(@Valid @ModelAttribute Transaction transaction, BindingResult bindingResult, Model model){
        model.addAttribute("transaction",transaction);
        if(!bindingResult.hasErrors()){
            Transaction transaction1=transactionServices.apiSave(transaction);
            model.addAttribute("status","Transaction successful!");
            model.addAttribute("transaction",transaction1);
            return "dashboard";
        }else{
            model.addAttribute("status","Transaction failed!");
            return "newTransaction";
        }
    }

    @GetMapping("/search")
    public String searchTransaction(Model model){
        Transaction transaction=new Transaction();
        model.addAttribute("transaction",new Transaction());
        return "search";
    }

    @GetMapping("/result")
    public String searchTransactionBy(@RequestParam("filter") String filter, @RequestParam("search") String search,Model model){
        List<Transaction> transactionList=null;
        switch (filter){
            case "filterBySender":transactionList=transactionServices.apiFindBySender(search);
                break;
            case "filterByReceiver":transactionList=transactionServices.apiFindByReceiver(search);
                break;
            case "filterByAmount":transactionList=transactionServices.apiFindByAmount(Double.parseDouble(search));
                break;
        }
        model.addAttribute("transactions",transactionList);
        return "search";
    }

    @GetMapping("/delete")
    public String delete(Model model){
        //Transaction transaction=new Transaction();
        model.addAttribute("transaction",new Transaction());
        return "delete";
    }

    @GetMapping("/remove")
    public String deleteTransaction(@RequestParam("startDate") String start,@RequestParam("endDate") String end,Model model){
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy");
        Date startDate,endDate;
        try {
            startDate = (Date) dateFormat.parse(start);
            endDate = (Date) dateFormat.parse(end);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error";
        }
        String delete=transactionServices.deleteTransaction(startDate,endDate);
        model.addAttribute("delete",delete);
        return "dashboard";

    }

}
