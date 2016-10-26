package hu.dpc.edu.web;

import org.springframework.hateoas.LinkBuilder;

/**
 * Created by vrg on 26/10/16.
 */
public interface LinkBuilderProvider<E> {

    LinkBuilder getLinkBuilder(E entity);

}
