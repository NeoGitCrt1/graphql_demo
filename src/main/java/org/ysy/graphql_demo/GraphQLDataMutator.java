package org.ysy.graphql_demo;

import com.google.common.collect.ImmutableMap;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class GraphQLDataMutator {

    private final AtomicInteger seq = new AtomicInteger(10);
    public DataFetcher putBook() {
        return dataFetchingEnvironment -> {
            Map<String, Object> bookId = dataFetchingEnvironment.getArgument("input");
            String newId = "book-" + seq.getAndIncrement();
            GraphQLDataHolder.books.add(ImmutableMap.of("id", newId,
                    "name", bookId.get("name").toString(),
                    "pageCount", bookId.get("pageCount").toString(),
                    "authorId", bookId.get("author").toString()));
            return newId;
        };
    }
}
