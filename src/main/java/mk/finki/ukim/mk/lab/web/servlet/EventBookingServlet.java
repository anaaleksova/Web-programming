//package mk.finki.ukim.mk.lab.web.servlet;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.finki.ukim.mk.lab.model.EventBooking;
//import mk.finki.ukim.mk.lab.service.EventBookingService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//@WebServlet(name = "EventBookingServlet",urlPatterns = "/eventBooking")
//public class EventBookingServlet extends HttpServlet {
//    private final EventBookingService eventBookingService;
//    private final SpringTemplateEngine springTemplateEngine;
//
//    public EventBookingServlet(EventBookingService eventBookingService, SpringTemplateEngine springTemplateEngine) {
//        this.eventBookingService = eventBookingService;
//
//        this.springTemplateEngine = springTemplateEngine;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(req, resp);
//
//        WebContext context = new WebContext(webExchange);
//
//        String eventName = (String) req.getSession().getAttribute("eventName");
//        EventBooking eventBooking =eventBookingService.getBooking(eventName);
//        context.setVariable("eventBooking", eventBooking);
//
//        springTemplateEngine.process("bookingConfirmation.html", context, resp.getWriter());
//
//    }
//
//}
