package com.sample.contact.demo.mvp.usecase;

public interface UseCaseScheduler {

    void execute(final Runnable runnable);

    <V extends UseCase.ResponseValue> void notifyResponse(final V response,
                                                          final UseCase.UseCaseCallback<V> useCaseCallback);

    <V extends UseCase.ResponseValue> void onError(final UseCase.UseCaseCallback<V> useCaseCallback);
}