package com.isaac.sp_boot.app_reservations.web.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


@Configuration
public class LanguageConfig implements WebMvcConfigurer {

	/**
	 * Define que el idioma por defecto en la pagina es español
	 * 
	 * @return LocaleResolver slr
	 */
	@Bean
	public LocaleResolver localeResolver() {
		var slr = new SessionLocaleResolver();
		slr.setDefaultLocale(new Locale("es"));
		return slr;
	}

	/**
	 * Interceptor para poder cambiar el idioma de forma dinamica con un valor
	 * pasado a un parametro en la url
	 * 
	 * @return LocaleChangeInterceptor lci
	 */
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		var lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	/**
	 * Se registra el interceptor para que pueda funcionar
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registro) {
		registro.addInterceptor(localeChangeInterceptor());
	}

}
