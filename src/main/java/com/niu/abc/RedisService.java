package com.niu.abc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedisService {
	private static final Logger logger = LoggerFactory.getLogger(RedisService.class);
	public static ExecutorService executorService = new ThreadPoolExecutor(1, 100, 30, TimeUnit.MINUTES,
			new LinkedBlockingQueue<Runnable>(100), new RejectedExecutionHandler() {

				@Override
				public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
					try {
						executor.getQueue().put(r);
					} catch (InterruptedException e) {
						logger.info("线程处理异常.....");
						e.printStackTrace();
					}
				}
			});
	@PostConstruct
	public void runing() {
		Future<String> fs = executorService.submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				String result = RedisClient.jc.set("", "");
				return result;
			}

		});
		try {
			fs.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
