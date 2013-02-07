<html>

<%@ page import="com.hp.hplab.example.PropertiesReader"%>
<%@ page import="java.util.Properties"%>
<%@ page import="java.util.Enumeration"%>

<%
PropertiesReader reader=new PropertiesReader();
Properties	properties =reader.getResourceProperties("example.properties");
Enumeration<?> enumerations = properties.propertyNames();

%>


<body>
	<table>
		<thead>
			<tr>
				<td>Property</td>
				<td>Value</td>
			</tr>

		</thead>
		<%
while (enumerations.hasMoreElements()){
	String key=(String) enumerations.nextElement();
	String value=(String)properties.get(key);
			
			%>

		<tr>
			<td><%=key %></td>
			<td><%=value %></td>
		</tr>

		<%
}

		 %>




	</table>

</body>


</html>