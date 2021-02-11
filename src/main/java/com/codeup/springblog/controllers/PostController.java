package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @GetMapping(path = "/posts")
    public String index(Model model) {
        Post post1 = new Post(1, "First blog!", "This is a posting for stuff!");
        Post post2 = new Post(2, "Second blog.", "Still kinda neat...");

        List<Post> postedList = new ArrayList<>();
        postedList.add(post1);
        postedList.add(post2);

        model.addAttribute("title", "All Posts");
        model.addAttribute("posts", postedList);

        return "posts/index";
    }

    @GetMapping(path = "/posts/{id}")
    public String indexById(Model model) {
        Post post = new Post(1, "Blogging is neat!", "This is a post. I will make more!");
        model.addAttribute("title", "Single Post");
        model.addAttribute("post", post);
        return "posts/show";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    @ResponseBody
    public String create() {
        return "view the form for creating a post";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    @ResponseBody
    public String creating() {
        return "creating a new post";
    }
}
