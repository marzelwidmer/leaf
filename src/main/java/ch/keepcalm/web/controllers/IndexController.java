package ch.keepcalm.web.controllers;


import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by marcelwidmer on 20/03/16.
 */
@RestController
@RequestMapping("/api")
public class IndexController {


    @RequestMapping(value="",
            method = RequestMethod.GET
    )
    public HttpEntity<List<Link>> showLinks() {

        List<Link> links = new ArrayList<Link>();
        Link self = linkTo(IndexController.class).withSelfRel();
        Link product = linkTo(methodOn(ProductRestController.class).showAll()).withRel("products");


       /* Link person = linkTo(methodOn(PersonController.class).show(1L)).withRel("person");
        Link products = linkTo(ProductController.class).withRel("products");
        Link personProducts = linkTo(PersonProductController.class, 1L).withRel("personProducts");
        Link personResources = linkTo(methodOn(PersonController.class).showAllAsResources()).withRel("peopleAsResources");
        Link pagedResources = linkTo(methodOn(PersonController.class).showAllPaged()).withRel("peoplePaged");
        Link search = linkTo(methodOn(PersonController.class).searchPersonForm()).withRel("search");
*/
        links.add(self);
        links.add(product);
        //links.add(search);
        /*links.add(person);
        links.add(products);
        links.add(personProducts);
        links.add(personResources);
        links.add(pagedResources);*/
        return new HttpEntity<List<Link>>(links);
    }

}
