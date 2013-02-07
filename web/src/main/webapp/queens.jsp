<html>

<%@ page import="com.hp.hplab.example.NQueen"%>
<%@ page contentType="text/html;charset=UTF-8"%>.
<head>
<link type="text/css" rel="stylesheet" href="chess.css">
<link type="text/css" rel="stylesheet" href="gnomechess.css">

</head>

<body>
	<table id="flatChessboard"
		style="width: 500px; height: 500px; margin-bottom: 6px; margin-top: 6px;">
		<%
			int n = 8;
			NQueen queen = new NQueen();
			int[] queens = queen.getSolution(n);
			for (int i = 0; i < n; i++) {
		%>
		<tr>
			<%
				for (int j = 0; j < n; j++) {
						String className;
						if ((i + j) % 2 == 0) {
							className = "whiteSquares";
						} else {
							className = "blackSquares";
						}
			%>

			<td class="<%=className%>">
				<%
					if (queens[i] == j) {
				%> <span>♛</span> <%
 	}
 %>

			</td>
			<%
				}
			%>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>