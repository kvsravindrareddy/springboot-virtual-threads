package com.veera.client;

import com.veera.config.CartConfig;
import com.veera.entity.Product;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class CartClient {

    private final RestClient restClient;
    private final CartConfig cartConfig;

    public CartClient(final RestClient restClient,
                      final CartConfig cartConfig) {
        this.restClient = restClient;
        this.cartConfig = cartConfig;
    }

    public void addToCart(final String userId, final Product product) {
        restClient.post().uri(cartConfig.getUrl()+"?userId="+userId)
                .contentType(MediaType.APPLICATION_JSON)
                .body(product).retrieve().toBodilessEntity();
    }
}
