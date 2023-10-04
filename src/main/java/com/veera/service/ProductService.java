package com.veera.service;

import com.veera.client.CartClient;
import com.veera.entity.Product;
import com.veera.repo.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
public class ProductService {
    private final ProductRepo productRepo;
    private final CartClient cartClient;

    private final ExecutorService virtualTaskExecutorService;
    //private final Executor virtualThreadExecutor;

    final ExecutorService threadTaskExecutorService;

    //private final Executor threadPoolExecutor;

    public ProductService(final ProductRepo productRepo,
                          final CartClient cartClient,
                          final ExecutorService virtualTaskExecutorService,
                          final ExecutorService threadTaskExecutorService) {
        this.productRepo = productRepo;
        this.cartClient = cartClient;
        this.virtualTaskExecutorService = virtualTaskExecutorService;
        this.threadTaskExecutorService = threadTaskExecutorService;
    }

    public void createProduct(Product product) {
        productRepo.save(product);
    }

    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    public String addProductToCartVirtual(final String userId, final Long productId) {
        String result = "Success";
        Future<?> future = virtualTaskExecutorService.submit(() -> {
            Optional<Product> productOpt = productRepo.findById(productId);
            if (productOpt.isPresent()) {
                cartClient.addToCart(userId, productOpt.get());
            }
            System.out.println(".....Thread pool executor" + Thread.currentThread().isVirtual());
        });
        try {
            future.get();
            System.out.println("Virtual task is completed");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public String addProductToCart(String userId, Long productId) {
        String result = "Success";
        Future<?> future = threadTaskExecutorService.submit(() -> {
            Optional<Product> productOpt = productRepo.findById(productId);
            if (productOpt.isPresent()) {
                cartClient.addToCart(userId, productOpt.get());
            }
            System.out.println(".....Thread pool executor" + Thread.currentThread().isVirtual());
        });
        try {
            future.get();
            System.out.println("Thread pool Task is completed");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
