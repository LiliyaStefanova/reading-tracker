import { Component } from '@angular/core';
import { faBookReader } from '@fortawesome/free-solid-svg-icons/faBookReader';
import { faUser} from '@fortawesome/free-solid-svg-icons/faUser';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'my-book-tracker';
  faBookReader = faBookReader;
  faUser = faUser;
}
