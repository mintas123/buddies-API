package pl.mroz.buddiesapi.domain.common;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

@Slf4j
public class ObjectMapper {

    /**
     * Apply data from one object into another.
     *
     * @param updating Object from which the data is taken
     * @param updated Object to which the data is applied
     * @return Old object updated with new data
     * @param <T> Class of new object (Most commonly should be same as old)
     * @param <R> Class of old object (Most commonly should be same as new)
     */
    public static <T, R> R mapInto(T updating, R updated) {
        for (Field updatingField : updating.getClass().getDeclaredFields()) {
            try {
                var updatedField = updated.getClass().getDeclaredField(updatingField.getName());
                updatingField.setAccessible(true);
                updatedField.setAccessible(true);

                var updatingValue = updatingField.get(updating);
                var updatedValue = updatedField.get(updated);

                if (updatingValue != null && updatedValue != null 
                        && updatingValue.getClass() == updatedValue.getClass()) {
                    updatedField.set(updated, updatingValue);
                }
            } catch (IllegalAccessException | NoSuchFieldException e) {
                log.error("Failed mapping. Reason: {}", e.getMessage());
            }
        }
        return updated;
    }

}
