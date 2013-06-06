<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%@ page import="datasource.TypeDataSource" %>
<%@ page import="datasource.UserClicksDataSource" %>
<%@ page import="datasource.xml.XmlDataSourcesRepository" %>
<%@ page import="generators.GA" %>
<%@ page import="generators.MenuGenerator" %>
<%@ page import="storedentities.Type" %>
<%@ page import="java.util.Collection" %>

<%
    UserClicksDataSource userClicksDataSource = datasource.stub.StubDataSourcesRepository.UserClicksDataSourceInstance;
    TypeDataSource typeDataSource = new XmlDataSourcesRepository().XmlTypeDataSourceInstance;

    String viewer_id = request.getParameter("viewer_id");
%>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="css/buttons.css"/>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <link rel="stylesheet" type="text/css" href="css/welcome.css"/>
    <link href='https://fonts.googleapis.com/css?family=Tenor+Sans&subset=latin,cyrillic' rel='stylesheet'
          type='text/css'>
    <link rel="stylesheet" href="font/FontAwesome/css/font-awesome.min.css">
    <script type="text/javascript" src="js/ButtonsClick.js"></script>
    <script type="text/javascript" src="https://vk.com/js/api/xd_connection.js?2"></script>
    <script type="text/javascript" src="js/VKFunctions.js"></script>
    <script type="text/javascript">VK.init();</script>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body id="mainbody">
<%
    if (!userClicksDataSource.existRecord(viewer_id)) {
        request.setCharacterEncoding("utf-8");
%>
<jsp:include page="WelcomeScreen.jsp"/>
<%}%>


<table id="frametable">
    <tr id="menurow" class="btn_menu">
        <td style="box-shadow: 1px 2px 5px 1px gray;">
            <div id="menucell">
                <%
                    Collection<Type> types = typeDataSource.getAllTypes();
                    MenuGenerator menuGenerator = new MenuGenerator();
                %>
                <%=menuGenerator.generateMenu(types)%>

            </div>
            <div id="buttonscell">
                <a href="#" class="btn_unchecked" id="helpMenuBtn" onclick="clickHelp()">?</a>
                <a href="#" class="btn_checked " onclick="clickBackButton()"><i class="icon-reply"></i></a>
            </div>
        </td>
    </tr>
    <tr>
        <td id="contentd" colspan="2">
            <div id="content">

                <%=GA.GACode%>

                <i class='icon-spinner icon-spin' id="spinner"></i>

                <iframe id='myIframe' src='/RCTD/main?viewer_id="<%=viewer_id%>"'
                        width='800' height='440' style='border: transparent 0px;'
                        onload="processingComplete()"></iframe>
            </div>
            <br/>
        </td>
    </tr>
    <tr>
    </tr>
</table>
</body>
</html>