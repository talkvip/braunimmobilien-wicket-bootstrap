import { Component, OnInit } from '@angular/core';
import { Hero }                from './hero';
import { MysqlService }         from './mysql.service';
@Component({
  selector: 'mysql-list',
   templateUrl: './app/mysql/mysql-list.component.html',
 providers: [ MysqlService ]
})


export class MysqlListComponent implements OnInit {
  errorMessage: string;
  heroes: Hero[];
  mode = 'Observable';
  constructor (private mysqlService: MysqlService) {}
  ngOnInit() { this.getHeroes(); }
  getHeroes() {
    this.mysqlService.getHeroes()
                     .subscribe(
                       heroes => this.heroes = heroes,
                       error =>  this.errorMessage = error);
  }
   addHero (name: string) {
    if (!name) { return; }
    this.mysqlService.addHero(name)
                     .subscribe(
                       hero  => this.heroes.push(hero),
                       error =>  this.errorMessage = error);
  }
}



