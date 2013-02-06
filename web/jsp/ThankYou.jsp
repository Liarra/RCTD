<%@ page import="datasource.DonateDataSource" %>
<%@ page import="datasource.xml.XmlDataSourcesRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%!
    private String AdScripts;
    private String AdHTML;
    private String image;
%>

<%
    Long donateId=new Long(request.getParameter("id"));

    DonateDataSource XmlDonateDataSourceInstance=new XmlDataSourcesRepository().XmlDonateDataSourceInstance;
    image=XmlDonateDataSourceInstance.getDonateById(donateId).getPicURL();

    AdScripts=request.getParameter("adS");
    AdHTML=request.getParameter("adH");
%>
<link href="css/thankyou.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" id="adscript"> <%=AdScripts%> </script>
<div id="ThankYouBar" style="background-image:url(<%=image%>);">
    <div id="ThankYouString">Спасибо!</div>
</div>

<div id="AdPics"><%=AdHTML%></div>
