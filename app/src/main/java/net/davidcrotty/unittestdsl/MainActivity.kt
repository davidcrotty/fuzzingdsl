package net.davidcrotty.unittestdsl

import android.arch.lifecycle.MutableLiveData
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // we call an API with a response if the data == something
        // else we call another API

        // expect a response message of success or not
    }
}

class Order(val name: String, val email: String, val ingredients: Ingredients)

class Ingredients(val waterTemperature: Int, sugarCount: Int, milk: Boolean, keyIngredient: KeyIngredient)

class OrderStatus(val success: Boolean, val orderNumber: Int, ingredients: Ingredients)

sealed class KeyIngredient {
    object BreakfastTea : KeyIngredient()
    object InstantCoffee: KeyIngredient()
    object CoffeeBeans: KeyIngredient()
}

abstract class UseCase<T, R> {

    private val liveData = MutableLiveData<R>()

    abstract fun execute(param: T)

    fun observe() : MutableLiveData<R> {
        return liveData
    }
}

class DrinkUseCase : UseCase<Order, OrderStatus>() {
    override fun execute(order: Order) {

    }
}

class RemoteAPI {
    fun makeCoffee(requestID: String) : Int {
        val responseCode = 1
        return responseCode
    }

    fun makeTea(requestID: String) : Int {
        val responseCode = 1
        return responseCode
    }
}
