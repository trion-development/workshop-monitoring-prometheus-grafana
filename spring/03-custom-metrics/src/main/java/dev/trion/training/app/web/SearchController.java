package dev.trion.training.app.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/search")
public class SearchController
{
    private final RestTemplate restTemplate;

    public SearchController(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    String search()
    {
        var result = restTemplate
           .getForObject("https://jsonplaceholder.typicode.com/albums/{id}",
              Album.class, 1);

        return result.getTitle();
    }

    static class Album
    {
        String title;
        Integer id;

        public String getTitle()
        {
            return title;
        }

        public void setTitle(String title)
        {
            this.title = title;
        }

        public Integer getId()
        {
            return id;
        }

        public void setId(Integer id)
        {
            this.id = id;
        }
    }
}
