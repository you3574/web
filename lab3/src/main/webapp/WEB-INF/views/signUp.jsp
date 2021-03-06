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
h1 {
	color: #555;
}
</style>

</head>
<body>

	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-form-title">
					<img style="width: 50%; margin-top: 20px"
						src="/res/images/login.png"> <span
						class="login100-form-title-1"> 졸업관리시스템 </span>
				</div>

				<form method="post" modelAttribute="student">
					<div class="form-group" style="width: 70%;">
						<label>이름</label> :</label> <input id="studentName" path="studentName"
							class="form-control" />
					</div>
					<div class="form-group" style="width: 70%;">
						<label>학번 :</label> <input id="studentNumber" path="studentNumber"
							class="form-control" />
					</div>
				</form>
			</div>
			<br />
			<!-- Footer -->
			<div class="modal-footer">
				<button type="button" id="button1" class="btn btn-default">가입하기</button>
				&nbsp;
				<button type="button" class="btn btn-default" data-dismiss="modal">되돌아가기</button>
			</div>
		</div>
	</div>


	<script>
 	 function checkVal(){
 		if($('#studentName').val()==''){
 			alert("이름을 입력해주세요.");
 			return false;	
 		}else if($('#studentNumber').val()==''){
 			alert("학번을 입력해주세요.");
 			return false;	
 		}else if($('#password').val()==''){
 			alert("비밀번호를 입력해주세요.");
 			return false;	
 		}
 		return true;				
 	};

	$('#button1').bind({
		click: function(e){
    		e.preventDefault();
			
    		//입력폼 값 검사
			if(!checkVal()){
				return false;
			}

			//전송되는 값을 정리
			//부서는 선택된 lv3만 보내기
			var sendData;
			sendData = JSON.stringify({
	            "studentName":			$('#studentName').val(),
	            "studentNumber":		$('#studentNumber').val(),
	        });		

			$.ajax({
    			url: "signup",
    			method: "POST",
    			data: sendData,
    			dataType: "json",
    			contentType: "application/json;charset=UTF-8",
    			beforeSend: function(){
    				if(confirm('회원가입 하시겠습니까?')){	
						return true;
					}
					else{
						return false;
					}    				
    			},
    			success:function(data){
    				if(data){
    					alert(data+"가입에 성공하셨습니다.");
    					location.reload();
    				}else{
    					alert("에러");
    				}
				},
				error: function(){
            		alert("에러");
         	   }
			});
		}//end of click
	});
	

      </script>


</body>
</html>