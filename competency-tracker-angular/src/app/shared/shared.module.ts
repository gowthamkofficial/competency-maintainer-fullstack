import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from './material/material.module';
import { DirectivesModule } from './directives/directives.module';
import { PipesModule } from './pipes/pipes.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ComponentsModule } from './components/components.module';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [],
  imports: [CommonModule, MaterialModule, DirectivesModule, PipesModule,FormsModule,ReactiveFormsModule,ComponentsModule,RouterModule],
  exports: [MaterialModule, DirectivesModule, PipesModule,FormsModule,ReactiveFormsModule,RouterModule],
})
export class SharedModule {}
