package net.davidcrotty.unittestdsl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations
import java.security.Key

class DrinkUseCaseTest {

    @get:Rule
    val instant: TestRule = InstantTaskExecutorRule()

    @Test
    fun `when calculating an order`() {
        // Given a tea order
        val order = order {
            ingredients {
                KeyIngredient.BreakfastTea
            }
        }
        // When executing it
        val case = useCase {
            RemoteAPI()
        }
        val result = case.observe()
        case.execute(order)

        // Then should have a Tea order
    }
}

fun useCase(action: RemoteAPI.() -> Unit) : DrinkUseCase {
    val api = RemoteAPI()
    api.action()
    return DrinkUseCase(api)
}

fun order(action: Ingredients.() -> Unit) : Order {
    val requestID = ""
    val email = ""
    val name = ""
    val ingredients = Ingredients()
    ingredients.action()
    return Order(requestID, email, name, ingredients)
}

fun ingredients(action: () -> KeyIngredient) : Ingredients {
    val waterTemperature = 1
    val sugarCount = 1
    val milk = true
    val keyIngredient = action.invoke()
    return Ingredients(waterTemperature, sugarCount, milk, keyIngredient)
}