const performValidation=()=>{
var isValid = true

var myForm = document.forms['application']
const name = myForm.accountHolderName.value
const account=myForm.accounNumber.value
const contact=myForm.applicantContact.value
const email = myForm.applicantEmail.value
const date=myForm.applyDate.value
const chequeType = myForm.chequebookType.value
const accType = myForm.accType.value

var nameErr = document.getElementById("nameErr")
var accErr = docuent.getElementById("accErr")
var chequeErr = document.getElementById("chequeErr")
var contactErr = document.getElementById("contact")                                                        
var nameErr = document.getElementById("nameErr")
var dateErr = document.getElementById("applyErr")
var emailErr=document.getElementById("emailErr")




try{
    if(!(/[A-Za-z]/).test(name) || (/[0-9@!#$]/).test(name)){
        throw "Requires only alphabets"
    }
}
catch(message){
    isValid=false
    nameErr.innerHTML=message
}
try{
    if(!(/[0-9]{14}/).test(account)){
        throw "Only numbers are allowed here(14 digits)"
    }
}
catch(message){
    isValid=false
    accErr.innerHTML=message
}

try{
    if(chequeType==='Select '||chequeType===''){
        throw "Requires valid purpose"
    }
}
catch(message){
    isValid=false
    chequeErr.innerHTML=message
}


try{
    if(!(/[[1-9]{1}[0-9]{9}]/).test(contact)){
        throw "Requires only 10digit phone number"
    }
}
catch(message){
    isValid=false
    contactErr.innerHTML=message
}
try {
    
    if (!accType) {
        throw('Please select Account Type.');
        
    }
    
} catch (message) {
    isValid=false
    accErr.innerHTML=message    
}
try {
    if (date === '') {
        throw 'Please select Date of Apply.'
        
    }

} catch (message) {

    isValid=false
    dateErr.innerHTML=message   
}
try {
    if (email === ''|| !(/^[^\s@]+@[^\s@]+\.[^\s@]+$/).test(email)) {
        throw 'Please enter valid email'
        
    }

} catch (message) {

    isValid=false
    emailErr.innerHTML=message   
}


return isValid

}