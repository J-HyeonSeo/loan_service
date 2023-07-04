package com.jhsfully.domain.aop;

import com.jhsfully.domain.util.EncryptComponent;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Optional;

@EnableAspectJAutoProxy
@Aspect
@Component
@RequiredArgsConstructor
public class EncryptAspect {

    private final EncryptComponent encryptComponent;

    @Pointcut("execution(* com.jhsfully.domain.repository.*.find*(..))")
    private void findPointCut() {}

    @Pointcut("execution(* com.jhsfully.domain.repository.*.save*(..))")
    private void savePointCut() {}

    @Around("savePointCut()")
    public Object encryptData(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.getArgs()[0];
        System.out.println(result);

        Object entity = result;

        Class<?> entityClass = entity.getClass();
        Field[] fields = entityClass.getDeclaredFields();

        for(Field field : fields){

            if(!field.isAnnotationPresent(Encrypt.class)){
                continue;
            }

            field.setAccessible(true);
            Object value = field.get(entity);

            //execute encrypt
            String encryptedString = encryptComponent.encryptString((String) value);

            field.set(entity, encryptedString);
            System.out.println(value);
        }

        return joinPoint.proceed();
    }


    @Around("findPointCut()")
    public Object decryptData(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();

        if(result instanceof Optional<?>){

            Optional<?> optional = (Optional<?>) result;

            if(!optional.isPresent()){
                return result;
            }

            Object entity = optional.get();

            Class<?> entityClass = entity.getClass();
            Field[] fields = entityClass.getDeclaredFields();

            for(Field field : fields){

                if(!field.isAnnotationPresent(Encrypt.class)){
                    continue;
                }

                field.setAccessible(true);
                Object value = field.get(entity);

                //execute decrypt
                String decryptedString = encryptComponent.decryptString((String)value);

                field.set(entity, decryptedString);
                System.out.println(value);
            }

        }

        return result;
    }

}
