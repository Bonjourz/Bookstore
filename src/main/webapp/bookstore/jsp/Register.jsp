<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 	String path = request.getContextPath();
 %>
<!DOCTYPE html>
<!-- saved from url=(0042)http://v2.bootcss.com/examples/signin.html -->
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <title>Register</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

      .form-signin {
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }

    </style>
    <link href="css/bootstrap-responsive.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="../assets/js/html5shiv.js"></script>
    <![endif]-->

    <!-- Fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="http://v2.bootcss.com/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="http://v2.bootcss.com/assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="http://v2.bootcss.com/assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="http://v2.bootcss.com/assets/ico/apple-touch-icon-57-precomposed.png">
    <link rel="shortcut icon" href="http://v2.bootcss.com/assets/ico/favicon.png">
  </head>

  <body>

    <div class="container">
      

      <form class="form-signin" >
        <div style="text-align:center"><h2 class="form-signin-heading">Register</h2></div>
        	<label>Email:</label><br>
        	<input type="text" class="input-block-level" placeholder="Email" name="email" id="email">
        	<button class="btn btn-primary" id="valid" type="button">Valid?</button>
        	<p id="info"></p>
        	<br><label>UserName:</label><br>
        	<input type="text" class="input-block-level" placeholder="Username" name="username">
        	<br><label>Password:</label><br>
        	<input type="password" class="input-block-level" placeholder="Password" name="pwd">
        	<br><label>Confirm Your Password:</label><br>
        	<input type="password" class="input-block-level" placeholder="Confirm Your Password" name="pwd-confirm">
        <div class="row">
          <p class="text-center">
            <div style="text-align:center;">
            	<button id="submit" type="button" class="btn btn-primary">Register</button>
            </div>

          </p>
        </div>
        <br>
        <button type="button" class="btn btn-primary"><a href="index"><-Back</a></button>
      </form>

    </div> <!-- /container -->
    
    <script src="<%=path%>/bookstore/js/jquery.min.js"></script>
	<script src="<%=path%>/bookstore/js/bootstrap.min.js"></script>
	<script src="<%=path%>/bookstore/js/jquery.dataTables.min.js"></script>
	<script src="<%=path%>/bookstore/js/dataTables.bootstrap.min.js"></script>
	<script src="<%=path%>/bookstore/js/bookstore.js"></script>
	<script src="<%=path%>/bookstore/js/bootbox.min.js"></script>
	
	<script>
		var canRegister = 0;
		$(document).ready(function() {
			$('#dataTables').DataTable({
				responsive : true
			});
		});
		
		$(function () {
			$('#valid').click(function(e){
				var email = $("input[name='email']").val();
				if (email == "") {
					$('#info').html("Please input email");
					return;
				}
				
				jQuery.ajax({
					url : 'CheckEmail',
					processData : true,
					dataType : "text",
					data : {
						email : email
					},
					success : function(data) {
						var info = eval("("+data+")");
						$('#info').html(info);
						canRegister = 1;
					}
				});
			});
			
			$('#email').on('input', function(e){
				$('#info').html("");
			});
			
			$('#submit').click(function(e) {
				var pwd = $("input[name='pwd']").val();
				var pwd_confirm = $("input[name='pwd-confirm']").val();
				if (pwd != pwd_confirm) {
					alert("Two passwords you input is not the same!");
					return;
				}
				
				else if (canRegister != 1) {
					alert("Please input valid email address");
					return;
				}
				
				var userName = $("input[name='username']").val();
				var email = $("input[name='email']").val();
				jQuery.ajax({
					url : 'RegisterBuyer',
					processData : true,
					dataType : "text",
					data : {
						email : email,
						userName : userName,
						password : pwd
					},
					success : function(data) {
						data = eval("("+data+")");
						alert(data);
						location.reload();
					}
				});
			});
		});
	</script>
</body></html>

