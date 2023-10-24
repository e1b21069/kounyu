package oit.is.lec05.team6.kounyu.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class Sample3AuthConfiguration {
  /**
   * 認可処理に関する設定（認証されたユーザがどこにアクセスできるか）
   *
   * @param http
   * @return
   * @throws Exception
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.formLogin(login -> login
        .permitAll())
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")) // ログアウト後に / にリダイレクト
        .authorizeHttpRequests(authz -> authz
            .requestMatchers(AntPathRequestMatcher.antMatcher("/sample5/**"))
            .authenticated() // /sample4/以下は認証済みであること
            .requestMatchers(AntPathRequestMatcher.antMatcher("/**"))
            .permitAll())// 上記以外は全員アクセス可能
        .csrf(csrf -> csrf
            .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/*")))// h2-console用にCSRF対策を無効化
        .headers(headers -> headers
            .frameOptions(frameOptions -> frameOptions
                .sameOrigin()));
    return http.build();
  }

  /**
   * 認証処理に関する設定（誰がどのようなロールでログインできるか）
   *
   * @return
   */
  @Bean
  public InMemoryUserDetailsManager userDetailsService() {

    // ユーザ名，パスワード，ロールを指定してbuildする
    // このときパスワードはBCryptでハッシュ化されているため，{bcrypt}とつける
    // ハッシュ化せずに平文でパスワードを指定する場合は{noop}をつける
    // ハッシュ化されたパスワードを得るには，この授業のbashターミナルで下記のように末尾にユーザ名とパスワードを指定すると良い(要VPN)
    // $ sshrun htpasswd -nbBC 10 user1 p@ss

    // $ sshrun htpasswd -nbBC 10 customer1 p@ss
    UserDetails customer1 = User.withUsername("customer1")
        .password("{bcrypt}$2y$10$fPRBHNPUAVbjRY/tSFYsYuOoZBe7/yU1ojUGSor8IaVpeS.gsGl02")
        .roles("CUSTOMER")
        .build();
    // $ sshrun htpasswd -nbBC 10 customer2 p@ss
    UserDetails customer2 = User.withUsername("customer2")
        .password("{bcrypt}$2y$10$1UobwJX3buaxLRBWR.0gkunuVCAvjoUc/vzwrCWX9QILN2OM/Avf6")
        .roles("CUSTOMER")
        .build();
    // $ sshrun htpasswd -nbBC 10 seller p@ss
    UserDetails seller = User.withUsername("seller")
        .password("{bcrypt}$2y$10$/X4HVicOBmqcv9SZqmfI3uuvPN8NhMJojczjlfQnX7gs2bvwkupve")
        .roles("SELLER")
        .build();
    //pass word naoya
    // 生成したユーザをImMemoryUserDetailsManagerに渡す（いくつでも良い）
    return new InMemoryUserDetailsManager(customer1, customer2, seller);
  }

}
