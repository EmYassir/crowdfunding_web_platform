<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="stripes"
	uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<s:layout-render name="/WEB-INF/jsp/common/layout_main.jsp" title="">
    <s:layout-component name="body">
            
	<div class="bordershadow">
	    <div id="header">
		<div class="floatright">
		    <c:out value="(${fn:length(actionBean.idea.votedUp)})"/>
		    <s:link beanclass="${beanPackage}.idea.VoteActionBean" event="voteUp">
			<img alt="Vote Up" title="Vote Up" src="<c:url value="/css/icons/vote_up.png" />" />
			<s:param name="ideaId" value="${actionBean.idea.id}"/>
		    </s:link>

		    <c:out value="(${fn:length(actionBean.idea.votedDown)})"/>
		    <s:link beanclass="${beanPackage}.idea.VoteActionBean" event="voteDown">
			<img alt="Vote Down" title="Vote Down" src="<c:url value="/css/icons/vote_down.png" />" />
			<s:param name="ideaId" value="${actionBean.idea.id}"/>
		    </s:link>
		    <c:if test="${(actionBean.currentUser eq actionBean.idea.project.owner) or (actionBean.currentUser.class == 'class fr.ensimag.ack.eggsale.db.entity.Administrator')}">
			<c:choose>
			    <c:when test="${empty actionBean.idea.reward}">
				<s:link beanclass="${beanPackage}.reward.CreateActionBean">
				    <img alt="Reward" title="Reward" src="<c:url value="/css/icons/gold_medal_add.png" />" />
				    <s:param name="ideaId" value="${actionBean.idea.id}" />
				</s:link>
			    </c:when>
			    <c:otherwise>
				<s:link beanclass="${beanPackage}.reward.RemoveActionBean">
				    <img alt="Unreward" title="Unreward" src="<c:url value="/css/icons/gold_medal_delete.png" />" />
				    <s:param name="ideaId" value="${actionBean.idea.id}" />
				</s:link>
			    </c:otherwise>
			</c:choose>
		    </c:if>
		    <c:if test="${(actionBean.currentUser eq actionBean.idea.owner) or (actionBean.currentUser eq actionBean.idea.project.owner) or (actionBean.currentUser.class == 'class fr.ensimag.ack.eggsale.db.entity.Administrator')}">
			<s:link beanclass="${beanPackage}.idea.UpdateActionBean">
			    <img alt="Edit" title="Edit" src="<c:url value="/css/icons/edit.png" />" />
			    <s:param name="ideaId" value="${actionBean.idea.id}" />
			</s:link>
			<s:link beanclass="${beanPackage}.idea.RemoveActionBean">
			    <img alt="Remove" title="Remove" src="<c:url value="/css/icons/trash.png" />" />
			    <s:param name="ideaId" value="${actionBean.idea.id}" />
			</s:link>
		    </c:if>
		</div>
	
		<h2>
		    <s:link beanclass="${beanPackage}.idea.ShowActionBean">
			<c:out value="${actionBean.idea.title}" />
			<s:param name="ideaId" value="${actionBean.idea.id}" />
		    </s:link>
		</h2>

		<h4 style="font-size: 14px;">
		    idea for <s:link beanclass="${beanPackage}.project.ShowActionBean">
			<c:out value="${actionBean.idea.project.name}" />
			<s:param name="id" value="${actionBean.idea.project.id}" />
		    </s:link>
		    submitted by  
		    <s:link beanclass="${beanPackage}.user.ShowActionBean">
			<c:out value="${actionBean.idea.owner.userName}" />
			<s:param name="userId" value="${actionBean.idea.owner.id}"/>
		    </s:link>
		    on
		    <fmt:formatDate type="both" dateStyle="medium" timeStyle="short" value="${actionBean.idea.createdAt}" />
		    
		</h4>
	    </div>
                
            <p><c:out value="${actionBean.idea.content}" /></p>
                        
            <c:if test="${not empty actionBean.idea.reward}">
                <p>
                    <img style="display: inline-block; vertical-align: middle" alt="Reward_icon" src="<c:url value="/css/icons/reward.png" />" />
                    <b style="display: inline-block; vertical-align: middle"><c:out value="This idea have been rewarded ${actionBean.idea.reward.amount}$ by ${actionBean.idea.project.owner.userName} !" /></b>
                </p>
            </c:if>
            
	    <h4>Attachments</h4>
            <c:if test="${empty actionBean.idea.attachments}">
		<p>There is no attachment for the moment.</p>
	    </c:if>
	    <c:forEach items="${actionBean.idea.attachments}" var="attach">
		<s:link beanclass="${beanPackage}.attachment.DownloadActionBean">
		    <div class="attachment">
			<div class="attachmentVignette" ></div>
			<c:out value="${attach.fileName}" />
			<s:param name="attachmentId" value="${attach.id}" />
		    </div>
		</s:link>
	    </c:forEach>
	</div>

	<div class="bordershadow">
	    <h2>Comments</h2>
	    <c:choose>
		<c:when test="${not empty actionBean.currentUser}">
	    	<p>
		    <s:link beanclass="${beanPackage}.comment.CreateActionBean" class="btn">
			<s:param name="ideaId" value="${actionBean.ideaId}" />
			Add Comment
		    </s:link>
		    </p>
		</c:when>
		<c:otherwise>
		    <p>Please <s:link beanclass="${beanPackage}.user.LoginActionBean">log in</s:link> to add a new comment</p>
		</c:otherwise>
	    </c:choose>

	    <c:forEach var="comment" items="${actionBean.idea.comments}">
		<%@include file="/WEB-INF/jsp/comment/show.jsp" %>
	    </c:forEach>
	</div>
                
    </s:layout-component>
</s:layout-render>
