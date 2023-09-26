package com.veera.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/virtual")
public class VirtualController {

    @GetMapping
    public String virtual() {
//        Thread t = Thread.startVirtualThread(() -> {
//            for(int i=0;i<1000;i++) {
//                System.out.println("Virtual");
//            }
//        });
        Thread t = Thread.ofVirtual().start(() -> {
            for(int i=0;i<1000;i++) {
                System.out.println("Virtual thread");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        try {
            t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ;

        return "Success";
    }
}
