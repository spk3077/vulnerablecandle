import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from './_helpers/auth.guard';
import { AdminGuard } from './_helpers/admin.guard';
import { ProductComponent } from './product/product.component';
import { CollectionsComponent } from './collections/collections.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { SignupComponent } from './signup/signup.component';
import { UserProfileComponent } from './userprofile/userprofile.component';
import { NotFoundComponent } from './not-found/not-found.component';
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
    component: ProductComponent 
  },
  { 
    path: 'collections', 
    component: CollectionsComponent 
  },
  { 
    path: 'login', 
    component: LoginComponent 
  },
  { 
    path: 'logout', 
    component: LogoutComponent, 
    canActivate:[AuthGuard] 
  },
  { 
    path: 'signup', 
    component: SignupComponent 
  },
  { 
    path: 'userprofile', 
    component: UserProfileComponent, 
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
    component: NotFoundComponent 
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
