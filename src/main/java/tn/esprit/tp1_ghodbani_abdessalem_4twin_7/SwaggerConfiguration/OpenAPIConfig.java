package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.SwaggerConfiguration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Abdessalem",
                        email = "abdessalem.ghodbani@esprit.tn",
                        url = "https://www.linkedin.com/in/abdessalem-ghodbeni/"
                ),
                description = "Open Api made by Ghodbani Abdessalem",
                version = "",
                termsOfService = "JDK17, SpringBoot 3 , University,foyer"
        ),
        servers={
                @Server(
                        description ="Local Dev Env",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "Prod Env",
                        url = ""
                )




}

)
public class OpenAPIConfig {
}
