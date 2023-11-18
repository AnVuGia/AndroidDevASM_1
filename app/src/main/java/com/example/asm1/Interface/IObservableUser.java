package com.example.asm1.Interface;

public interface IObservableUser {
    void register(IObserverUser observer);
    void unregister(IObserverUser observer);
    void notifyObservers();
}
