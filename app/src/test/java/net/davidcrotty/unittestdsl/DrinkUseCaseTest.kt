package net.davidcrotty.unittestdsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import fr.xgouchet.elmyr.junit.JUnitForger



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
        assertThat(result.value!!.success).`as`("Order for \"%s\" was \"%b\"", order.ingredients.keyIngredient, result.value!!.success).isEqualTo(true)
    }
}

var forger = JUnitForger()

fun useCase(action: RemoteAPI.() -> Unit) : DrinkUseCase {
    val api = RemoteAPI()
    api.action()
    return DrinkUseCase(api)
}

fun order(action: () -> Ingredients) : Order {
    val requestID = forger.aNumericalString(200)
    val email = forger.anEmail()
    val name = forger.aNumericalString(200)
    val ingredients = action.invoke()
    return Order(requestID, email, name, ingredients)
}

fun ingredients(action: () -> KeyIngredient) : Ingredients {
    val waterTemperature = forger.anInt()
    val sugarCount = forger.anInt()
    val milk = forger.aBool()
    val keyIngredient = action.invoke()
    return Ingredients(waterTemperature, sugarCount, milk, keyIngredient)
}