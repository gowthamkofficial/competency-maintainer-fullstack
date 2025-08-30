import { AbstractControl } from '@angular/forms';

export type FieldType = 'Provide' | 'Select' | 'Choose';

export type ErrorTypes =
  | 'required'
  | 'minlength'
  | 'maxlength'
  | 'pattern'
  | 'email'
  | 'min'
  | 'max';

// helper to lowercase fieldType
function formatFieldType(type: FieldType): string {
  return type.toLowerCase();
}

// helper to capitalize label
function capitalize(word: string): string {
  if (!word) return '';
  return word.charAt(0).toUpperCase() + word.slice(1).toLowerCase();
}

export function showValidationMessage(
  control: AbstractControl,
  label: string,
  fieldType: FieldType
): string | null {
  if (!control || control.valid || !control.errors) {
    return null;
  }

  const errors = control.errors;
  const formattedFieldType = formatFieldType(fieldType);
  const formattedLabel = capitalize(label);

  if (errors['required']) {
    return `Kindly ${formattedFieldType} ${formattedLabel}`;
  }

  if (errors['minlength']) {
    return `Kindly ensure ${formattedLabel} is at least ${errors['minlength'].requiredLength} characters (currently ${errors['minlength'].actualLength}).`;
  }

  if (errors['maxlength']) {
    return `Kindly ensure ${formattedLabel} does not exceed ${errors['maxlength'].requiredLength} characters (currently ${errors['maxlength'].actualLength}).`;
  }

  if (errors['pattern']) {
    return `Kindly provide a valid ${formattedLabel} format.`;
  }

  if (errors['email']) {
    return `Kindly provide a valid email address.`;
  }

  if (errors['min']) {
    return `Kindly ensure ${formattedLabel} is at least ${errors['min'].min} (currently ${errors['min'].actual}).`;
  }

  if (errors['max']) {
    return `Kindly ensure ${formattedLabel} is not greater than ${errors['max'].max} (currently ${errors['max'].actual}).`;
  }

  const firstErrorKey = Object.keys(errors)[0];
  return `Kindly provide a valid ${formattedLabel} (${firstErrorKey}).`;
}
