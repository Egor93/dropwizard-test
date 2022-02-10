package io.dropwizard;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.Valid;
import io.dropwizard.db.DataSourceFactory;

public class HelloWorldConfiguration extends Configuration {

        public HelloWorldConfiguration()
        {
            System.out.println("Constructor of the HWconfig class");
        }

        /* DB factory to access PSQL database */
        @Valid
        @NotNull
        private DataSourceFactory database = new DataSourceFactory();

        @NotEmpty
        private String template;

        @NotEmpty
        private String defaultName = "Stranger";


        // DB getter and setter: START
        @JsonProperty("database")  //writes down the factory into YML field "database"
        public void setDataSourceFactory(DataSourceFactory factory) {
                this.database = factory;
        }

        @JsonProperty("database") //reads YML field "database"
        public DataSourceFactory getDataSourceFactory() {
                return database;
        }
        // DB getter and setter: END


        @JsonProperty
        public String getTemplate()
        {
                return template;
        }

        @JsonProperty
        public void setTemplate(String template)
        {
                this.template = template;
        }

        @JsonProperty
        public String getDefaultName()
        {
                 return defaultName;
        }

        @JsonProperty
        public void setDefaultName(String name)
        {
                this.defaultName = name;
        }
}