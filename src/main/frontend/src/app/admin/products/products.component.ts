import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PATTERNS } from '@app/_core/constants';
import { DefaultResponse } from '@app/_core/defaultResponse';

import { ProductReceive, ProductSend } from '@app/_core/product';
import { TagReceive } from '@app/_core/tag';
import { ProductService } from '@app/_services/product.service';
import { TagService } from '@app/_services/tag.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class AdminProductsComponent implements OnInit{
  products: ProductReceive[] = [];
  // tags: TagReceive[] = [];
  // tagHeads: string[] = [];

  // Form Values
  addForm:any = FormGroup;
  addSubmitted = false;

  // Form Values
  editForm:any = FormGroup;
  editProductID: number = 1;
  displayEditForm: boolean = false;
  editSubmitted = false;

  // Display Variables
  getProductsError: boolean = false;
  getTagsError: boolean = false;
  addProductSuccess: boolean = false;
  addProductError: boolean = false;
  editProductError: boolean = false;
  
  constructor(
    private formBuilder: FormBuilder,
    private productService: ProductService,
    private tagService: TagService
    ) { 
      this.addForm = this.formBuilder.group({
        name: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(50)]],
        brand: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(50)]],
        price: ['', [Validators.required, Validators.min(.01), Validators.max(99.99)]],
        stock: ['', [Validators.required, Validators.min(1), Validators.pattern(PATTERNS.STOCK)]],
        description: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(2000)]]
        });
      
    this.editForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(50)]],
      brand: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(50)]],
      price: ['', [Validators.required, Validators.min(.01), Validators.max(99.99)]],
      stock: ['', [Validators.required, Validators.min(1), Validators.pattern(PATTERNS.STOCK)]],
      description: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(2000)]]
      });
    }
  
  ngOnInit(): void {
    this.getProducts();
    // this.getTags();
  }

  //Add addProduct form actions
  get fAdd() { return this.addForm.controls; }

  //Add editProduct form actions
  get fEdit() { return this.editForm.controls; }

  // Retrieve Products
  private getProducts(): void {
    this.productService.getProducts().subscribe({
      next: (res) => {
        if (res.length <= 0) {
          this.getProductsError = true;
          return;
        }
        
        this.products = [];
        Array.from(res).forEach((element: unknown) => {
          let product: any = element;
          this.products.push(ProductReceive.forAdmin(product.id, product.name, product.brand, product.description, 
            product.tagNames, product.price, product.stock, product.image, product.averageReviewGrade));
          }
        );
      },
      error: () => {
        // Failed at server
        this.getProductsError = true;
      }}
    );
  }

  // Retrieve Tags
  // private getTags(): void {
  //   this.tagService.getTags().subscribe({
  //     next: (res) => {
  //       if (res.length <= 0) {
  //         this.getTagsError = true;
  //       }
  //       Array.from(res).forEach((element: unknown) => {
  //         let tag: any = element;
  //         this.tags.push(new TagReceive(tag.id, tag.name, tag.type));
  //         if (!this.tagHeads.includes(tag.type)) {
  //           this.tagHeads.push(tag.type);
  //         }
          
  //       });
  //     },
  //     error: () => {
  //       // Failed at server
  //       this.getTagsError = true;
  //     }}
  //   );
  // }

  // Form Add Product Submission
  public addProduct(): void {
    this.addSubmitted = true;
    
    // stop here if form is invalid
    if (this.addForm.invalid) {
        return;
    }
    //True if all the fields are valid
    if(this.addSubmitted) {
      const productInfo: ProductSend = ProductSend.forAdminAdd(this.addForm.value.name, this.addForm.value.brand, this.addForm.value.price, 
        this.addForm.value.stock, this.addForm.value.description);
      this.productService.addProduct(productInfo).subscribe({
        // If Successful
        next: (res) => {
          const addProductResponse: DefaultResponse = res as DefaultResponse;
          if (addProductResponse.success != true) {
            this.addProductError = true;
            this.addProductSuccess = false;
            return;
          }

          this.getProducts();
          this.addProductSuccess = true;
          this.addProductError = false;
          this.addSubmitted = false;
          this.addForm.reset();
        },
        // If fails at server
        error: () => {
          this.addProductError = true;
          this.addProductSuccess = false;
          return;
        }
      });
    }
  }

  // Displays the EditForm and updates it's associated values
  public openEditForm(productID: number): void {
    // Edit Product Form Update
    let product: any = this.products.find(i => i.id === productID);
    this.editForm.patchValue({
      name: product.name,
      brand: product.brand,
      price: product.price,
      stock: product.stock,
      description: product.description
    });
    
    this.displayEditForm = true;
    this.editProductID = productID;
  }

  // Form Edit Product Submission
  public editProduct(): void {
    this.editSubmitted = true;
    
    // stop here if form is invalid
    if (this.editForm.invalid) {
        return;
    }

    //True if all the fields are valid
    if(this.editSubmitted) {
      const productInfo: ProductSend = ProductSend.forAdminEdit(this.editProductID, this.editForm.value.name, 
        this.editForm.value.brand, this.editForm.value.price, this.editForm.value.stock, this.editForm.value.description);
      this.productService.updateProduct(productInfo).subscribe({
        // If Successful
        next: (res) => {
          const editProductResponse: DefaultResponse = res as DefaultResponse;
          if (editProductResponse.success != true) {
            this.editProductError = true;
            return;
          }

          this.getProducts();
          this.editProductError = false;
          this.editSubmitted = false;
          this.displayEditForm = false;
        },
        // If fails at server
        error: () => {
          this.editProductError = true;
          return;
        }
      });
    }
  }

}
