import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicidadeListComponent } from './publicidade-list.component';

describe('PublicidadeListComponent', () => {
  let component: PublicidadeListComponent;
  let fixture: ComponentFixture<PublicidadeListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PublicidadeListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PublicidadeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
