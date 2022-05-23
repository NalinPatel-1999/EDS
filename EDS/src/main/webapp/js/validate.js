/**
 * 
 */

var letters=/^[A-Za-z]+$/;	

function fName(){
	
	
	//var name=document.getElementById("firstname").value;
	if(document.getElementById("firstname").value.match(letters)==null){
		document.getElementById("firstname1").innerHTML="Invalid Name.";
		}else{
			document.getElementById("firstname1").innerHTML="";
			}
	}
/************************************END-METHOD*******************************************************/
function lName(){
	

	//var name=document.getElementById("firstname").value;
	if(document.getElementById("lastname").value.match(letters)==null){
		document.getElementById("lastname1").innerHTML="Invalid Last Name.";
		}else{
			document.getElementById("lastname1").innerHTML="";
			}
    }
/************************************END-METHOD*******************************************************/
function confirmPassword(){
	
	if(document.getElementById("password").value.match(document.getElementById("confirmpassword").value)==null
	  && document.getElementById("confirmpassword").value.match(document.getElementById("password").value)==null){
		document.getElementById("confirmpassword1").innerHTML="Password must be same.";
		}else{
			document.getElementById("confirmpassword1").innerHTML="";
			}
    }
/************************************END-METHOD*******************************************************/
function gmail(){
	if(document.getElementById("login").value.match(/(\W|^)[\w.+\-]*@gmail\.com(\W|$)/)==null){
		document.getElementById("login1").innerHTML="Please Enter valid Mail Address.";
		}else{
			document.getElementById("login1").innerHTML="";
			}
	}
/************************************END-METHOD*******************************************************/
function validatePassword(){
	//alert("hii");
	
	
	if(document.getElementById("password").value.match(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[#$^+=!*()@%&]).{8,15}$/)==null){
		
		document.getElementById("password1").innerHTML="Password must contain a capital letter,<br>Number,special character and length 8-15.";
	}else{
		document.getElementById("password1").innerHTML="";
	}
	}
/************************************END-METHOD*******************************************************/
function mobileNo(){
	
	
    if(document.getElementById("mobileno").value.match(/^[6-9]\d{9}$/)==null){
		
		document.getElementById("mobileno1").innerHTML="Invalid Mobile Number.";
	}else{
		document.getElementById("mobileno1").innerHTML="";
	}
	}
/************************************END-METHOD*******************************************************/
function lastCheck(){
	

	if(document.getElementById("firstname").value.match(letters)!=null
			&& document.getElementById("lastname").value.match(letters)!=null 
			&& document.getElementById("login").value.match(/(\W|^)[\w.+\-]*@gmail\.com(\W|$)/)!=null 
			&& document.getElementById("password").value.match(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[#$^+=!*()@%&]).{8,10}$/)!=null
		    && document.getElementById("password").value.match(document.getElementById("confirmpassword").value)!=null 
		    && document.getElementById("mobileno").value.match(/^[6-9]\d{9}$/)!=null
	  ){
		
		return true;
		
	}else{
		
		return false;
	}
	}
/************************************END-METHOD*******************************************************/

function compareOTP(otp){
	
	var page=document.getElementById("check").content;
	
   if(document.getElementById("OTP").value.match(otp)==null){
	   
	   document.getElementById("OTP1").innerHTML="Enter right OTP";
	   return false;
   }else{
	   document.getElementById("OTP1").innerHTML="";
	   
	   if(page=="ResetPassword"){
	   if(document.getElementById("password").value.match(document.getElementById("confirmpassword").value)!=null
			   && document.getElementById("confirmpassword").value.match(document.getElementById("password").value)!=null
			   && document.getElementById("password").value.match(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[#$^+=!*()@%&]).{8,10}$/)!=null){
	   
		   return true;
   }else{
	   return false;
   }
   }else if(page=="ForgetPassword"){
	   
	   return true;
   }
   }
   }
/************************************END-METHOD*******************************************************/