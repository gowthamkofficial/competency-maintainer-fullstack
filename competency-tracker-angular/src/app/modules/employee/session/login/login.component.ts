import { Component, OnInit } from '@angular/core';
import { showValidationMessage } from '../../../../core/helper/errorMessage.helper';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { LoaderService } from '../../../../core/services/loader.service';
import { ToasterService } from '../../../../core/services/toaster.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {
  protected loginForm: FormGroup;

  constructor(
    private toaster: ToasterService,
    private loader: LoaderService,
    private router : Router
  ) {}

  ngOnInit(): void {
    this.initialLoginForm();
  }

  initialLoginForm() {
    this.loginForm = new FormGroup({
      email: new FormControl('gowthamkoffcl@gmail.com', [Validators.required, Validators.email]),
      password: new FormControl('12345678', [Validators.required]),
    });
  }

  showToaster() {
    this.toaster.success('Toaster is working');
  }

  showError(controlName, label, type) {
    const control = this.loginForm.get(controlName);
    return showValidationMessage(control, label, type);
  }

  get form() {
    return this.loginForm.controls;
  }

  login() {
    this.loginForm.markAllAsTouched()
    if (this.loginForm.invalid) {
      this.toaster.error('Kindly fill all required fields');
      return;
    }

  }



}
