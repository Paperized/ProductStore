import {Directive, ElementRef, Input, OnChanges, OnDestroy, SimpleChanges} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";
import {Observable, Subscription} from "rxjs";

@Directive({
  selector: '[errorTranslate]'
})
export class ErrorTranslatorDirective implements OnChanges, OnDestroy {
  private observableStream?: Subscription;
  @Input() errorCode?: string;

  constructor(private elementRef: ElementRef,
              private translateService: TranslateService) {
  }

  ngOnDestroy(): void {
    this.observableStream?.unsubscribe();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (this.errorCode === undefined) return;
    const key = `errors.serverResponse.${this.errorCode}`;

    this.observableStream?.unsubscribe();
    this.observableStream = this.translateService.stream(key).subscribe({
      next: v => this.elementRef.nativeElement.innerText = v
    });
  }
}
