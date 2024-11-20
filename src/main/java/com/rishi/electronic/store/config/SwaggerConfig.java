package com.rishi.electronic.store.config;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Electronic Store Api")
                        .description("This is backed of electronic store developed in premium course")
                        .version("1.0v")
                        .contact(new Contact().name("Rishi singh").email("rishicoding9838@gmail.com").url(""))
                        .license(new License().name("Apache"))
                ).externalDocs(new ExternalDocumentation()
                        .description("this is external URl")
                        .url("https://springshop.wiki.github.org/docs"));
//
    }
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
