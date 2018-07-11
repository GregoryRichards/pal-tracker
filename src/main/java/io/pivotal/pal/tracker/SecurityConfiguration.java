package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    private boolean httpsDisabled;

    public SecurityConfiguration(@Value("${HTTPS_DISABLED}") boolean httpsDisabled )
    {
        this.httpsDisabled = httpsDisabled;

    }

    @Override
    public void configure( HttpSecurity security ) throws Exception
    {
        if ( !httpsDisabled )
        {
            security.requiresChannel().anyRequest().requiresSecure();
        }

        security.authorizeRequests().antMatchers("/**").hasRole("USER")
                .and().httpBasic().and().csrf().disable();

    }

    @Override
    public void configure(AuthenticationManagerBuilder builder ) throws Exception
    {
        builder.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    }
}
