package com.example.appmvvm

class Observable<T> {
    private var observers = emptyList<(T)->Unit>()
    fun addObservers(observer:(T)->Unit)
    {
        observers += observers
    }
    fun clearObservers(){
        observers = emptyList()
    }
    fun callObservers(newValue:T){
        observers.forEach{ it(newValue) }
    }
}