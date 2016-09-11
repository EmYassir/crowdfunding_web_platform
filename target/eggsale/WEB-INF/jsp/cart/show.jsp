<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="stripes"
           uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<s:layout-render name="/WEB-INF/jsp/common/layout_main.jsp" title="Your Cart">
    <s:layout-component name="show_cart">
        <br>
        <tr>
            <% int i = 0; %>
            <c:forEach items="${actionBean.projects}" var="project">
                <s:form
                    beanclass="${beanPackage}.cart.RemoveFromCartActionBean"
                    focus="">
                    <s:errors />
                    <% i=i+1; %>
                <li>  Product name: ${project.name} </li>
                <li>  Quantity: ${project.quantity} 
                    <s:param name="projectId" value="${project.id}"/>
                    <s:submit name="remove" value="Remove!" />
                </li>
                <br>
                <br>
            </s:form>
        </c:forEach> 
    </tr>
    <% if(i>0) {%>
    <tr>
        <td>Price: </td>
        <td>${actionBean.amount}</td>
    </tr>
    <tr>
        <s:link beanclass="${beanPackage}.cart.PayActionBean"> 
            Buy
        </s:link> 
    </tr>
    <%}%>
</s:layout-component>
</s:layout-render>
