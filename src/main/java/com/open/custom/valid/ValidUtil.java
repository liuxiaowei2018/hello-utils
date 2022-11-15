package com.open.custom.valid;

import com.open.custom.R;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

public class ValidUtil {

    public static final String ERROR_CODE = "403";

    public static <T> R<T> validate(T obj) {
        try {
            valid(obj);
            return R.success(obj);
        } catch (NotValidException e) {
            return R.error(e.getErrorMessage());
        }
    }

    public static <T> R<T> validate(T obj, Class<?>... groups) {
        try {
            valid(obj, groups);
            return R.success(obj);
        } catch (NotValidException e) {
            return R.error(e.getErrorMessage());
        }
    }

    public static <T> R<T> validate(T bean, String... properties) {
        try {
            validProperties(bean, properties);
            return R.success(bean);
        } catch (NotValidException e) {
            return R.error(e.getErrorMessage());
        }
    }

    public static <T> T valid(T bean) throws NotValidException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(bean);
        Iterator it = constraintViolations.iterator();
        if (it.hasNext()) {
            ConstraintViolation<Object> constraintViolation = (ConstraintViolation<Object>) it.next();
            throw new NotValidException(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
        }
        return bean;
    }

    public static <T> T valid(T bean, Class<?>... groups) throws NotValidException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(bean, groups);
        Iterator it = constraintViolations.iterator();
        if (it.hasNext()) {
            ConstraintViolation<Object> constraintViolation = (ConstraintViolation<Object>) it.next();
            throw new NotValidException(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
        }
        return bean;
    }

    public static <T> T validProperties(T bean, String... properties) throws NotValidException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        for (String property : properties) {
            Set<ConstraintViolation<Object>> constraintViolations = validator.validateProperty(bean, property);
            Iterator it = constraintViolations.iterator();
            if (it.hasNext()) {
                ConstraintViolation<Object> constraintViolation = (ConstraintViolation<Object>) it.next();
                throw new NotValidException(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
            }
        }
        return bean;
    }

    public static <X extends RuntimeException> void valid(boolean bol, Supplier<? extends X> exceptionSupplier, Runnable consumer) {
        if (bol) {
            if (consumer != null) {
                consumer.run();
            }
            throw exceptionSupplier.get();
        }
    }

    public static void valid(boolean bol, String errMsg, Runnable consumer) {
        if (bol) {
            if (consumer != null) {
                consumer.run();
            }
            throw new NotValidException(ERROR_CODE, errMsg);
        }
    }

    public static <T> void valid(Function<T, Boolean> validMethod, T parameter, String errMsg, Runnable consumer) {
        if (validMethod.apply(parameter)) {
            if (consumer != null) {
                consumer.run();
            }
            throw new NotValidException(ERROR_CODE, errMsg);
        }
    }

    public static <T> void valid(Function<T, Boolean> validMethod, String errMsg, Supplier<T> supplier, Runnable consumer) {
        if (validMethod.apply(supplier.get())) {
            if (consumer != null) {
                consumer.run();
            }
            throw new NotValidException(ERROR_CODE, errMsg);
        }
    }

    public static void valid(Supplier<Boolean> validMethod, String errMsg, Runnable consumer) {
        if (validMethod.get()) {
            if (consumer != null) {
                consumer.run();
            }
            throw new NotValidException(ERROR_CODE, errMsg);
        }
    }

    @SafeVarargs
    public static <T> void valid(Function<T[], Boolean> validMethod, String errMsg, Runnable consumer, T... parameter) {

        if (validMethod.apply(parameter)) {
            if (consumer != null) {
                consumer.run();
            }
            throw new NotValidException(ERROR_CODE, errMsg);
        }
    }

    public static <X extends RuntimeException> void valid(boolean bol, Supplier<? extends X> exceptionSupplier) {
        valid(bol, exceptionSupplier, null);
    }

    public static void valid(boolean bol, String errMsg) {
        valid(bol, errMsg, null);
    }

    public static <T> void valid(Function<T, Boolean> validMethod, T parameter, String errMsg) {
        valid(validMethod, parameter, errMsg, null);
    }

    public static <T> void valid(Function<T, Boolean> validMethod, String errMsg, Supplier<T> supplier) {
        valid(validMethod, errMsg, supplier, null);
    }

    public static void valid(Supplier<Boolean> validMethod, String errMsg) {
        valid(validMethod, errMsg, null);
    }

    @SafeVarargs
    public static <T> void valid(Function<T[], Boolean> validMethod, String errMsg, T... parameter) {
        valid(validMethod, errMsg, null, parameter);
    }

//    public static <T, R> T validProperties(T bean, SFunction<T, R>... properties) throws NotValidException {
//        Stream.of(properties)
//                .map(LambdaUtils::resolve)
//                .map(SerializedLambda::getImplMethodName)
//                .map(it -> StrUtil.startWith(it, "is") ? it.substring(2) : it.substring(3))
//                .map(StrUtil::lowerFirst)
//                .forEach(it -> validProperties(bean, it));
//        return bean;
//    }
}
