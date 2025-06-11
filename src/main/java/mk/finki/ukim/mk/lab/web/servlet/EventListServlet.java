//package mk.finki.ukim.mk.lab.web.servlet;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.finki.ukim.mk.lab.model.Event;
//import mk.finki.ukim.mk.lab.service.EventBookingService;
//import mk.finki.ukim.mk.lab.service.EventService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Objects;
//
//@WebServlet(name = "EventListServlet")
//public class EventListServlet extends HttpServlet {
//    private final EventService eventService;
//    private final EventBookingService eventBookingService;
//    private final SpringTemplateEngine springTemplateEngine;
//
//    public EventListServlet(EventService eventService, EventBookingService eventBookingService, SpringTemplateEngine springTemplateEngine) {
//        this.eventService = eventService;
//        this.eventBookingService = eventBookingService;
//        this.springTemplateEngine = springTemplateEngine;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(req, resp);
//        List<Event> eventList;
//        String searchName = req.getParameter("searchName");
//        String minRating =req.getParameter("minRating");
//
//        if (searchName != null && minRating != null && !Objects.equals(minRating, "")) {
//            eventList = eventService.searchEventsByNameAndRating(searchName,Double.parseDouble(minRating));
//        } else if (minRating != null && !Objects.equals(minRating, "")) {
//            eventList = eventService.searchEventsByRating(Double.parseDouble(minRating));
//        } else if (searchName != null) {
//            eventList = eventService.searchEvents(searchName);
//        } else {
//            eventList = eventService.listAll();
//        }
//        WebContext context = new WebContext(webExchange);
//
//        context.setVariable("eventList",eventList);
//
//        springTemplateEngine.process("listEvents.html", context, resp.getWriter());
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         String eventName = req.getParameter("eventname");
//         String attendeeName = req.getRemoteUser();
//         String attendeeAddress = req.getRemoteAddr();
//         String numberOfTickets=req.getParameter("numTickets");
//        req.getSession().setAttribute("eventName", eventName);
//         eventBookingService.placeBooking(eventName,attendeeName,attendeeAddress,Long.parseLong(numberOfTickets));
//         resp.sendRedirect("/eventBooking");
//    }
//}
