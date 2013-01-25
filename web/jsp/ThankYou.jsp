<%@ page import="generators.PageGenerator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%!
    private String AdScripts;
    private String image;
%>

<%
    Long donateId=new Long(request.getParameter("id"));
    image=new PageGenerator().getDonatePicAddress(donateId);
%>
<link href="../css/thankyou.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript"><%=AdScripts%> </script>
<div id="ThankYouBar" style="background-image:url(<%=image%>);">
    <div id="ThankYouString">Спасибо!</div>
</div>

<div id="AdPics">000</div>
