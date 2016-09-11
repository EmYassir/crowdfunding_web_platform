<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="stripes"
           uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<s:layout-render name="/WEB-INF/jsp/common/layout_main.jsp" title="Your Cart">
    <s:layout-component name="body">
        <c:if test="${empty actionBean.products}">
	    <p>It seems that you did not pick a project for the moment...
		<s:link beanclass="${beanPackage}.project.ListActionBean">Let's choose one !</s:link></p>
	</c:if>
        
        <c:forEach items="${actionBean.products}" var="project">
            <div class="vignette bordershadow">
                <div class="header">
                    <div style="float:right;">#${project.key.id}</div>

                    <h3>
                        <s:link beanclass="${beanPackage}.project.ShowActionBean" >
                            <c:out value="${project.key.name}" />
                            <s:param name="id" value="${project.key.id}" />
                        </s:link>
                    </h3>
                    <h4>by 
                        <s:link beanclass="${beanPackage}.user.ShowActionBean">
                            <c:out value="${project.key.owner.userName}" />
                            <s:param name="userId" value="${project.key.owner.id}"/>
                        </s:link>
                    </h4>
                </div>

                <div class="imagesmall">        
                    <s:link beanclass="${beanPackage}.project.ShowActionBean">
                        <c:choose>
                            <c:when test="${not empty project.key.firstImage}">
                                <img src="data:image/jpeg;base64,${project.key.firstImage}"/>
                            </c:when>
                            <c:otherwise>
                                <img src="${contextPath}/css/no_image.png"/>
                            </c:otherwise>
                        </c:choose>
                        <s:param name="id" value="${project.key.id}"/>
                    </s:link>
                </div>

                <div class="descr">
                    <b>Quantity:</b> ${project.value }<br>
                    <b>Total Price:</b> $${ project.key.price * project.value}
                </div>
                
                <s:form beanclass="${beanPackage}.cart.RemoveFromCartActionBean" focus="">
                    <s:errors />
                    <s:param name="projectId" value="${project.key.id}" />
                    <s:submit name="remove" value="Remove!" class="btn" />
                    <s:submit name="removeAll" value="Remove All!" class="btn" />
                </s:form>
            </div>
        </c:forEach>
    <br />
    
    <c:if test="${not empty actionBean.products}">
        
        <h4>
            Flush your cart: 
            <s:link beanclass="${beanPackage}.cart.RemoveFromCartActionBean" event="flush" class="btn btn-danger">
                Flush
            </s:link>
        </h4>
        
        <h3>
            <s:form action="paiementPayPal"  method="POST" style="margin-left: 0px">
            Total price: $${actionBean.currentCart.amount }
            
                <input name="amount" type='hidden' value="${actionBean.currentCart.amount}"/>
                <input type="image" 
                       src="https://www.paypal.com/en_US/i/btn/btn_buynow_LG.gif" 
                       name="submit" 
                       alt="Make payments with PayPal - it's fast, free and secure!"
                       style="border: none" />
            </s:form>
            
        </h3>
            
        
        
            
    </c:if>
</s:layout-component>
</s:layout-render>
