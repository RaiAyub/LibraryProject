import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthService } from 'src/service/auth/auth-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string = "";
  password: string = "";

  constructor(private authService: AuthService, private router: Router) { }



  login(): void {
    const credentials = {
      username: this.username,
      password: this.password
    };
  
    this.authService.login(credentials).subscribe(
      response => {
        console.log('Login successful', response);
        // Perform any desired actions upon successful login
        this.router.navigate(['/dashboard']);
      },
      error => {
        console.error('Login failed', error);
        // Handle login error
      }
    );
  }

}
