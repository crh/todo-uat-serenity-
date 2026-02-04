import { test, expect } from '@playwright/test';

test('test', async ({ page }) => {
  await page.goto('http://localhost:3000/');
  await page.getByTestId('new-todo').click();
  await page.getByTestId('new-todo').fill('Buy Milk');
  await page.getByTestId('add-todo').click();
  await expect(page.getByText('Buy Milk')).toBeVisible();
});