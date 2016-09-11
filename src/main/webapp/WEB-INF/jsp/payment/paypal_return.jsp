<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="stripes"
           uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<s:layout-render name="/WEB-INF/jsp/common/layout_main.jsp" title="Transaction succeeded">
<s:layout-component name="body">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Transaction succeeded!</title>
    </head>
    <body>
        <p>Thank you for buying a product with eggsale.</p>
        <s:link beanclass="${beanPackage}.project.ListActionBean"> 
            Let's discover other amazing projects!
        </s:link> 
    </body>
</html>


</s:layout-component>
</s:layout-render>