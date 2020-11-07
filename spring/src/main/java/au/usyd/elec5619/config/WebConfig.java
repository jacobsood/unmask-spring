package au.usyd.elec5619.config;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
  @Autowired
  @Qualifier("jstlViewResolver")
  private ViewResolver jstlViewResolver;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/vue/**").addResourceLocations("/WEB-INF/view/vue/");
    registry.addResourceHandler("/jsp/**").addResourceLocations("/WEB-INF/view/jsp/");
  }

  @Bean
  @DependsOn({ "jstlViewResolver" })
  public ViewResolver viewResolver() {
    return jstlViewResolver;
  }

  @Bean(name = "jstlViewResolver")
  public ViewResolver jstlViewResolver() {
    UrlBasedViewResolver resolver = new UrlBasedViewResolver();
    resolver.setPrefix(""); // NOTE: no prefix here
    resolver.setViewClass(JstlView.class);
    resolver.setSuffix(""); // NOTE: no suffix here
    return resolver;
  }
}