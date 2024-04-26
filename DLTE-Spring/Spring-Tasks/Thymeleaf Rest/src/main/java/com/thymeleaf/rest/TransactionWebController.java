package com.thymeleaf.rest;

import com.thymeleaf.rest.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
public class TransactionWebController {

    @Autowired
    TransactionServices transactionServices;

    @GetMapping("/new")
    public String newtransaction(Model model){
        model.addAttribute("transaction",new Transaction());
        return "newtransaction";
    }
    @RequestMapping(value = "/new" ,method = RequestMethod.POST)
    public String addTransaction(@ModelAttribute Transaction transaction, BindingResult bindingResult, Model model){
        model.addAttribute("transaction",transaction);
        Transaction nnewt=transactionServices.apiSave(transaction);
        if(nnewt==null && bindingResult.hasErrors()){
            model.addAttribute("status","Transaction failed!");
            return "newtransaction";

        }else{
            model.addAttribute("status","Transaction successful!");
            return "index";
        }
    }


}
