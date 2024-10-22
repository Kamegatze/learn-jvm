package com.kamegatze.learnjvm.controllers.home.articles;

import com.kamegatze.learnjvm.configuration.props.app.AppNamesProps;
import com.kamegatze.learnjvm.configuration.security.details.UsersDetails;
import com.kamegatze.learnjvm.model.articles.Article;
import com.kamegatze.learnjvm.model.db.users.Users;
import com.kamegatze.learnjvm.servicies.articles.ArticlesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/articles")
public class ArticlesController {
    private final ArticlesService articlesService;
    private final AppNamesProps appNamesProps;

    public ArticlesController(ArticlesService articlesService, AppNamesProps appNamesProps) {
        this.articlesService = articlesService;
        this.appNamesProps = appNamesProps;
    }

    @GetMapping("/create")
    String handlingCreateArticleView(Model model) {
        model.addAttribute("article", new Article("", ""));
        return "/articles/create";
    }

    @PostMapping("/create")
    String handlingCreateArticle(@ModelAttribute("article")Article article, Authentication authentication) {
        final UsersDetails userDetails = (UsersDetails) authentication.getPrincipal();
        article.setUserId(userDetails.getUser().getId());
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
        final Users user = ((UsersDetails)authentication.getPrincipal()).getUser();
        final Page<Article> articlesByUser = Objects.isNull(searchValue) || searchValue.isEmpty() || searchValue.isBlank() ?
                articlesService.findAllByUserPageable(user, pageable):
                articlesService.findAllByArticlesAndUserPageable(user, searchValue, pageable);

        model.addAttribute("articles", articlesByUser);
        model.addAttribute("searchName", appNamesProps.getSearchFieldName());
        model.addAttribute("searchValue", searchValue);
        return "articles/all-articles-by-user";
    }

    @GetMapping("/edit/{id}")
    String handlingEditArticle(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("article", articlesService.getById(id));
        return "/articles/edit";
    }

    @PostMapping("/published/{id}")
    ResponseEntity<Void> handlingPublishedArticle(@PathVariable("id") UUID id, Boolean published) {
        articlesService.updatePublished(published, id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/delete/{id}")
    String handlingDeleteArticle(@PathVariable("id") UUID id) {
        articlesService.delete(id);
        return "redirect:/articles/all-articles-by-user";
    }

    @GetMapping("/create/upload")
    String handlingCreateUpload() {
        return "articles/upload-article";
    }

    @PostMapping("/create/upload")
    String handlingCreateUploadArticle(@RequestParam("file") MultipartFile file, @RequestParam("label") String label,
                                       Authentication authentication) {
        final UsersDetails usersDetails = (UsersDetails) authentication.getPrincipal();
        final UUID userId = usersDetails.getUser().getId();
        articlesService.save(file, label, userId);
        return "redirect:/articles/all-articles-by-user";
    }

    @GetMapping("/search-by-name")
    String handlingSearchByName(@RequestParam(value = "#{appNamesProps.searchFieldName}", required = true) String searchValue, Pageable pageable) {
        return String.format("redirect:/articles/all-articles-by-user?page=%s&size=%s&%s=%s", pageable.getPageNumber(), pageable.getPageSize(),
                appNamesProps.getSearchFieldName(), searchValue);
    }
}