package kr.co.wikibook.gallery.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import kr.co.wikibook.gallery.account.helper.AccountHelper;
import kr.co.wikibook.gallery.common.config.authenticate.CustomAccessDeniedHandler;
import kr.co.wikibook.gallery.common.config.authenticate.JwtAuthFilter;
import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
	private final CustomAccessDeniedHandler accessDeniedHandler;
	private final CustomAuthenticationEntryPoint authenticationEntryPoint;
//	private final MemberService memberService;
	private final CustomUserDetailsService customUserDetailsService;
	private final AccountHelper accountHelper;
//	private final JwtUtils jwtUtils;

	private static final String[] PUBLIC = new String[] { "/v1/api/account/check", "/error", "/v1/api/account/login",
			"/v1/api/account/logout", "/v1/api/account/join", "/v1/api/items" };

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//CSRF, CORS
		http.csrf(AbstractHttpConfigurer::disable);
		http.cors((Customizer.withDefaults()));

		//세션 관리 상태 없음으로 구성, Spring Security가 세션 생성 or 사용 x
		http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(
			SessionCreationPolicy.STATELESS));

		//FormLogin, BasicHttp 비활성화
		http.formLogin(AbstractHttpConfigurer::disable);
		http.httpBasic(AbstractHttpConfigurer::disable);

		//JwtAuthFilter를 UsernamePasswordAuthenticationFilter 앞에 추가
		http.addFilterBefore(new JwtAuthFilter(customUserDetailsService, accountHelper),
			UsernamePasswordAuthenticationFilter.class);

		http.exceptionHandling((exceptionHandling) -> exceptionHandling.authenticationEntryPoint(
			authenticationEntryPoint).accessDeniedHandler(accessDeniedHandler));

		//권한 규칙 작성
		http
		.securityMatcher("/actuator/**")
		.authorizeHttpRequests(auth -> auth
				.requestMatchers("/actuator/health").permitAll()
				.requestMatchers("/actuator/info").permitAll()
				.requestMatchers("/actuator/prometheus").hasRole("MONITORING")
				.requestMatchers("/actuator/**").hasRole("ADMIN"))
		.authorizeHttpRequests(authorize -> authorize
				.requestMatchers(PUBLIC).permitAll()
				.requestMatchers("/assets/**", "/img/**", "/favicon.ico", "/index.html").permitAll()
				.anyRequest().authenticated());

		return http.build();
	}
	
}