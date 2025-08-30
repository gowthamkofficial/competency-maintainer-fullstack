import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { showValidationMessage } from '../../../../core/helper/errorMessage.helper';
import { LoaderService } from '../../../../core/services/loader.service';
import { ToasterService } from '../../../../core/services/toaster.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: false,
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit {
  protected signupForm: FormGroup;

  constructor(
    private toaster: ToasterService,
    private loader: LoaderService,
    private router : Router
  ) {}

  ngOnInit(): void {
    this.initializeSignupForm();
  }

  initializeSignupForm() {
    this.signupForm = new FormGroup({
      firstName: new FormControl('Gowtham', [
        Validators.required,
        Validators.minLength(2),
      ]),
      lastName: new FormControl('Kathirvel', [
        Validators.required,
        Validators.minLength(2),
      ]),
      mobile: new FormControl('9566721028', [
        Validators.required,
        Validators.pattern(/^[0-9]{10}$/),
      ]),
      email: new FormControl('gowtham.k@gmail.com', [
        Validators.required,
        Validators.email,
      ]),
      address: new FormControl('6/8,Vaiyathan, Vikkiramangalam', [
        Validators.required,
      ]),
      state: new FormControl('Tamil Nadu', [Validators.required]),
      district: new FormControl('Madurai', [Validators.required]),
      pincode: new FormControl('625207', [
        Validators.required,
        Validators.pattern(/^[0-9]{6}$/),
      ]),
      password: new FormControl('12345678', [
        Validators.required,
        Validators.minLength(6),
      ]),
    });
  }

  showError(
    controlName: string,
    label: string,
    type: 'Provide' | 'Select' | 'Choose'
  ) {
    const control = this.signupForm.get(controlName);
    return showValidationMessage(control!, label, type);
  }

  get form() {
    return this.signupForm.controls;
  }

  signup() {
    if (this.signupForm.invalid) {
      this.toaster.error('Please fix the errors in the form');
      return;
    }
    // Call backend API via sessionService

    const payload = {
      firstName: this.signupForm.value.firstName,
      lastName: this.signupForm.value.lastName,
      mobileNumber: this.signupForm.value.mobile,
      emailAddress: this.signupForm.value.email,
      address: this.signupForm.value.address,
      state: this.signupForm.value.state,
      district: this.signupForm.value.district,
      pincode: this.signupForm.value.pincode,
      password: this.signupForm.value.password,
    }
  }
}
