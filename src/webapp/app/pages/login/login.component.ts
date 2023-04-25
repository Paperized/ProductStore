import {Component, ViewEncapsulation} from '@angular/core';
import {AbstractControl, FormBuilder, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {Login} from "../../models/Login";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  loginForm = this.formBuilder.group({
    username: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(16)]],
    password: ['', [Validators.required, Validators.minLength(6)]]
  });

  constructor(private formBuilder: FormBuilder, private router: Router, private authService: AuthService) { }

  onSubmit() {
    this.authService.login(new Login(this.loginForm.value.username!, this.loginForm.value.password!))
      .subscribe({
        next: _ => this.router.navigate(['/']),
        error: this.onError
      });
  }

  onError(err: any) {
    console.log(err);
  }
}
