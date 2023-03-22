import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from './_helpers/auth.guard';
import { AdminGuard } from './_helpers/admin.guard';
import { HomeComponent } from './home/home.component';
import { ShopComponent } from './shop/shop.component';
import { TransactionsComponent } from './transactions/transactions.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { CheckOutComponent } from './check-out/check-out.component';
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
  { path: '', redirectTo: '/', pathMatch: 'full' },
  { path: '', component: HomeComponent },
  { path: 'shop', component: ShopComponent },
  { path: 'transactions', component: TransactionsComponent, canActivate:[AuthGuard] },
  { path: 'cart', component: ShoppingCartComponent, canActivate:[AuthGuard] },
  { path: 'checkout', component: CheckOutComponent, canActivate:[AuthGuard] },
  { path: 'shop/:productid', component: ProductComponent },
  { path: 'collections', component: CollectionsComponent },
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent, canActivate:[AuthGuard] },
  { path: 'signup', component: SignupComponent },
  { path: 'userprofile', component: UserProfileComponent, canActivate:[AuthGuard] },
  { path: 'admin', component: AdminComponent, canActivate:[AdminGuard] },
  { path: 'admin/products', component: AdminProductsComponent, canActivate:[AdminGuard] },
  { path: 'admin/users', component: AdminUsersComponent, canActivate:[AdminGuard] },
  { path: 'not-found', component: NotFoundComponent },

  // Default to Shop if not valid path
  { path: '**', redirectTo: '/not-found'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
