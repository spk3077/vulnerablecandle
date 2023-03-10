import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ShopComponent } from './shop/shop.component';
import { TransactionsComponent } from './transactions/transactions.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { ProductComponent } from './product/product.component';
import { CollectionsComponent } from './collections/collections.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { SignupComponent } from './signup/signup.component';
import { UserProfileComponent } from './userprofile/userprofile.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { AdminUsersComponent } from './admin/users/users.component';
import { AdminProductsComponent } from './admin/products/products.component';

const routes: Routes = [
  // { path: '', redirectTo: '/shop', pathMatch: 'full' },
  { path: '', component: ShopComponent },
  { path: 'shop', component: ShopComponent },
  { path: 'transactions', component: TransactionsComponent },
  { path: 'cart', component: ShoppingCartComponent },
  { path: 'product/:productid', component: ProductComponent },
  { path: 'collections', component: CollectionsComponent },
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'userprofile', component: UserProfileComponent },
  { path: 'not-found', component: NotFoundComponent },
  { path: 'admin/products', component: AdminProductsComponent },
  { path: 'admin/users', component: AdminUsersComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
