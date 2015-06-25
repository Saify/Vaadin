package org.vaadin.training.backend;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ProgressingFuture<V> implements Future<V> {
	
	private Future<V> future;

	private int progressingState = 0;
	
	public ProgressingFuture(Future<V> future) {
		this.future = future;
	}
	
	public ProgressingFuture() {		
	}
	
	

	protected Future<V> getFuture() {
		return future;
	}

	protected void setFuture(Future<V> future) {
		this.future = future;
	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		return future.cancel(mayInterruptIfRunning);
	}

	@Override
	public V get() throws InterruptedException, ExecutionException {
		return future.get();
	}

	@Override
	public V get(long timeout, TimeUnit unit) throws InterruptedException,
			ExecutionException, TimeoutException {
		return future.get(timeout, unit);
	}

	@Override
	public boolean isCancelled() {
		return future.isCancelled();
	}

	@Override
	public boolean isDone() {
		return future.isDone();
	}
	
	public int getProgress() {
		return progressingState;
	}

	public void setProgress(int progressingState) {
		this.progressingState = progressingState;
	}

}
