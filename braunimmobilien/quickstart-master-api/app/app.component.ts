import { Component }          from '@angular/core';
import './rxjs-operators';
@Component({
  selector: 'my-app',
  template: `
    <h1>Rest Api Examples</h1>
   <hero-list></hero-list>
<my-wiki></my-wiki>
<hero-list-promise></hero-list-promise>
<my-wiki-smart></my-wiki-smart>
<mysql-list></mysql-list>
  `,
  styleUrls: ['app/app.component.css']
})
export class AppComponent {

}

