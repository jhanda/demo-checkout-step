<%@ page import="com.liferay.commerce.model.CommerceOrder" %>
<%@ page import="com.liferay.commerce.model.CommerceOrderItem" %>
<%@ page import="com.liferay.commerce.model.CommerceAddress" %>
<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="com.liferay.commerce.product.model.CPDefinition" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.liferay.portal.kernel.util.CalendarFactoryUtil" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://liferay.com/tld/commerce-ui" prefix="commerce-ui" %>
<%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
    Date requestedDeliveryDate = (Date)request.getAttribute("requestedDeliveryDate");

    int requestedDeliveryDay = 0;
    int requestedDeliveryMonth = -1;
    int requestedDeliveryYear = 0;

    if (requestedDeliveryDate != null) {
        Calendar calendar = CalendarFactoryUtil.getCalendar(requestedDeliveryDate.getTime());
        requestedDeliveryDay = calendar.get(Calendar.DAY_OF_MONTH);
        requestedDeliveryMonth = calendar.get(Calendar.MONTH);
        requestedDeliveryYear = calendar.get(Calendar.YEAR);
    }

%>

<portlet:actionURL name="saveRequestedDeliveryDate" var="saveRequestedDeliveryDateActionURL"/>

<aui:form action="<%= saveRequestedDeliveryDateActionURL %>" data-senna-off="true" method="post" name="fm">

    <aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

                <div class="form-group input-select-wrapper">

                    <liferay-ui:input-date
                            dayParam="requestedDeliveryDateDay"
                            dayValue="<%= requestedDeliveryDay %>"
                            disabled="<%= false %>"
                            monthParam="requestedDeliveryDateMonth"
                            monthValue="<%= requestedDeliveryMonth %>"
                            name="requestedDeliveryDate"
                            nullable="<%= true %>"
                            showDisableCheckbox="<%= false %>"
                            yearParam="requestedDeliveryDateYear"
                            yearValue="<%= requestedDeliveryYear %>"
                    />
                </div>

</aui:form>

