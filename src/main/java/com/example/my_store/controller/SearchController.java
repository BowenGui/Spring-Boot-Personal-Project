package com.example.my_store.controller;
import com.example.my_store.model.BusinessService;
import com.example.my_store.model.SearchResult;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;


@Controller
public class SearchController {
    @Autowired //again spring-boot creates an instance of BusinessService
    private BusinessService businessService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    /*@RequestMapping("/search") //default method is everything
    public String search() {
        return "search"; //html
    } */

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    //returning html here as a string; the easiest but not the cleanest way to do mapping.
    /*@RequestMapping("/result")
    public String stringSearch(@RequestParam(value = "description") String searchInput, model model) {
        //business logic here
        model.addAttribute("searchQuery", searchInput); //just to show user what he searched for
        model.addAttribute("searchResults", businessService.searchAllStores(searchInput));
        return "result"; //html
    } */

    //using ModelAndView for mapping the html page; remember to return the model
    /*@RequestMapping("/result")
    public ModelAndView modelAndViewSearch(ModelAndView modelAndView, @RequestParam(value = "description", defaultValue = "hello", required = false) String searchInput) {
        modelAndView.addObject("searchQuery", searchInput);
        modelAndView.addObject("searchResults", businessService.searchAllStores(searchInput));
        modelAndView.setViewName("result"); //this is also html
        return modelAndView;
    }*/

    @RequestMapping("/search")
    /*TODO: note: use addFlashAttribute for showing the information, NOT addAttributes */
    public ModelAndView redirectSearch(ModelAndView modelAndView,
                                       @RequestParam(value = "description", required = false) String searchInput,
                                       RedirectAttributes redirectAttributes, HttpSession httpSession) {

        if(searchInput == null) { //first time visiting the search-page (no descriptions obviously)
            modelAndView.setViewName("search");
            return modelAndView;
        }

        if(searchInput.equals("")) { //if search-term is empty
            modelAndView.addObject("error_message", "Search was empty");
            modelAndView.setViewName("search");
            return modelAndView;
        }

        ArrayList<SearchResult> itemsFromStore = businessService.searchByDescription(searchInput);
        httpSession.setAttribute("searchQuery", searchInput);
        httpSession.setAttribute("searchResults", itemsFromStore);

        modelAndView.setViewName("redirect:/result"); //redirects page to this url, which eventually returns an html in some controller class
        return modelAndView;
    }

    @RequestMapping("/result") //redirected from redirectSearch(above)
    public ModelAndView displayResult(@RequestParam(value = "store", required = false) String store,
                               @RequestParam(value = "next", required = false) String nextPage,
                               @RequestParam(value = "previous", required = false) String previousPage,
                               HttpSession httpSession, ModelAndView modelAndView) {

        String searchQuery = (String) httpSession.getAttribute("searchQuery");
        ArrayList<SearchResult> searchResults = (ArrayList<SearchResult>) httpSession.getAttribute("searchResults");

        if(searchQuery == null) {
            modelAndView.setViewName("redirect:/search");
            return modelAndView;
        }

        if(nextPage != null) {
            SearchResult searchResult1 = searchResults.stream().filter(s -> s.getStoreName().equals(store)).findFirst().get();
            searchResult1.nextPage();
        }

        if(previousPage != null) {
            SearchResult searchResult1 = searchResults.stream().filter(s -> s.getStoreName().equals(store)).findFirst().get();
            searchResult1.previousPage();
        }

        modelAndView.addObject("searchQuery", searchQuery);
        modelAndView.addObject("searchResults", searchResults);
        modelAndView.setViewName("result"); //html
        return modelAndView;
    }
}
