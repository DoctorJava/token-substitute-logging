<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%= ( request.getAttribute("message") != null ? request.getAttribute("message") : "") %>
<%! Random randomValue=new Random(); %>
<fieldset>
	<legend>Demo Logged Activity</legend>
	<form method=POST action=ActionDemo >  
		User:  <select name=user><option>alfred.barnes<option>charlotte.davies</select> <br/>
		Action: <select name=action><option>Copy<option>Delete</select> <br/>   
		Subject: <select name=hostip><option>192.168.101.1
				 <option>192.168.101.<%=(Math.abs(randomValue.nextInt())%255) %>
				 </select> <br/>   
		Result: <select name=result><option>Success<option>Failure</select> <br/>   
		<input type="submit" value="Go"/> 
	</form>
</fieldset>

<h2>Resources</h2>
<ul>
	<li>https://stackify.com/java-logging-best-practices/
	<li>http://logback.qos.ch/manual/mdc.html
</ul>

</body>
</html>