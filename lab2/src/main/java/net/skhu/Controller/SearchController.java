package net.skhu.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.skhu.model.Pagination;
import net.skhu.service.SearchService;

@Controller
public class SearchController {

	@Autowired SearchService searchService;


    @RequestMapping("search")
    public String list2(Pagination pagination, Model model) {

        model.addAttribute("students", searchService.findAll(pagination));
        model.addAttribute("orderBy", searchService.getOrderByOptions());
        model.addAttribute("category", searchService.getCategoryOptions());
        return "admin/search";
    }
}
