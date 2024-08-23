package buddiesapi.domain.common

import java.lang.reflect.Field
import mu.KLogging

object ObjectMapper : KLogging() {
    /**
     * Apply data from one object into another.
     *
     * @param updating Object from which the data is taken
     * @param updated Object to which the data is applied
     * @return Old object updated with new data
     * @param <T> Class of new object (Most commonly should be same as old)
     * @param <R> Class of old object (Most commonly should be same as new)
     */
    fun <T, R> mapInto(updating: T, updated: R): R {
        for (updatingField in updating!!::class.java.declaredFields) {
            try {
                val updatedField: Field = updated!!::class.java.getDeclaredField(updatingField.name)
                updatingField.isAccessible = true
                updatedField.isAccessible = true

                val updatingValue = updatingField[updating]
                val updatedValue = updatedField[updated]

                if (updatingValue != null && updatedValue != null && updatingValue.javaClass == updatedValue.javaClass) {
                    updatedField[updated] = updatingValue
                }
            } catch (e: RuntimeException) {
                logger.error { "Failed mapping. Reason: ${e.message}}" }
            }
        }
        return updated
    }
}
