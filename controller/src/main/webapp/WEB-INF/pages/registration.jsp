<%@ include file="include.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>

    <script type="text/javascript">
    		function valid(form){

    			var fail = false;

    			var login = form.login.value;
    			var firstName = form.firstName.value;
    			var lastName = form.lastName.value;
    			var email = form.email.value;
    			var mobileTelephone = form.mobileTelephone.value;
    			var homeTelephone = form.homeTelephone.value;
    			var pass = form.pass.value;
    			var repass = form.repass.value;

                var stringPattern = /^[a-zA-Z0-9]{4,20}$/i;
    			var mailPattern = /[0-9a-z_-]+@[0-9a-z_-]+\.[a-z]{2,5}/i;
    			var telephonePattern = /^\+\d{2}\(\d{3}\)\d{3}-\d{2}-\d{2}$/i;

    			if(!stringPattern.test(login)){
    				fail = "Incorrect login";
    			} else if(!stringPattern.test(firstName)){
    			    fail = "Incorrect first name";
    			} else if(!stringPattern.test(lastName)){
                    fail = "Incorrect last name";
      			} else if(!mailPattern.test(email)){
    			    fail = "Enter your email";
    			} else if(!telephonePattern.test(mobileTelephone)){
    			    fail = "Incorrect mobile phone";
    			} else if(!telephonePattern.test(homeTelephone)){
    			    fail = "Incorrect home phone";
    			} else if(!stringPattern.test(pass)){
    			    fail = "Enter your pass";
    			} else if(pass != repass){
    		        fail = "Repass must be same pass";
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

    		<label for="firstName">Enter first name</label>
    		<input type="text" name="firstName" placeholder="firstName" id="firstName" title="Enter your first name"/></br></br>

    		<label for="lastName">Enter last name</label>
    		<input type="text" name="lastName" placeholder="lastName" id="lastName" title="Enter your last name"/></br></br>

    		<label for="email">Enter email</label>
    		<input type="text" name="email" placeholder="email" id="email" title="Enter your email"/></br></br>

    		<label for="mobileTelephone">Enter mobile phone</label>
    		<input type="text" name="mobileTelephone" placeholder="+38(0__)___-__-__" id="mobileTelephone" title="Enter your mobile phone +38(055)555-55-55"/></br></br>

            <label for="homeTelephone">Enter home phone</label>
    		<input type="text" name="homeTelephone" placeholder="+38(0__)___-__-__" id="homeTelephone" title="Enter your home phone +38(044)555-55-55"/></br></br>

    		<label for="pass">Enter pass</label>
    		<input type="password" name="pass" placeholder="pass" id="pass" /></br></br>

    		<label for="repass">Enter pass</label>
    		<input type="password" name="repass" placeholder="repass" id="repass" /></br></br>

            <input type="button" name="button" value="ready" onclick="valid(document.getElementById('form'))" />


    	</form>

</body>
</html>