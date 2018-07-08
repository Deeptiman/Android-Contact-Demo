package com.sample.contact.demo.mvp.usecase;

public abstract class UseCase<Q extends UseCase.RequestValues, P extends UseCase.ResponseValue> {

    private Q mRequestValues;

    private UseCaseCallback<P> mUseCaseCallback;

    public void setRequestValues(final Q requestValues) {
        mRequestValues = requestValues;
    }

    public Q getRequestValues() {
        return mRequestValues;
    }

    public UseCaseCallback<P> getUseCaseCallback() {
        return mUseCaseCallback;
    }

    public void setUseCaseCallback(final UseCaseCallback<P> useCaseCallback) {
        mUseCaseCallback = useCaseCallback;
    }

    abstract public boolean validateRequest(Q requestValues);

    abstract public boolean validateResponse(P responseValues);

    public void run() {
        executeUseCase(mRequestValues);
    }

    protected abstract void executeUseCase(final Q requestValues);

    public interface RequestValues {
    }

    public interface ResponseValue {
    }

    public interface UseCaseCallback<R> {
        void onSuccess(final R response);

        void onError();
    }
}