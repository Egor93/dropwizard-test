package io.dropwizard.api;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface HelloWorldDAO
{
    //@SqlUpdate("create table something (id int primary key, name varchar(100))")
    //void createSomethingTable();

   // @SqlUpdate("insert into something (id, name) values (:id, :name)")
    //void insert(@Bind("id") int id, @Bind("name") String name);

    @SqlQuery("select id_term from public.term order by id_term asc limit  :nrows ")
    List<Integer> selectFirstNrows(@Bind("nrows") int nrows);
    // Question: will the method above really return a string as
    // a query result, how will this string be formatted?
}




