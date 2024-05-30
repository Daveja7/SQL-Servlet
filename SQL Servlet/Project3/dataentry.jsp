<!doctype html>
<%--
/* Name: Dave Jaikaran
  Course: CNT 4714-Summer 2022-Project 3
  Assignment title: A Three-Tier Distributed Web-Based Application
  Date: August 4th, 2022
*/
--%>


<%
String snum = (String) session.getAttribute("snum");
String pnum = (String) session.getAttribute("pnum");
String jnum = (String) session.getAttribute("jnum");
String quantity = (String) session.getAttribute("quantity");

if(snum == null){
    snum = " ";
}

if(pnum == null){
    pnum = " ";
}


if(jnum == null){
    jnum = " ";
}

if(quantity == null){
    snum = " ";
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
  function keepData() {
    $("#snum").html("<%=snum%>");
  }
</script>
<script type="text/javascript">
  function eraseData(){
    $("data").remove();
  }
</script>
</head>
<body>
  <h1>Welcome to the Summer 2022 Project 3 Enterprise Database System>/h1>
  <h2>A Servlet/JSP-based Multi-tiered Enterprise Application Using A Tomcat Container</h2>
  <hr>
  <br>
  <p class="main">You are connected to the Project 3 Enterprise database as a <span class="highlight">data-entry-level
  </span> user. <br />
    Please enter the data values in the form below to add a new record to the shipments table. <br />
    <br>
    <br>
    </p>
    <form action="DataEntryUserApp" method="post">
    <table>
    <tr>
      <th>snum</th><th>pnum</th><th>jnum</th><th>quantity</th>
    </tr>
    <tr>
      <td><textarea name="snum" id="snum" rows=1 <%=snum%>></textarea> </td>
      <td><textarea name="pnum" id="pnum" <%=pnum%> rows=1 ></textarea></td>
      <td><textarea name="jnum" id="jnum" <%=jnum%> rows=1 ></textarea></td>
      <td><textarea name="quantity" id="quantity" <%=quantity%> rows=1 ></textarea></td>
    </tr>
  </table>
  <input type="submit" value="Enter Record into Database" onclick="javascript:keepData();" /> &nbsp; &nbsp; &nbsp;
  <input type="reset" value="Clear Results" onclick="javascript:eraseData();" /> &nbsp; &nbsp; &nbsp;
</form>
<center>
  <br><hr>
  <br><br>
    <b class="main">Database Results:</b><br><br>
    <table id="data">
      <%= snum %>
    </table>
    </p>
    </center>
    </body>
    </html>
