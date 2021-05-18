package ru.javaops.graduation.util;

import lombok.experimental.UtilityClass;
import ru.javaops.graduation.error.IllegalRequestDataException;
import ru.javaops.graduation.model.AbstractBaseEntity;

@UtilityClass
public class ValidationUtil {
    public static void checkNew(AbstractBaseEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalRequestDataException(entity.getClass().getSimpleName() + " must be new (id=null)");
        }
    }

    //  Conservative when you reply, but accept liberally (http://stackoverflow.com/a/32728226/548473)
    public static void assureIdConsistent(AbstractBaseEntity entity, int id) {
        if (entity.isNew()) {
            entity.setId(id);
        } else if (entity.id() != id) {
            throw new IllegalRequestDataException(entity.getClass().getSimpleName() + " must has id=" + id);
        }
    }
}
