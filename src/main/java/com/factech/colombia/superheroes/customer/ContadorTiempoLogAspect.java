package com.factech.colombia.superheroes.customer;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
@Slf4j
public class ContadorTiempoLogAspect {

    @Around("@annotation(ContadorTiempoLog)")
    public Object contadorTiempoLog(ProceedingJoinPoint point) throws Throwable {
        long inicio = new Date().getTime();
        Object result = point.proceed();
        long fin = new Date().getTime();
        caluclarDiferenciaTiempo(inicio, fin, (MethodSignature) point.getSignature());
        return result;
    }

    private void caluclarDiferenciaTiempo(long inicio, long fin, MethodSignature firma) {
        log.info("Clase {} Funcion {} tomo {} milisegundos", firma.getMethod().getDeclaringClass().getName(), firma.getMethod().getName(), TimeUnit.MILLISECONDS.toMillis(fin - inicio));
    }

}
