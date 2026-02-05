import { test, expect } from '@playwright/test';
import { readFile } from 'node:fs/promises';
import path from 'node:path';

const WEB_DIR = path.resolve(__dirname, '../../web');
const INDEX_PATH = path.join(WEB_DIR, 'index.html');
const SCRIPT_PATH = path.join(WEB_DIR, 'script.js');
const STYLE_PATH = path.join(WEB_DIR, 'style.css');

async function loadApp(page) {
  const fallbackUrl = process.env.TODO_APP_URL || process.env.APP_URL;
  if (fallbackUrl) {
    await page.goto(fallbackUrl);
    return;
  }

  const html = await readFile(INDEX_PATH, 'utf8');
  await page.setContent(html, { waitUntil: 'domcontentloaded' });
  await page.addStyleTag({ content: await readFile(STYLE_PATH, 'utf8') });
  await page.addScriptTag({ content: await readFile(SCRIPT_PATH, 'utf8') });

  // The static script listens for DOMContentLoaded; dispatch again so listeners run
  await page.evaluate(() => {
    document.dispatchEvent(new Event('DOMContentLoaded', { bubbles: true }));
  });
}

test('test', async ({ page }) => {
  await loadApp(page);
  await page.getByTestId('new-todo').click();
  await page.getByTestId('new-todo').fill('Buy Milk');
  await page.getByTestId('add-todo').click();
  await expect(page.getByText('Buy Milk')).toBeVisible();
});
