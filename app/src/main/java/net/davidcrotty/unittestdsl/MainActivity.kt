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

class Order(val requestID: String, val name: String, val email: String, val ingredients: Ingredients)

data class Ingredients(val waterTemperature: Int? = null,
                       val sugarCount: Int? = null,
                       val milk: Boolean? = null,
                       val keyIngredient: KeyIngredient? = null)

class OrderStatus(val success: Boolean, val orderNumber: Int, ingredients: Ingredients)

sealed class KeyIngredient {
    object BreakfastTea : KeyIngredient()
    object InstantCoffee: KeyIngredient()
    object CoffeeBeans: KeyIngredient()
}

abstract class UseCase<T, R> {

    protected val liveData = MutableLiveData<R>()

    abstract fun execute(param: T)

    fun observe() : MutableLiveData<R> {
        return liveData
    }
}

class DrinkUseCase(private val api: RemoteAPI) : UseCase<Order, OrderStatus>() {
    override fun execute(order: Order) {
        val orderNumber = if (order.ingredients.keyIngredient == KeyIngredient.BreakfastTea) {
            api.makeTea(order.requestID)
        } else {
            api.makeCoffee(order.requestID)
        }

        liveData.postValue(
            OrderStatus(true, orderNumber, order.ingredients)
        )
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
