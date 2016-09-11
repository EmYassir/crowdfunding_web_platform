
<div class="vignette bordershadow">
    <div id="header">
	<h3>
	    <s:link beanclass="${beanPackage}.project.ShowActionBean">
		<c:out value="${project.name}" />
		<s:param name="id" value="${project.id}"/>
	    </s:link>
	</h3>
	<h4>by 
	    <s:link beanclass="${beanPackage}.user.ShowActionBean">
		<c:out value="${project.owner.userName}" />
		<s:param name="userId" value="${project.owner.id}"/>
	    </s:link>
	</h4>
    </div>
    <div class="body">
	<div class="imagesmall">        
	    <s:link beanclass="${beanPackage}.project.ShowActionBean">
		<c:choose>
		    <c:when test="${not empty project.firstImage}">
			<img src="data:image/jpeg;base64,${project.firstImage}"/>
		    </c:when>
		    <c:otherwise>
			<img src="${contextPath}/css/no_image.png"/>
		    </c:otherwise>
		</c:choose>
		<s:param name="id" value="${project.id}"/>
	    </s:link>
	</div>

	<div class="descr">
	    <c:out value="${project.smallDescription}" />
	</div>
    </div>
    <div class="more">
    	<strong>$</strong> <c:out value="${project.price}" />
	&nbsp;&nbsp;&nbsp;<i class="icon-comment"></i> <c:out value="${project.activityCounter}" />
    </div>
</div>