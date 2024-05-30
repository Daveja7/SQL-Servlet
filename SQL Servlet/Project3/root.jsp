<!doctype html>
<%--
/* Name: Dave Jaikaran
  Course: CNT 4714-Summer 2022-Project 3
  Assignment title: A Three-Tier Distributed Web-Based Application
  Date: August 4th, 2022
*/
--%>


<%
String textBox = (String) session.getAttribute("textBox");
String result = (String) session.getAttribute("result");
if(result == null){
    result = " ";
}
if(textBox == null){
   textBox = " ";
}
%>


<html>
<head>
  <title> CNT 4714 Remote Database Management System</title>
  <style>
  <!--
  body {background: purple; text-align: center; font-family: Arial;}
  h1 {color:yellow; font-size:28pt;}
  h2 {color: lime; font-size:24pt;}
  input {color:yellow; background:#665D1E; font-weight:bold; font-size: 16pt;}
  input[type="submit"] {color: lime;}
  input[type="reset"] {color: red;}
p {color:black; font-size:13pt;}
table{font-family: Verdana; border:3px solid black;}
textarea {background: blue; color: white; font-family: Verdana; font-size:15pt; width: 900px; height: 275px;}
th, td {padding: 5px; border: 1px solid black;}
.highlight {color: red;}
.main {color: white;}
#bl {color: #708090;}
-->
</style>
<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
  function eraseText() {
    $("#cmd").html("");
  }
</script>
<script type="text/javascript">
  function eraseData() {
    $("#data").remove();
  }
</script>
</head>

<body>
  <h1>Welcome to the Summer 2022 Project 3 Enterprise Database System</h1>
  <h2>A Servlet/JSP-based Multi-tiered Enterprise Application using a Tomcat Container</h2>
  <br>
  <p class="main">You are connected to the Project 3 Enterprise System database as a <span class="highlight">root-level</span>
  user. <br />
    Please enter any valid SQL Query or update command in the box below. <br />
    <br />
  </p>
  <form action="RootUserApp" method="post">
    <textarea id="cmd" name="textBox" cols=60 rows=8><%=textBox%></textarea><br>
    <br>
    <input type="submit" value="Execute Command" /> &nbsp; &nbsp; &nbsp;
    <input type="reset" value="Reset Form" onclick="javascript:eraseText();" /> &nbsp; &nbsp; &nbsp;
    <input type="button" value="Clear Results" onclick="javascript:eraseData();" />
</form>
<p class="main"> <br /> All execution results will appear below this line. </p>
<br>
<center>
<p>
<b class="main">Database Results:</b><br>
<table id="data">
  <%= result %>
</table>
</p>
</center>
</body>
</html>
