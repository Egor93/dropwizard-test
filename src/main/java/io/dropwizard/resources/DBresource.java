package io.dropwizard.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.api.DBoutDisplayer;
import io.dropwizard.api.HelloWorldDAO;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/db-page")
@Produces(MediaType.APPLICATION_JSON)
public class DBresource
{
        //TODO: read default param from .yml
        private final int defaultNrows = 10;
        private String defaultColumn = "id_term";
        private int counter = 1;
        private final Jdbi jdbi;

        public DBresource(Jdbi jdbiFromHWapp)
        {
                this.jdbi = jdbiFromHWapp;
        }

        @GET
        @Timed
        public DBoutDisplayer GenerateDBout
                (
                @QueryParam("column") Optional<String> column,
                @QueryParam("nrows") Optional<Integer> nrows
        )
        {
                final int nrowsVal =  nrows.orElse(defaultNrows);
                final String columnVal =  column.orElse(defaultColumn);
                HelloWorldDAO helloWorldDAO = jdbi.onDemand(HelloWorldDAO.class);// implementation of the  interface
                List<String> strings = helloWorldDAO.selectFirstNrows(columnVal,nrowsVal);
                System.out.println("Returned query result is "+strings.toString());

                return new DBoutDisplayer(++counter, strings);
        }
}