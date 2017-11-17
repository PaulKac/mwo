package com.mwo.klasterix.api.converters;

import com.mwo.klasterix.api.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.data.geo.format.DistanceFormatter;
import org.springframework.data.geo.format.PointFormatter;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.mapping.context.PersistentEntities;
import org.springframework.data.repository.support.DefaultRepositoryInvokerFactory;
import org.springframework.data.repository.support.DomainClassConverter;
import org.springframework.data.repository.support.Repositories;
import org.springframework.data.rest.core.UriToEntityConverter;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class EntityConverter {

	@Autowired
	private MappingContext<?, ?> mappingContext;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired(required = false)
	private List<RepositoryRestConfigurer> configurers = Collections.emptyList();

	public <T> T convert(Link link, Class<T> target) {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();

		PersistentEntities entities = new PersistentEntities(Arrays.asList(mappingContext));

		Repositories repositories = new Repositories(Main.context);

		DefaultRepositoryInvokerFactory invokerFactory = new DefaultRepositoryInvokerFactory(repositories, conversionService);

		UriToEntityConverter converter = new UriToEntityConverter(entities, invokerFactory, repositories);
		conversionService.addConverter(converter);
		addFormatters(conversionService);
		for (RepositoryRestConfigurer configurer : configurers) {
			configurer.configureConversionService(conversionService);
		}

		URI uri = convert(link);
		T object = target.cast(conversionService.convert(uri, TypeDescriptor.valueOf(target)));
		if (object == null) {
			throw new IllegalArgumentException(String.format("%s '%s' was not found.", target.getSimpleName(), uri));
		}
		return object;
	}

	private URI convert(Link link) {
		try {
			return new URI(link.getHref());
		} catch (Exception e) {
			throw new IllegalArgumentException("URI from link is invalid", e);
		}
	}

	private void addFormatters(FormatterRegistry registry) {

		registry.addFormatter(DistanceFormatter.INSTANCE);
		registry.addFormatter(PointFormatter.INSTANCE);

		if (!(registry instanceof FormattingConversionService)) {
			return;
		}

		FormattingConversionService conversionService = (FormattingConversionService) registry;

		DomainClassConverter<FormattingConversionService> converter = new DomainClassConverter<FormattingConversionService>(
				conversionService);
		converter.setApplicationContext(applicationContext);
	}

}