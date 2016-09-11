<div class="border idea_small">
    <!-- Header with the title, date, author, up, down and remove buttons -->
    <div id="header">
	<div style="float:right;">
	    <c:out value="(${fn:length(idea.votedUp)})"/>
	    <s:link beanclass="${beanPackage}.idea.VoteActionBean" event="voteUp">
		<img alt="Vote Up" title="Vote Up" src="<c:url value="/css/icons/vote_up.png" />" />
		<s:param name="ideaId" value="${idea.id}"/>
	    </s:link>
	    
	    <c:out value="(${fn:length(idea.votedDown)})"/>
	    <s:link beanclass="${beanPackage}.idea.VoteActionBean" event="voteDown">
		<img alt="Vote Down" title="Vote Down" src="<c:url value="/css/icons/vote_down.png" />" />
		<s:param name="ideaId" value="${idea.id}"/>
	    </s:link>
	    
            <c:if test="${(actionBean.currentUser eq idea.project.owner) or (actionBean.currentUser.class == 'class fr.ensimag.ack.eggsale.db.entity.Administrator')}">
                <c:choose>
                    <c:when test="${empty idea.reward}">
                        <s:link beanclass="${beanPackage}.reward.CreateActionBean">
                            <img alt="Reward" title="Reward" src="<c:url value="/css/icons/gold_medal_add.png" />" />
                            <s:param name="ideaId" value="${idea.id}" />
                        </s:link>
                    </c:when>
                    <c:otherwise>
                        <s:link beanclass="${beanPackage}.reward.RemoveActionBean">
                            <img alt="Unreward" title="Unreward" src="<c:url value="/css/icons/gold_medal_delete.png" />" />
                            <s:param name="ideaId" value="${idea.id}" />
                        </s:link>
                    </c:otherwise>
                </c:choose>
            </c:if>
                                    
	    <c:if test="${(actionBean.currentUser eq idea.owner) or (actionBean.currentUser eq actionBean.project.owner) or (actionBean.currentUser.class == 'class fr.ensimag.ack.eggsale.db.entity.Administrator')}">
	    <s:link beanclass="${beanPackage}.idea.UpdateActionBean">
		<img alt="Edit" title="Edit" src="<c:url value="/css/icons/edit.png" />" />
		<s:param name="ideaId" value="${idea.id}" />
	    </s:link>
            <s:link beanclass="${beanPackage}.idea.RemoveActionBean">
		<img alt="Remove" title="Remove" src="<c:url value="/css/icons/trash.png" />" />
		<s:param name="ideaId" value="${idea.id}" />
	    </s:link>
	    </c:if>
	</div>
	
	<h3>
	    <s:link beanclass="${beanPackage}.idea.ShowActionBean">
		<c:out value="${idea.title}" />
		<s:param name="ideaId" value="${idea.id}" />
	    </s:link>
	</h3>
	
	<h4>
	    Submitted by
	    <s:link beanclass="${beanPackage}.user.ShowActionBean">
                <c:out value="${idea.owner.userName}" />
		<s:param name="userId" value="${idea.owner.id}"/>
	    </s:link>
	    on <fmt:formatDate type="both" dateStyle="medium" timeStyle="short" value="${idea.createdAt}" />
	</h4>
    </div>
    <p>${idea.content}</p>
    <p style="margin-bottom: 0">
	<s:link beanclass="${beanPackage}.idea.ShowActionBean">
	    ${idea.comments.size()} comment(s)
	    <s:param name="ideaId" value="${idea.id}" />
	</s:link>
	 - 
	<c:choose>
	    <c:when test="${not empty actionBean.currentUser}">
		<s:link beanclass="${beanPackage}.comment.CreateActionBean">
		    Add a comment
		    <s:param name="ideaId" value="${idea.id}" />
		</s:link>
	    </c:when>
	    <c:otherwise>
		<p>Please <s:link beanclass="${beanPackage}.user.LoginActionBean">log in</s:link> to add a new comment</p>
	    </c:otherwise>
	</c:choose>
    </p>
</div>