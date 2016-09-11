<%-- 
    Document   : paypal_cancel_return.jsp
    Created on : 16 déc. 2012, 21:09:26
    Author     : yassir
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="stripes"
           uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<s:layout-render name="/WEB-INF/jsp/common/layout_main.jsp" title="Transaction canceled">
    
    <s:layout-component name="cancel_return">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Transaction canceled!</title>
    </head>
    <body>               
       <s:link beanclass="${beanPackage}.user.ShowActionBean"> 
            Home Page
        </s:link> 
    </body>
</html>

     
</s:layout-component>
</s:layout-render>
