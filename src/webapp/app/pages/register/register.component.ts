import { Component } from '@angular/core';
import {AbstractControl, FormBuilder, Validators} from "@angular/forms";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {

  registerForm = this.formBuilder.group({
    username: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(16)]],
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(6), this.validateMatchingPassword(true)]],
    confirmPassword: ['', [Validators.required, this.validateMatchingPassword(false)]]
  });

  constructor(private formBuilder: FormBuilder) { }

  onSubmit() {
    console.warn('Your order has been submitted', this.registerForm.value);
  }

  validateMatchingPassword(isOriginal: boolean) {
    return (control: AbstractControl) => {
      if(!control.parent) return null;
      let otherControl = isOriginal ? control.parent.get('confirmPassword') : control.parent.get('password');
      const res = otherControl?.value === control.value ? null : { 'passwordMismatch': true };
      if(isOriginal && res) {
        otherControl?.setErrors(res);
        return null;
      }

      return res;
    }
  }
}
