package io.dropwizard;

import io.dropwizard.api.HelloWorldDAO;
import io.dropwizard.resources.DBresource;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.resources.HelloWorldResource;
import io.dropwizard.health.TemplateHealthCheck;
//DB -related
import org.jdbi.v3.core.Jdbi;
import io.dropwizard.jdbi3.JdbiFactory;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.List;

public class HelloWorldApplication extends Application<HelloWorldConfiguration>
{

        public HelloWorldApplication()
        {
                System.out.println("CALLING CONSTRUCTOR OF HWAPP");
        }

        public static void main(String[] args) throws Exception
        {
                System.out.println("STARTING MAIN");

                HelloWorldApplication HWapp ; //declaration of the variable
                HWapp = new HelloWorldApplication(); //instantiation, create an object of class and assign it the HWapp variable
                System.out.println(HWapp.getName()   );
                HWapp.run(args); //calling class method (inherited from the parent class - Application)

                System.out.println("ENDING MAIN");
        }

        @Override
        public String getName()
        {
                return "hello-world";
        }

        @Override
        public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap)
        {
            // nothing to do yet
        }

        @Override
        public void run(HelloWorldConfiguration configuration, Environment environment)
        {
                //DB related part: START
                final JdbiFactory factory = new JdbiFactory();
                final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
                jdbi.installPlugin(new SqlObjectPlugin());
                DBresource dBresource = new DBresource(jdbi);

                //DB related part: END

                final HelloWorldResource resource = new HelloWorldResource
                        (
                        configuration.getTemplate(),
                        configuration.getDefaultName()
                        );

                final TemplateHealthCheck healthCheck =
                        new TemplateHealthCheck(configuration.getTemplate());
                environment.healthChecks().register("template", healthCheck);

                environment.jersey().register(resource);
                environment.jersey().register(dBresource);
        }

}