package oit.is.z2883.kaizi.janken.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class JankenAuthConfiguration {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.formLogin(login -> login
        .permitAll())
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")) // ログアウト後に / にリダイレクト
        .authorizeHttpRequests(authz -> authz
            .requestMatchers("/janken/**").authenticated() // /janken/以下はログイン必須
            .anyRequest().permitAll()); // それ以外は誰でもOK
    return http.build();
  }

  @Bean
  public InMemoryUserDetailsManager userDetailsService() {

    UserDetails user1 = User.withUsername("user1")
        .password("{bcrypt}$2y$05$j72EisM.f6uhlHmbTQ/oGuLRlj2KoAsxJvNtgjiYzzf7DVq4aGVHq")
        .roles("USER").build();

    UserDetails user2 = User.withUsername("user2")
        .password("{bcrypt}$2y$05$yCW74Ch4u.syClJnmRmdguFCpnXo4To1N5tpbKYIxqHbxB6/UuK2S")
        .roles("USER").build();

    UserDetails honda = User.withUsername("honda")
        .password("{bcrypt}$2y$05$yCW74Ch4u.syClJnmRmdguFCpnXo4To1N5tpbKYIxqHbxB6/UuK2S")
        .roles("USER").build();

    return new InMemoryUserDetailsManager(user1, user2,  honda);
  }

}
