package ci.ashamaz.sweater.config

import ci.ashamaz.sweater.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.NoOpPasswordEncoder

@Configuration
@EnableWebSecurity
open class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    @Autowired
    val userService: UserService? = null
    override fun configure(http: HttpSecurity?) {
        http!!
                .authorizeRequests()
                .antMatchers("/", "/registration").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/user", "/user/*").hasAnyAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!
                .userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
    }
}