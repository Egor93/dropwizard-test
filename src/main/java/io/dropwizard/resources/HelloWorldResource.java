package io.dropwizard.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Optional;
import io.dropwizard.api.Saying;
import com.codahale.metrics.annotation.Timed;
import org.jdbi.v3.core.Jdbi;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource
{
        private final String template;
        private final String defaultName;
        private final AtomicLong counter;
        private final Jdbi jdbi; //DB-related

        public HelloWorldResource(String template, String defaultName, Jdbi jdbi)
        {
                this.template = template;
                this.defaultName = defaultName;
                this.jdbi = jdbi; //DB-related
                this.counter = new AtomicLong();
        }

        @GET
        @Timed
        public Saying sayHello(@QueryParam("name") Optional<String> name)
        {
                final String value = String.format(template, name.orElse(defaultName));
                return new Saying(counter.incrementAndGet(), value);
        }
}