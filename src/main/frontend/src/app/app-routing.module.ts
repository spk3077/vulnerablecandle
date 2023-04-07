import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from './_helpers/auth.guard';
import { AdminGuard } from './_helpers/admin.guard';
import { LogoutComponent } from './logout/logout.component';
import { AdminComponent } from './admin/admin/admin.component';
import { AdminProductsComponent } from './admin/products/products.component';
import { AdminUsersComponent } from './admin/users/users.component';

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
    path: 'shop/:productid',
    loadChildren: () => import('./product/product.module').then(m => m.ProductModule)
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
    component: LogoutComponent,
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
    component: AdminComponent,
    canActivate:[AdminGuard] 
  },
  { 
    path: 'admin/products', 
    component: AdminProductsComponent, 
    canActivate:[AdminGuard] 
  },
  { 
    path: 'admin/users', 
    component: AdminUsersComponent, 
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
