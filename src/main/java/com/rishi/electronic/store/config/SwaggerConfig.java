package com.rishi.electronic.store.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.servers.Server;

import java.util.List;


/*@SecurityScheme(
        name = "scheme1",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
@OpenAPIDefinition(
        info = @Info(
                title = "Electronic Store API ",
                description = "This is backed of electronic store by Rishi singh",
                version = "1.0V",
                contact = @Contact(
                        name = "Rishi Singh",
                        email = "rishicoding9838@gmail.com",
                        url = "https://google.com"
                ),
                license = @License(
                        name = "OPEN License",
                        url = "https://learncodewithdurgesh.com"
                )
        )
        ,
        externalDocs = @ExternalDocumentation(
                description = "This is external docs",
                url = "https://learncodewithdurgesh.com"
        )
)*/
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        String schemeName = "bearerScheme";

        return new OpenAPI()
                // Define security requirements
                .addSecurityItem(new SecurityRequirement().addList(schemeName))
                // Define components including security schemes
                .components(new Components()
                        .addSecuritySchemes(schemeName, new SecurityScheme()
                                .name(schemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .bearerFormat("JWT")
                                .scheme("bearer")))
                // Add metadata information
                .info(new Info()
                        .title("Electronic Store API")
                        .description("This is the backend of the Electronic Store App by Rishi Singh")
                        .version("1.0v")
                        .contact(new Contact()
                                .name("Rishi Singh")
                                .email("rishicoding9838@gmail.com")
                                .url("")) // You can leave URL blank or provide a real one
                        .license(new License()
                                .name("Apache License 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                // Add external documentation if needed
                .externalDocs(new ExternalDocumentation()
                        .description("This is an external URL")
                        .url("https://springshop.wiki.github.org/docs"))
                // Add server information
                .servers(List.of(new Server()
                        .url("http://localhost:9090")
                        .description("Generated server URL")));
    }
   /* @Bean
    public OpenAPI openAPI(){

        String schemeName = "bearerScheme";

        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement()
                        .addList(schemeName)
                )
                .components(new Components()
                        .addSecuritySchemes(schemeName, new SecurityScheme()
                                .name(schemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .bearerFormat("JWT")
                                .scheme("bearer")
                        )
                )
                .info(new Info()
                         .title("Electronic Store Api")
                        .description("This is backed of electronic store App by Rishi singh")
                        .version("1.0v")
                        .contact(new Contact().name("Rishi singh").email("rishicoding9838@gmail.com").url(""))
                        .license(new License().name("Apache"))
                ).externalDocs(new ExternalDocumentation()
                        .description("this is external URl")
                        .url("https://springshop.wiki.github.org/docs"));
    }
   */
//
//    @Bean
//    public OpenAPI openAPI() {
//
//        String schemeName = "bearerScheme";
//
//        return new OpenAPI()
//                .addSecurityItem(new SecurityRequirement()
//                        .addList(schemeName)
//                )
//                .components(new Components()
//                        .addSecuritySchemes(schemeName, new SecurityScheme()
//                                .name(schemeName)
//                                .type(SecurityScheme.Type.HTTP)
//                                .bearerFormat("JWT")
//                                .scheme("bearer")
//                        )
//                )
//                .info(new Info()
//                        .title("Electronic Store API")
//                        .description("This is electronic store project api developed by LCWD")
//                        .version("1.0")
//                        .contact(new Contact().name("Durgesh").email("durgesh@gmail.com").url("durgesh.com"))
//                        .license(new License().name("Apache"))
//
//                ).externalDocs(new ExternalDocumentation().url("learncodewithdurgesh.com").description("this is external url"))
//
//                ;
//
//    }
//
//    @Bean
//    public Docket docket() {
//        Docket docket = new Docket(DocumentationType.SWAGGER_2);
//        docket.apiInfo(getApiInfo());
//        docket.securityContexts(Arrays.asList(getSecurityContext()));
//        docket.securitySchemes(Arrays.asList(getSchemes()));
//        ApiSelectorBuilder select = docket.select();
//        select.apis(RequestHandlerSelectors.any());
//        select.paths(PathSelectors.any());
//        Docket build = select.build();
//        return build;
//    }
//
//
//    private SecurityContext getSecurityContext() {
//
//        SecurityContext context = SecurityContext
//                .builder()
//                .securityReferences(getSecurityReferences())
//                .build();
//        return context;
//    }
//
//    private List<SecurityReference> getSecurityReferences() {
//        AuthorizationScope[] scopes = {new AuthorizationScope("Global", "Access Every Thing")};
//        return Arrays.asList(new SecurityReference("JWT", scopes));
//
//    }
//
//    private ApiKey getSchemes() {
//        return new ApiKey("JWT", "Authorization", "header");
//    }
//
//
//    private ApiInfo getApiInfo() {
//
//        ApiInfo apiInfo = new ApiInfo(
//                "Electronic Store Backend : APIS ",
//                "This is backend project created by LCWD",
//                "1.0.0V",
//                "https://www.learncodewithdurgesh.com",
//                new Contact("Durgesh", "https://www.instagram.com/durgesh_k_t", "learncodewithdurgesh@gmail.com"),
//                "License of APIS",
//                "https://www.learncodewithdurgesh.com/about",
//                new ArrayDeque<>()
//        );
//
//        return apiInfo;
//
//    }
//


    //@SecurityScheme(
//        name = "scheme1",
//        type = SecuritySchemeType.HTTP,
//        bearerFormat = "JWT",
//        scheme = "bearer"
//)
//@OpenAPIDefinition(
//        info = @Info(
//                title = "Electronic Store API ",
//                description = "This is backed of electronic store developed in premium course",
//                version = "1.0V",
//                contact = @Contact(
//                        name = "Rishi singh",
//                        email = "rishicoding9838@gmail.com",
//                        url = ""
//                ),
//                license = @License(
//                        name = "OPEN License",
//                        url = "https://learncodewithdurgesh.com"
//                )
//        )
//        ,
//        externalDocs = @ExternalDocumentation(
//                description = "This is external docs",
//                url = ""
//        )
//)

}
