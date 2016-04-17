<%@ include file="include.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>

    <script type="text/javascript">
    		function valid(form){

    			var fail = false;

    			var login = form.login.value;
    			var pass = form.pass.value;

                var stringPattern = /^[a-zA-Z0-9]{4,20}$/i;

    			if(!stringPattern.test(login)){
    				fail = "Incorrect login";
    			} else if(!stringPattern.test(pass)){
    			    fail = "Enter your pass";
    			}
    			if(fail){
    				alert(fail);
    			} else{
				    alert("Enter");
    				<!-- Enter -->
    			}
    		}
    	</script>
</head>
<body>


    <form action="" method="post" name="test" id="form">

    		<label for="login">Enter login</label>
    		<input type="text" name="login" placeholder="login" id="login" title="Enter your login" /></br></br>

    		<label for="pass">Enter pass</label>
    		<input type="password" name="pass" placeholder="pass" id="pass" title="Enter your pass" /></br></br>

            <input type="button" name="button" value="sign up" onclick="valid(document.getElementById('form'))" />

            <a href="localhost:8080/phonebook/registration" title="registration" class="registration">registration</a>
    	</form>

</body>
</html>