import { NgModule }       from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HttpModule, JsonpModule, XHRBackend } from '@angular/http';
import { WikiComponent } from './wiki/wiki.component'
import { AppComponent }   from './app.component';
import { HeroListPromiseComponent } from './toh/list.component.promise';
import { HeroListComponent  } from './toh/hero-list.component';
import { WikiSmartComponent } from './wiki/wiki-smart.component';
import { MysqlListComponent } from './mysql/mysql-list.component';
import { MysqlService } from './mysql/mysql.service';
@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
 JsonpModule
  ],
  declarations: [
    AppComponent,
    HeroListComponent,
WikiSmartComponent,
MysqlListComponent,
HeroListPromiseComponent,
WikiComponent
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule {
}

