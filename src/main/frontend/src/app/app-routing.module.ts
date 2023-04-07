import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from './_helpers/auth.guard';
import { AdminGuard } from './_helpers/admin.guard';

const routes: Routes = [
  { 
    path: '', 
    redirectTo: '/', 
    pathMatch: 'full' 
  },
  { 
    path: '', 
    loadChildren: () => import('./home/home.module').then(m => m.HomeModule) 
  },
  { 
    path: 'shop', 
    loadChildren: () => import('./shop/shop.module').then(m => m.ShopModule) 
  },
  { 
    path: 'shop/:productid',
    loadChildren: () => import('./product/product.module').then(m => m.ProductModule)
  },
  { 
    path: 'transactions', 
    loadChildren: () => import('./transactions/transactions.module').then(m => m.TransactionsModule), 
    canActivate:[AuthGuard] 
  },
  { 
    path: 'cart', 
    loadChildren: () => import('./shopping-cart/shopping-cart.module').then(m => m.ShoppingCartModule), 
    canActivate:[AuthGuard] 
  },
  { 
    path: 'checkout', 
    loadChildren: () => import('./check-out/check-out.module').then(m => m.CheckOutModule), 
    canActivate:[AuthGuard] 
  },
  { 
    path: 'collections', 
    loadChildren: () => import('./collections/collections.module').then(m => m.CollectionsModule)
  },
  { 
    path: 'login', 
    loadChildren: () => import('./login/login.module').then(m => m.LoginModule)
  },
  { 
    path: 'logout', 
    loadChildren: () => import('./logout/logout.module').then(m => m.LogoutModule),
    canActivate:[AuthGuard] 
  },
  { 
    path: 'signup',
    loadChildren: () => import('./signup/signup.module').then(m => m.SignupModule),
  },
  { 
    path: 'userprofile', 
    loadChildren: () => import('./userprofile/userprofile.module').then(m => m.UserProfileModule),
    canActivate:[AuthGuard] 
  },
  { 
    path: 'admin', 
    loadChildren: () => import('./admin/admin/admin.module').then(m => m.AdminModule),
    canActivate:[AdminGuard] 
  },
  { 
    path: 'admin/products', 
    loadChildren: () => import('./admin/products/products.module').then(m => m.AdminProductsModule),
    canActivate:[AdminGuard] 
  },
  { 
    path: 'admin/users', 
    loadChildren: () => import('./admin/users/users.module').then(m => m.AdminUsersModule),
    canActivate:[AdminGuard] 
  },
  { 
    path: 'not-found', 
    loadChildren: () => import('./not-found/not-found.module').then(m => m.NotFoundModule) 
  },

  // Default to Shop if not valid path
  { 
    path: '**', 
    redirectTo: '/not-found'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
