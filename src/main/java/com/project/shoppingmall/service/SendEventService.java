package com.project.shoppingmall.service;

import com.project.shoppingmall.domain.Users;
import com.project.shoppingmall.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

@RequiredArgsConstructor
@Service
@Slf4j
public class SendEventService {

    int processors = Runtime.getRuntime().availableProcessors();
    int threadPoolSize = Math.max(2, processors);
    ExecutorService customThreadPool = Executors.newWorkStealingPool(threadPoolSize);

    private final UsersRepository usersRepository;

    public long sendAll() {
        ForkJoinPool commonPool = ForkJoinPool.commonPool();

        int commonPoolSize = commonPool.getParallelism();

        List<Users> usersList = usersRepository.findAll();
        long start = System.currentTimeMillis();

        // 동기
        usersList.forEach(users -> sendEvent("봄 맞이 30% 할인 쿠폰"));

        // 비동기
//        usersList.forEach(users ->
//                CompletableFuture.runAsync(() -> sendEvent("봄 맞이 30% 할인 쿠폰"), customThreadPool)
//                        .exceptionally(throwable -> {
//                            log.error("Exception occurred while sending event", throwable.getMessage());
//                            return null;
//                        }));

        long end = System.currentTimeMillis();
        long diff = end - start;

        return diff;
    }

    public void sendEvent(String message) {
        try {
            Thread.sleep(1);
            log.info("message: " + message);
        } catch(Exception e) {
            log.error("[Error] : {}", e.getMessage());
        }
    }
}
