<%@ page import="generators.PageGenerator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%!
    private String AdScripts;
    private String AdHTML;
    private String image;
%>

<%
    Long donateId=new Long(request.getParameter("id"));
    image=new PageGenerator().getDonatePicAddress(donateId);

    AdScripts=request.getParameter("adS");
    AdHTML=request.getParameter("adH");
%>
<link href="css/thankyou.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript"><%=AdScripts%> </script>
<div id="ThankYouBar" style="background-image:url(<%=image%>);">
    <div id="ThankYouString">Спасибо!</div>
</div>

<div id="AdPics"><%=AdHTML%></div>
