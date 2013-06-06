<%@ page import="storedentities.Type" %>
<%@ page import="java.util.Collection" %>
<a href="#" class="btn_checked" id="menuAll" onclick="clickMenu(-1,'menuAll')">Все</a>
<%
    Collection<Type> types= (Collection<Type>) request.getAttribute("types");
    for (Type type : types){
        String elementID = "menu" + type.getId();
%>
<a href="#" class="btn_unchecked" id="<%=elementID%>" onclick="clickMenu(<%=type.getId()%>,<%=elementID%>)"><%=type.getName()%></a>
<%}%>