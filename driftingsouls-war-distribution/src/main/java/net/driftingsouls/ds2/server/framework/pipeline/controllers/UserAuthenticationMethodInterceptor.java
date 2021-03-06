package net.driftingsouls.ds2.server.framework.pipeline.controllers;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

import net.driftingsouls.ds2.interfaces.annotations.controllers.KeinLoginNotwendig;
import net.driftingsouls.ds2.interfaces.framework.ContextMap;
import net.driftingsouls.ds2.server.framework.NotLoggedInException;

/**
 * Realisiert die notwendige Authentifizierung fuer Controller.
 */
@Component
public class UserAuthenticationMethodInterceptor implements ActionMethodInterceptor
{
	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable
	{
		if(ContextMap.getContext().getActiveUser() != null)
		{
			return methodInvocation.proceed();
		}
		if(!methodInvocation.getThis().getClass().isAnnotationPresent(KeinLoginNotwendig.class) &&
				!methodInvocation.getMethod().isAnnotationPresent(KeinLoginNotwendig.class))
		{
			throw new NotLoggedInException();
		}

		return methodInvocation.proceed();
	}
}
