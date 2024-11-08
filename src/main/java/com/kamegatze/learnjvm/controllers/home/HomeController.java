package com.kamegatze.learnjvm.controllers.home;

import com.kamegatze.learnjvm.configuration.props.app.AppNamesProps;
import com.kamegatze.learnjvm.configuration.security.details.UserDetails;
import com.kamegatze.learnjvm.model.articles.Article;
import com.kamegatze.learnjvm.model.db.users.Users;
import com.kamegatze.learnjvm.model.filtering.Filter;
import com.kamegatze.learnjvm.model.generation.url.Parameters;
import com.kamegatze.learnjvm.servicies.articles.ArticlesService;
import com.kamegatze.learnjvm.utils.Filtering;
import com.kamegatze.learnjvm.utils.GenerationUrlPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/main")
public class HomeController {

    private final ArticlesService articlesService;
    private final Filtering filtering;
    private final AppNamesProps appNamesProps;
    private final GenerationUrlPage generationUrlPage;

    public HomeController(ArticlesService articlesService,
                          Filtering filtering,
                          AppNamesProps appNamesProps,
                          GenerationUrlPage generationUrlPage) {
        this.articlesService = articlesService;
        this.filtering = filtering;
        this.appNamesProps = appNamesProps;
        this.generationUrlPage = generationUrlPage;
    }

    @GetMapping("/home")
    String handlingHomePage(Authentication authentication, Model model) {
        if (Objects.isNull(authentication)) {
            model.addAttribute("user", null);
        } else {
            final Users user = ((UserDetails)authentication.getPrincipal()).getUser();
            model.addAttribute("user", user);
        }
        return "home";
    }

    @GetMapping("/articles")
    String handlingArticles(Model model, Pageable pageable, @RequestParam(value = "#{appNamesProps.searchFieldName}", required = false) String searchValue) {

        Page<Article> articlesViaPageable = Objects.isNull(searchValue) || searchValue.isEmpty() || searchValue.isBlank() ?
                articlesService.findAllByPublished(true, pageable) : articlesService.findAllByArticleAndPublished(pageable, searchValue, true);

        articlesViaPageable = new PageImpl<>(articlesViaPageable.filter(Article::getPublished).toList(), articlesViaPageable.getPageable(), articlesViaPageable.getTotalElements());

        List<Filter> filters = filtering.processing(articlesViaPageable.getPageable().getSort(), List.of("published"));

        final Parameters parameters = Parameters.build()
                .url("/main/articles")
                .countPage(articlesViaPageable.getTotalPages())
                .pageSize(pageable.getPageSize())
                .sort(pageable.getSort())
                .queryStaticParams(new String[]{appNamesProps.getSearchFieldName(), searchValue});

        model.addAttribute("articles", articlesViaPageable);
        model.addAttribute("filters", filters);
        model.addAttribute("sizeName", appNamesProps.getSizeName());
        model.addAttribute("sortName", appNamesProps.getSortName());
        model.addAttribute("pageName", appNamesProps.getPageNumberName());
        model.addAttribute("searchName", appNamesProps.getSearchFieldName());
        model.addAttribute("searchValue", searchValue);
        model.addAttribute("urls", generationUrlPage.generation(parameters));
        model.addAttribute("urlSearch", "/main/articles/search-by-name");
        return "articles";
    }

    @GetMapping("/articles/search-by-name")
    String handlingArticlesByName(@RequestParam(value = "#{appNamesProps.searchFieldName}", required = true) String searchValue, Pageable pageable) {
        String sort = pageable.getSort().stream().map(it -> String.format("sort=%s,%s", it.getProperty(), it.getDirection().name().toLowerCase()))
                .collect(Collectors.joining("&"));
        sort = sort.isEmpty() ? "" : String.format("&%s", sort);
        return String.format("redirect:/main/articles?page=%s&size=%s&%s=%s%s", pageable.getPageNumber(), pageable.getPageSize(),
                appNamesProps.getSearchFieldName(), searchValue, sort);
    }


    @GetMapping("/courses")
    String handlingCourses() {return "courses";}

}
