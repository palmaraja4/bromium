package com.hribol.bromium.replay.filters;

import com.hribol.bromium.replay.execution.synchronization.SynchronizationEvent;
import io.netty.handler.codec.http.HttpRequest;

import java.net.URISyntaxException;
import java.util.Set;
import java.util.function.BooleanSupplier;

/**
 * Created by hvrigazov on 20.05.17.
 */
public class ReplayFiltersFactory {

    public ReplayRequestFilter createReplayRequestFilter(String baseURI,
                                                         Set<HttpRequest> httpRequestQueue) throws URISyntaxException {
        return new ReplayRequestFilter(baseURI, httpRequestQueue);
    }

    public ReplayResponseFilter createReplayResponseFilter(String injectionCode, String baseURI, Set<HttpRequest> httpRequestQueue) throws URISyntaxException {
        return new ReplayResponseFilter(injectionCode, baseURI, httpRequestQueue);
    }
}
