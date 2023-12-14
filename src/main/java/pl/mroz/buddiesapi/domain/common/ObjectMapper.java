package pl.mroz.buddiesapi.domain.common;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

@Slf4j
public class ObjectMapper {

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
