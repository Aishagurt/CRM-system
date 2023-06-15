package CRM.system.controllers;

import CRM.system.entities.Request;
import CRM.system.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    @GetMapping(value="/")
    public String HomePage(Model model){
        List<Request> reqs = requestRepository.findAll();
        model.addAttribute("reqs", reqs);
        return "home";
    }

    @Autowired
    private RequestRepository requestRepository;


    @GetMapping(value="/addrequest")
    public String AddingRequestPage(){
        return "addrequest";
    }

    @PostMapping(value = "/addrequest")

    public String addRequest(@RequestParam(name = "userName") String userName,

                         @RequestParam(name = "courseName") String courseName,

                         @RequestParam(name = "commentary") String commentary,

                         @RequestParam(name = "phone") String phone

                         ){



        Request req = new Request();

        req.setUserName(userName);

        req.setCourseName(courseName);

        req.setCommentary(commentary);

        req.setPhone(phone);

        req.setHandled(false);

        requestRepository.save(req);

        return "redirect:/";

    }

    @GetMapping(value = "/details/{id}")
    public String RequestDetails(Model model, @PathVariable(name = "id") Long id){
        Request req = requestRepository.findById(id)
                .orElse(null);;
        model.addAttribute("request", req);
        return "details";
    }

    @PostMapping(value = "/saverequest")
    public String SaveRequest(@RequestParam(name = "id") Long id,

                          @RequestParam(name = "userName") String name,

                          @RequestParam(name = "courseName") String course,

                          @RequestParam(name = "commentary") String commentary,

                          @RequestParam(name = "phone") String phone){



        Request req = requestRepository.findById(id).orElse(null);

        if(req!=null) {

            req.setUserName(name);

            req.setCourseName(course);

            req.setCommentary(commentary);

            req.setPhone(phone);

            requestRepository.save(req);

            return "redirect:/details/"+id;

        }

        return "redirect:/";

    }
    @PostMapping(value = "/deleterequest")

    public String DeleteRequest(@RequestParam(name = "id") Long id){

        requestRepository.deleteById(id);

        return "redirect:/";

    }

    @PostMapping(value ="/handlerequest")

    public String HandleRequest(@RequestParam(name = "id") Long id){

        Request req = requestRepository.findById(id).orElse(null);
        if(req != null){
            req.setHandled(true);

            requestRepository.save(req);

            return "redirect:/details/"+id;
        }
        return "home";
    }

    @GetMapping(value = "/newrequests")

    public String NewRequests(Model model){
        List<Request> reqs = requestRepository.findAll();
        List<Request> newRequests = reqs.stream()
                .filter(req -> !req.isHandled())
                .collect(Collectors.toList());

        model.addAttribute("reqs", newRequests);
        return "home";
    }

    @GetMapping(value = "/handledrequests")

    public String HandledRequests(Model model){
        List<Request> reqs = requestRepository.findAll();
        List<Request> HandledRequests = reqs.stream()
                .filter(req -> req.isHandled())
                .collect(Collectors.toList());

        model.addAttribute("reqs", HandledRequests);
        return "home";
    }
}
