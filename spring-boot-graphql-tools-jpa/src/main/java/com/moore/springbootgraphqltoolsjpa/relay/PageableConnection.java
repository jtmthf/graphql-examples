package com.moore.springbootgraphqltoolsjpa.relay;

import graphql.relay.Connection;
import graphql.relay.InvalidCursorException;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.util.Assert;

import java.nio.charset.StandardCharsets;
import java.util.function.Function;

import static java.lang.String.format;
import static java.util.Base64.getDecoder;

public class PageableConnection<T> implements DataFetcher<Connection<T>> {

    private static final String DUMMY_CURSOR_PREFIX = "pageable-cursor";
    private final String prefix;
    private final Function<Pageable, ? extends Slice<? extends T>> fetcher;

    public PageableConnection(Function<Pageable, ? extends Slice<? extends T>> fetcher, String prefix) {
        Assert.notNull(fetcher, "fetcher cannot be null");
        Assert.hasText(prefix, "prefix cannot be null or empty");

        this.fetcher = fetcher;
        this.prefix = prefix;
    }

    public PageableConnection(Function<Pageable, ? extends Slice<? extends T>> fetcher) {
        this(fetcher, DUMMY_CURSOR_PREFIX);
    }

    @Override
    public Connection<T> get(DataFetchingEnvironment environment) {
        environment.getArgument("after");
        environment.getArgument("before");
    }

    private int getOffsetFromCursor(String cursor, int defaultValue) {
        if (cursor == null) {
            return defaultValue;
        }
        byte[] decode;
        try {
            decode = getDecoder().decode(cursor);
        } catch (IllegalArgumentException e) {
            throw new InvalidCursorException(format("The cursor is not in base64 format : %s", cursor), e);
        }
        String string = new String(decode, StandardCharsets.UTF_8);
        if (prefix.length() > string.length()) {
            throw new RuntimeException();
        }
        try {
            return Integer.parseInt(string.substring(prefix.length()));
        } catch (NumberFormatException nfe) {
            throw new RuntimeException()
        }
    }
}
