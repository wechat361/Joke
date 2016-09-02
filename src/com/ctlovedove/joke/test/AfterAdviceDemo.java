package com.ctlovedove.joke.test;

import java.lang.reflect.Method;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.AfterAdvice;
import org.springframework.aop.AfterReturningAdvice;

public class AfterAdviceDemo implements AfterAdvice {

}

class AfterReturnAdviceDemo implements AfterReturningAdvice {

	@Override
	@After(value="")
	@Before("")
	public void afterReturning(Object obj, Method method, Object[] aobj,
			Object obj1) throws Throwable {
		
		
	}
	
}