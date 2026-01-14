import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MesuresComponent } from './mesures.component';

describe('MesuresComponent', () => {
  let component: MesuresComponent;
  let fixture: ComponentFixture<MesuresComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MesuresComponent]
    });
    fixture = TestBed.createComponent(MesuresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
