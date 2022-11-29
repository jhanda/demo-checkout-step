package com.liferay.commerce.demo.checkout.step;

import com.liferay.commerce.constants.CommerceCheckoutWebKeys;
import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.commerce.util.BaseCommerceCheckoutStep;
import com.liferay.commerce.util.CommerceCheckoutStep;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Jeff Handa
 */
@Component(
        immediate = true,
        property = {
                "commerce.checkout.step.name=" + DemoCheckoutStep.NAME,
                "commerce.checkout.step.order:Integer=22"
        },
        service = CommerceCheckoutStep.class
)
public class DemoCheckoutStep extends BaseCommerceCheckoutStep {

    public static final String NAME = "demo-checkout-step";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean isActive(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        /*
         * Add logic to determine if this step is going to be used during this checkout.  Some examples might be to
         * include a custom checkout step based on the type of Order or type of Items in the order.  Another common
         * use case is to limit custom checkout steps to a specific Channel.
         */
        return true;
    }
    @Override
    public void processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

        CommerceOrder commerceOrder = (CommerceOrder)actionRequest.getAttribute(CommerceCheckoutWebKeys.COMMERCE_ORDER);

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date requestedDeliveryDate = ParamUtil.getDate(actionRequest, "requestedDeliveryDate", df);

        commerceOrder.setRequestedDeliveryDate(requestedDeliveryDate);
        _commerceOrderLocalService.updateCommerceOrder(commerceOrder);

    }

    @Override
    public void render(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        CommerceContext commerceContext = (CommerceContext)httpServletRequest.getAttribute(CommerceWebKeys.COMMERCE_CONTEXT);
        CommerceOrder commerceOrder = commerceContext.getCommerceOrder();
        Date requestedDeliveryDate = commerceOrder.getRequestedDeliveryDate();
        httpServletRequest.setAttribute("requestedDeliveryDate", requestedDeliveryDate);
        _jspRenderer.renderJSP(
                _servletContext, httpServletRequest, httpServletResponse,
                "/demo_checkout_step_form.jsp");
    }

    @Reference
    private JSPRenderer _jspRenderer;

    @Reference(
            target = "(osgi.web.symbolicname=com.liferay.commerce.demo.checkout.step)"
    )
    private ServletContext _servletContext;

    @Reference
    private CommerceOrderLocalService _commerceOrderLocalService;
}