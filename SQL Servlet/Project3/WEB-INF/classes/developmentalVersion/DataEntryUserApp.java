package developmentalVersion;

// Project 3 - Summer 2022 - CNT 4714
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.ResultSetMetaData;



public class DataEntryUserApp extends HttpServlet
{
	private Connection connection;
	private Statement statement;


@Override
public void init(ServletConfig config) throws ServletException
{
	super.init(config);
	
	try
	{
		Class.forName(config.getInitParameter("databaseDriver"));
		connection = DriverManager.getConnection(config.getInitParameter("databaseName"),
		config.getInitParameter("username"), config.getInitParameter("password"));
		statement = connection.createStatement();
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
}

@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
	
	doGet(request, response);
	
/*	String sqlStatement = request.getParameter("sqlStatement");
	String message = "";
	
	try {
		
		
		if ("select") {
			
		}
		
		else {
			
		}
		} 	catch(SQLException e) {
			message = "<tr bgcolor=#ff0000><td><font color=#ffffff><b>Error executing the SQL statement:</b><br>" + e.getMessage() + "</tr></td></font>";
		}*/
	}

/*
public void getDBConnection() {
	Properties properties = new Properties();
	FileInputStream filein = null;
	MysqlDataSource dataSource = null;
	try {
		filein = new FileInputStream("C:/Program Files/Apache Software Foundation/Tomcat 10.0/webapps/Project3/Web-INF/lib/dataentry.properties");
		properties.load(filein);
		dataSource = new MysqlDataSource();
		dataSource.setUrl(properties.getProperty("MYSQL_DB_URL"));
		dataSource.setUser(properties.getProperty("MYSQL_DB_USERNAME"));
		dataSource.setPassword(properties.getProperty("MYSQL_DB_PASSWORD"));
		connection = dataSource.getConnection();
		statement = connection.createStatement();
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
	catch (IOException e) {
		e.printStackTrace();
	}
}
*/
public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	String snum = request.getParameter("snum");
	String pnum = request.getParameter("pnum");
	String jnum = request.getParameter("jnum");
	String quantity = request.getParameter("quantity");
	String textBox = "Text";
	String result = null;
	
	
	try {
		result = updateQuery(snum);
	} catch (SQLException e3) {
		// TODO Auto-generated catch block
		e3.printStackTrace();
	}
	try {
		result = updateQuery(pnum);
	} catch (SQLException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
	try {
		result = updateQuery(jnum);
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	try {
		result = updateQuery(quantity);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
			
	HttpSession session = request.getSession();
	session.setAttribute("result", result);
	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/dataentry.jsp");
	dispatcher.forward(request, response);
}

public String selectQuery(String textBox)
	throws SQLException {
	String result;
	ResultSet table = statement.executeQuery(textBox);
	ResultSetMetaData metaData = table.getMetaData();
	int col = metaData.getColumnCount();
	String tableHTML = "<div class='container-fluid'><div class='row justify-content-center'><div class='table-responsive-sm-10 table-responsive-md-10 table-responsive-lg-10'><table class='table'>";
	String tableColumnsHTML = "<thead class='thead-dark'><tr>";
	
	for (int i = 1; i <= col; i++) {
		tableColumnsHTML += "<th scope='col'>" + metaData.getColumnName(i) + "</th>";
	}
	
	tableColumnsHTML += "</tr></thead>";
	
	String tableBodyHTML = "<tbody>";
	while (table.next()) {
		tableBodyHTML += "<tr>";
			for (int i = 1; i <= col; i++) {
				if (i == 1)
					tableBodyHTML += "<td scope'row'>" + table.getString(i) + "</th>";
				else
					tableBodyHTML += "<td>" + table.getString(i) + "</th>";
			}
			tableBodyHTML += "</tr>";
		}

		tableBodyHTML += "</tbody>";

		String tableClosing = "</table></div></div></div>";
		result = tableHTML + tableColumnsHTML + tableBodyHTML + tableClosing;

		return result;
	}
	
	public String updateQuery(String text)
		throws SQLException {
		String result = null;
		int rows = 0;
		
		ResultSet beforeCheck = statement.executeQuery("select COUNT(*) from shipments where quantity >= 100");
		beforeCheck.next();
		int numOfShipmentsMax = beforeCheck.getInt(1);
		statement.executeUpdate("create table shipmentsBeforeUpdate like shipments");
		statement.executeUpdate("insert into shipmentsBeforeUpdate select * from shipments");
		rows = statement.executeUpdate(text);
		result = "<div> The statement executed successfully.</div><div>" + rows + " row(s) affected</div>";
		ResultSet afterCheck = statement.executeQuery("select COUNT(*) from shipments where quantity >= 100");
		afterCheck.next();
		int finalCheck = afterCheck.getInt(1);
		result += "<div>" + numOfShipmentsMax + " < "  + finalCheck + "</div>";
		
		if (numOfShipmentsMax < finalCheck)
		{
			int increment = statement.executeUpdate("update suppliers set status = status + 5 where snum in ( select distinct snum from shipments left join shipmentsBeforeUpdate using (snum, pnum, jnum, quantity) where shipmentsBeforeUpdate.snum is null)");
			result += "<div>Business Logic Detected! - Updating Supplier Status</div>";
			result += "<div>Business Logic Updated " + increment + "Supplier(s) status marks</div>";
		}
		
		statement.executeUpdate("drop table shipmentsBeforeUpdate");
		return result;
	}
}
