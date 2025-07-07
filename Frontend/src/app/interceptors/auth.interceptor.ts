// interceptors/auth.interceptor.ts
import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  // No agregar ningún token, todas las peticiones serán públicas
  return next(req);
};
