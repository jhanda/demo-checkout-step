package com.liferay.commerce.demo.checkout.step;

import com.liferay.commerce.util.BaseCommerceCheckoutStep;
import com.liferay.commerce.util.CommerceCheckoutStep;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public void processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

    }

    @Override
    public void render(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

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
}