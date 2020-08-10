<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta name="description" content="welcome to codeglim">
   <meta name='copyright' content='codeglim'>
   <meta name="viewport" content="width=device-width, initial-scale=1">   

      <style>
      h1{
      color:#555 ;
      }
      </style>

<script src="http://code.jquery.com/jquery-latest.js"></script>
</head>
<body>
 
   <div class="limiter">
      <div class="container-login100">
         <div class="wrap-login100">
            <div class="login100-form-title">
               <span class="login100-form-title-1">
                     졸업관리시스템
               </span>
            </div>

             <form class="login100-form">
               <div class="wrap-input100">
                  <span class="label-input100">학번</span>
                  <input class="input100" type="text" name="usernumber">
               </div>
               <br/>
               
               <div class="wrap-input100">
                  <span class="label-input100">이름</span>
                  <input class="input100" type="text" name="name">
               </div>
              

               <div class="single-service" style="padding-top:10px">
                  <button class="btn btn-default" id="loginbutton">
                     Login
                  </button>
                </div>
                
                </form>
             </div>
         </div>
      </div>
   
   
      
  
  <script>
	$('#loginbutton').bind({
		click: function(e){
    		e.preventDefault();
    		
    		if($('input[name=usernumber]').val()==''){
    			alert("학번을 입력해주세요.")
    			return false;
    		}else if($('input[name=name]').val()==''){
    			alert("비밀번호를 입력해주세요.")
    			return false;
    		}
    		
    		var loginForm = $('<form></form>');

    		loginForm.attr("action","login");
    		loginForm.attr("method","post");
    		loginForm.appendTo('body');
    		var idx = $("<input type='hidden' value="+$('input[name=usernumber]').val()+" name='usernumber'>");
    		var pwd = $("<input type='hidden' value="+$('input[name=name]').val()+" name='name'>");
    		loginForm.append(idx).append(pwd);
    		loginForm.submit();
		}
	});

      </script>
   

</body>
</html>