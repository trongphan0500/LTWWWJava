package vn.edu.iuh.fit.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import vn.edu.iuh.fit.utils.SecurityRoles;

import javax.sql.DataSource;

import static vn.edu.iuh.fit.utils.SecurityRoles.ADMIN_ROLE;
import static vn.edu.iuh.fit.utils.SecurityRoles.USER_ROLE;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final DataSource dataSource;
    @Value("${spring.admin.username}")
    private String adminUsername;
    @Value("${spring.admin.password}")
    private String adminPassword;
    @Value("${spring.queries.users-query}")
    private String usersQuery;
    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void configureGlobal(
            AuthenticationManagerBuilder auth) throws Exception {
       /* auth.
                jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder())
        ;*/
        UserDetails ud_teo = User.builder()
                .username("teo")
                .password("$2a$10$iVIV6KTf2GdAAo9FpLVpsubzVP8undhidTtCyftx.OnfBn5yn2eZW")
                .roles(ADMIN_ROLE)
                .build();
        UserDetails ud_ty = User.builder()
                .username("ty")
                .password("$2a$10$hFhz/f3e0GI5y4baqAvdX.BlEiMivXXcOU9kKNgyUDCBi1f7hbmFa")
                .roles(USER_ROLE)
                .build();
        auth.inMemoryAuthentication()
                .withUser(ud_teo)
                .withUser(ud_ty);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/", "/index", "/error", "/home").permitAll()
                                .requestMatchers("/admin", "/admin/**").hasRole(ADMIN_ROLE)
                                //.requestMatchers("/api/**").hasRole(USER_ROLE)
                                .requestMatchers("/api/**").hasAnyRole(USER_ROLE,ADMIN_ROLE)
//                                .requestMatchers("/adapi/**").hasAnyRole(ADMIN_ROLE)
                                .requestMatchers("/sensitive/**").denyAll()
                                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/logon")
                        .defaultSuccessUrl("/home")
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll)

                .httpBasic(Customizer.withDefaults())
        ;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
