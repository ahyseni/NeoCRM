package com.mrkt.admin;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {


//    private final UserService userDetailsService;
//    @Autowired
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//    public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.userDetailsService = userDetailsService;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().
                authorizeRequests()
//                .antMatchers(HttpMethod.POST, SecurityConstants.)
//                .permitAll()
//                .antMatchers(HttpMethod.GET, SecurityConstants.VERIFICATION_EMAIL_URL)
//                .permitAll()
//                .antMatchers(HttpMethod.POST, SecurityConstants.PASSWORD_RESET_REQUEST_URL)
//                .permitAll()
//                .antMatchers(HttpMethod.POST, SecurityConstants.PASSWORD_RESET_URL)
//                .permitAll()
                .antMatchers("/api/**")
                .permitAll()
                .antMatchers("/api/**", "/configuration/**", "/swagger*/**", "/webjars/**")
                .permitAll();
//                .anyRequest().authenticated()
//                .and()
//                .addFilter( new AuthenticationFilter(authenticationManager()) )
//                .addFilter( new AuthorizationFilter( authenticationManager() ))
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.headers().frameOptions().disable();
    }
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
//    }
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        final CorsConfiguration configuration = new CorsConfiguration();
//        //configuration.setAllowedOrigins(ImmutableList.of("http://localhost:8080","http://localhost:8084"));
//        configuration.setAllowedOrigins(ImmutableList.of("*"));
//        configuration.setAllowedMethods(ImmutableList.of("GET", "POST", "PUT", "DELETE"));
//        configuration.setAllowCredentials(true);
//        configuration.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type"));
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

}
