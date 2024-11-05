package com.kamegatze.learnjvm.controllers.home.articles;

import com.kamegatze.learnjvm.configuration.props.app.AppNamesProps;
import com.kamegatze.learnjvm.configuration.security.details.UserDetails;
import com.kamegatze.learnjvm.model.articles.Article;
import com.kamegatze.learnjvm.model.db.users.Users;
import com.kamegatze.learnjvm.model.filtering.Filter;
import com.kamegatze.learnjvm.model.generation.url.Parameters;
import com.kamegatze.learnjvm.repository.articles.posts.PostsRepository;
import com.kamegatze.learnjvm.servicies.articles.ArticlesService;
import com.kamegatze.learnjvm.utils.Filtering;
import com.kamegatze.learnjvm.utils.GenerationUrlPage;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/articles")
public class ArticlesController {
    private final ArticlesService articlesService;
    private final AppNamesProps appNamesProps;
    private final GenerationUrlPage generationUrlPage;
    private final Filtering filtering;
    private final PostsRepository postsRepository;

    public ArticlesController(ArticlesService articlesService, AppNamesProps appNamesProps, GenerationUrlPage generationUrlPage, Filtering filtering, PostsRepository postsRepository) {
        this.articlesService = articlesService;
        this.appNamesProps = appNamesProps;
        this.generationUrlPage = generationUrlPage;
        this.filtering = filtering;
        this.postsRepository = postsRepository;
    }

    @GetMapping("/create")
    String handlingCreateArticleView(Model model) {
        model.addAttribute("article", new Article("", ""));
        return "/articles/create";
    }

    @PostMapping("/create")
    String handlingCreateArticle(@ModelAttribute("article") @Valid Article article, Authentication authentication) {
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        article.setUsers(userDetails.getUser());
        articlesService.save(article);
        return "redirect:/articles/all-articles-by-user";
    }

    @GetMapping("/view/{id}")
    String handlingGetById(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("article", articlesService.getById(id));
        return "articles/view";
    }

    @GetMapping("/all-articles-by-user")
    String handlingGetAllArticlesByUser(Model model,
                                        Authentication authentication,
                                        @RequestParam(value = "#{appNamesProps.searchFieldName}", required = false) String searchValue,
                                        Pageable pageable) {
        final Users user = ((UserDetails)authentication.getPrincipal()).getUser();
        final Page<Article> articlesByUser = Objects.isNull(searchValue) || searchValue.isEmpty() || searchValue.isBlank() ?
                articlesService.findAllByUserPageable(user, pageable):
                articlesService.findAllByArticlesAndUserPageable(user, searchValue, pageable);
        final Parameters parameters = Parameters.build()
                        .url("/articles/all-articles-by-user")
                        .countPage(articlesByUser.getTotalPages())
                        .pageSize(pageable.getPageSize())
                        .sort(pageable.getSort())
                        .queryStaticParams(new String[]{appNamesProps.getSearchFieldName(), searchValue});

        List<Filter> filters = filtering.processing(articlesByUser.getPageable().getSort(), List.of("published"));

        model.addAttribute("articles", articlesByUser);
        model.addAttribute("searchName", appNamesProps.getSearchFieldName());
        model.addAttribute("searchValue", searchValue);
        model.addAttribute("sizeName", appNamesProps.getSizeName());
        model.addAttribute("sortName", appNamesProps.getSortName());
        model.addAttribute("pageName", appNamesProps.getPageNumberName());
        model.addAttribute("urls", generationUrlPage.generation(parameters));
        model.addAttribute("filters", filters);
        model.addAttribute("urlSearch", "/articles/search-by-name");
        return "articles/all-articles-by-user";
    }

    @GetMapping("/edit/{id}")
    String handlingEditArticle(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("article", articlesService.getByIdWithMarkDown(id));
        return "/articles/edit";
    }

    @PostMapping("/edit/{id}")
    String handlingEdit(@ModelAttribute("article") @Valid Article article, @PathVariable("id") UUID id) {
        article.setId(id);
        articlesService.update(article);
        return "redirect:/articles/all-articles-by-user?page=0&size=5";
    }

    @PostMapping("/published/{id}")
    ResponseEntity<Void> handlingPublishedArticle(@PathVariable("id") UUID id, Boolean published) {
        articlesService.updatePublished(published, id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/delete/{id}")
    String handlingDeleteArticle(@PathVariable("id") UUID id) {
        articlesService.delete(id);
        return "redirect:/articles/all-articles-by-user?page=0&size=5";
    }

    @GetMapping("/create/upload")
    String handlingCreateUpload() {
        return "articles/upload-article";
    }

    @PostMapping("/create/upload")
    String handlingCreateUploadArticle(@RequestParam("file") MultipartFile file, @RequestParam("label") String label,
                                       Authentication authentication) {
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final Users users = userDetails.getUser();
        articlesService.save(file, label, users);
        return "redirect:/articles/all-articles-by-user?page=0&size=5";
    }

    @GetMapping("/search-by-name")
    String handlingSearchByName(@RequestParam(value = "#{appNamesProps.searchFieldName}", required = true) String searchValue, Pageable pageable) {
        String sort = pageable.getSort().stream().map(it -> String.format("sort=%s,%s", it.getProperty(), it.getDirection().name().toLowerCase()))
                .collect(Collectors.joining("&"));
        sort = sort.isEmpty() ? "" : String.format("&%s", sort);
        return String.format("redirect:/articles/all-articles-by-user?page=%s&size=%s&%s=%s%s", pageable.getPageNumber(), pageable.getPageSize(),
                appNamesProps.getSearchFieldName(), searchValue, sort);
    }
}
